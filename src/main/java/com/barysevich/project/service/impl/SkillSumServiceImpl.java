package com.barysevich.project.service.impl;

import com.barysevich.project.controller.dto.ContainerAction;
import com.barysevich.project.controller.dto.SkillContainer;
import com.barysevich.project.model.Row;
import com.barysevich.project.model.Skill;
import com.barysevich.project.model.SkillSum;
import com.barysevich.project.repository.RowRepository;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.repository.SkillSumRepository;
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
    private SkillRepository skillRepository;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    public SkillSumServiceImpl(SkillSumRepository repository) {
        super(repository);
        this.skillSumRepository = repository;
    }

    @Override
    public Iterable<SkillSum> findByPersonId(Long id) {
        return skillSumRepository.findByPersonId(id);
    }

    @Transactional
    @Override
    public void update(Long personId, Iterable<SkillSum> skillSumsNew) {

        Iterable<SkillSum> skillSumsOld = skillSumRepository.findByPersonId(personId);
        Map<Row, HashMap<Long, SkillSum>> skillSumsOldMap = new HashMap<>();
        Map<Row, HashMap<Long, SkillSum>> skillSumsNewMap = new HashMap<>();

        skillSumsOld.forEach(a -> {
            if (!skillSumsOldMap.containsKey(a.getRow())) {
                skillSumsOldMap.put(a.getRow(), new HashMap<>());
            }
            skillSumsOldMap.get(a.getRow()).put(a.getSkill().getId(), a);
        });

        skillSumsNew.forEach(a -> {
            if (!skillSumsNewMap.containsKey(a.getRow())) {
                skillSumsNewMap.put(a.getRow(), new HashMap<>());
            }
            skillSumsNewMap.get(a.getRow()).put(a.getSkill().getId(), a);
        });

        ArrayList<SkillContainer> skillContainers = new ArrayList<>();

        //compare old and new maps and fill in skillContainers
        skillSumsOldMap.keySet().forEach(row -> {
            if (skillSumsNewMap.containsKey(row)) {
                skillSumsOldMap.get(row).forEach((kOld, vOld) -> {
                    if (skillSumsNewMap.get(row).containsKey(kOld)) {
                        if (!skillSumsNewMap.get(row).get(kOld).getWeight().equals(vOld.getWeight()))
                            skillContainers.add(new SkillContainer(skillSumsNewMap.get(row).get(kOld), ContainerAction.UPDATE));
                    } else {
                        skillContainers.add(new SkillContainer(vOld, ContainerAction.DELETE));
                    }
                });
            } else {
                skillSumsOldMap.get(row).values().forEach(vOld ->
                        skillContainers.add(new SkillContainer(vOld, ContainerAction.DELETE))
                );
            }
        });

        skillSumsNewMap.keySet().forEach(row -> {
            if (skillSumsOldMap.containsKey(row)) {
                skillSumsNewMap.get(row).forEach((kNew, vNew) -> {
                    if (!skillSumsOldMap.get(row).containsKey(kNew)) {
                        skillContainers.add(new SkillContainer(vNew, ContainerAction.INSERT));
                    }
                });
            } else {
                skillSumsNewMap.get(row).values().forEach(vNew ->
                        skillContainers.add(new SkillContainer(vNew, ContainerAction.INSERT)));
            }
        });

        //process skillContainers
        skillContainers.forEach(skillContainer -> {
            switch (skillContainer.getAction()) {
                case DELETE:
                    skillSumRepository.delete(skillContainer.getSkillSum().getId());
                    break;
                case INSERT:
                    insertSkillSum(skillContainer.getSkillSum());
                    break;
                case UPDATE:
                    skillSumRepository.save(skillContainer.getSkillSum());
                    break;
                default:
            }
        });
    }

    private void insertSkillSum(SkillSum skillSum) {
        //additional checks due to editable typeahead
        if (skillSum.getSkill().isNew()) {
            Skill skill = skillRepository.findByName(skillSum.getSkill().getName());
            if (skill == null)
                skillSum.setSkill(skillRepository.save(new Skill(skillSum.getSkill().getName())));
            else
                skillSum.setSkill(skill);
        } else {
            skillSum.setSkill(skillRepository.findOne(skillSum.getSkill().getId()));
        }
        skillSumRepository.save(skillSum);
    }
}
