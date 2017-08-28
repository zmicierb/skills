package com.barysevich.project.service.impl;

import com.barysevich.project.controller.dto.ContainerAction;
import com.barysevich.project.controller.dto.SkillContainer;
import com.barysevich.project.model.Row;
import com.barysevich.project.model.SkillSum;
import com.barysevich.project.repository.SkillSumRepository;
import com.barysevich.project.service.SkillService;
import com.barysevich.project.service.SkillSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class SkillSumServiceImpl extends GenericServiceImpl<SkillSum, Long> implements SkillSumService {

    @Autowired
    private SkillSumRepository skillSumRepository;

    @Autowired
    private SkillService skillService;

    @Autowired
    public SkillSumServiceImpl(SkillSumRepository repository) {
        super(repository);
        this.skillSumRepository = repository;
    }

    @Override
    public Iterable<SkillSum> findByPersonId(Long id) {
        return skillSumRepository.findByPersonId(id);
    }

    @Override
    @Transactional
    public SkillSum save(SkillSum skillSum) {
        SkillSum skillSumNew = skillSumRepository.save(skillSum);
        updateTotalAmount(skillSumNew.getPersonId());
        return skillSum;
    }

    @Transactional
    @Override
    public void update(Long personId, Iterable<SkillSum> skillSumsNew) {

        Iterable<SkillSum> skillSumsOld = skillSumRepository.findByPersonIdEager(personId);
        Map<Row, HashMap<String, SkillSum>> skillSumsOldMap = new HashMap<>();
        Map<Row, HashMap<String, SkillSum>> skillSumsNewMap = new HashMap<>();

        skillSumsOld.forEach(a -> {
            skillSumsOldMap.computeIfAbsent(a.getRow(), k -> skillSumsOldMap.put(k, new HashMap<>()));
            skillSumsOldMap.get(a.getRow()).put(a.getSkill().getName(), a);
        });

        skillSumsNew.forEach(a -> {
            skillSumsNewMap.computeIfAbsent(a.getRow(), k -> skillSumsNewMap.put(k, new HashMap<>()));
            skillSumsNewMap.get(a.getRow()).put(a.getSkill().getName(), a);
        });

        ArrayList<SkillContainer> skillContainers = new ArrayList<>();

        //compare old and new maps and fill in skillContainers
        skillSumsOldMap.keySet().forEach(row -> {
            skillSumsNewMap
                    .computeIfPresent(row, (kRowFromNew, vRowFromNew) -> {
                        skillSumsOldMap.get(kRowFromNew).forEach((kSkillFromOld, vSkillFromOld) -> {
                            vRowFromNew.computeIfPresent(kSkillFromOld, (k, v) -> {
                                if (!v.getPosition().equals(vSkillFromOld.getPosition())) {
                                    vSkillFromOld.setPosition(v.getPosition());
                                    skillContainers.add(new SkillContainer(vSkillFromOld, ContainerAction.UPDATE));
                                }
                                return vRowFromNew.get(kSkillFromOld);
                            });
                            vRowFromNew.computeIfAbsent(kSkillFromOld, k -> {
                                skillContainers.add(new SkillContainer(vSkillFromOld, ContainerAction.DELETE));
                                return null;
                            });
                        });
                        return skillSumsNewMap.get(row);
                    });
            skillSumsNewMap
                    .computeIfAbsent(row, k -> {
                        skillSumsOldMap.get(k).values().forEach(vSkillFromOld ->
                                skillContainers.add(new SkillContainer(vSkillFromOld, ContainerAction.DELETE))
                        );
                        return null;
                    });
        });

        skillSumsNewMap.keySet().forEach(row -> {
            skillSumsOldMap
                    .computeIfPresent(row, (kRowFromOld, vRowFromOld) -> {
                        skillSumsNewMap.get(kRowFromOld).forEach((kSkillFromNew, vSkillFromNew) ->
                                vRowFromOld.computeIfAbsent(kSkillFromNew, k -> {
                                    skillContainers.add(new SkillContainer(vSkillFromNew, ContainerAction.INSERT));
                                    return null;
                                })
                        );
                        return skillSumsOldMap.get(row);
                    });
            skillSumsOldMap
                    .computeIfAbsent(row, k -> {
                        skillSumsNewMap.get(k).values().forEach(vSkillFromNew ->
                                skillContainers.add(new SkillContainer(vSkillFromNew, ContainerAction.INSERT))
                        );
                        return null;
                    });
        });

        //process skillContainers
        skillContainers.forEach(skillContainer -> {
            switch (skillContainer.getAction()) {
                case DELETE:
                    skillSumRepository.delete(skillContainer.getSkillSum().getId());
                    break;
                case INSERT:
                    skillContainer.getSkillSum().setSkill(skillService.save(skillContainer.getSkillSum().getSkill()));
                    skillSumRepository.save(skillContainer.getSkillSum());
                    break;
                case UPDATE:
                    skillSumRepository.save(skillContainer.getSkillSum());
                    break;
                default:
            }
        });

        updateTotalAmount(personId);
    }

    @Transactional
    public void updateTotalAmount(Long personId) {
        Integer totalAmount = skillSumRepository.countByPersonId(personId);
        Iterable<SkillSum> skillSums = skillSumRepository.checkTotalAmountByPersonId(personId, totalAmount);

        skillSums.forEach(skillSum -> {
            skillSum.setTotalAmount(totalAmount);
            skillSumRepository.save(skillSum);
        });
    }
}
