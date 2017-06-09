package com.barysevich.project.service.impl;

import com.barysevich.project.controller.dto.SkillDto;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Skill;
import com.barysevich.project.model.SkillSum;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.RowRepository;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.repository.SkillSumRepository;
import com.barysevich.project.service.SkillSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

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
    private PersonRepository personRepository;

    @Autowired
    public SkillSumServiceImpl(SkillSumRepository repository) {
        super(repository);
        this.skillSumRepository = repository;
    }

    public Iterable<SkillSum> findByPersonId(Long id) {
        return skillSumRepository.findByPersonId(id);
    }

    @Transactional
    public void update(Long personId, Iterable<SkillSum> skillSumsNew) {

        Person person = personRepository.findOne(personId);

//        Iterable<SkillSum> skillSumsOld = skillSumRepository.findByPersonId(personId);
//        Map<Row, TreeSet<SkillDto>> skillSumsOldMap = new HashMap<>();
//        Map<Row, TreeSet<SkillDto>> skillSumsNewMap = new HashMap<>();
//
//        skillSumsOld.forEach(a -> {
//            if (!skillSumsOldMap.containsKey(a.getRow())) {
//                skillSumsOldMap.put(a.getRow(), new TreeSet<>(new SkillByWeightComp()));
//            }
//            skillSumsOldMap.get(a.getRow())
//                    .add(new SkillDto(a.getSkillId(), a.getSkill().getName(), a.getWeight()));
//        });
//
//        skillSumsNew.forEach(a -> {
//            if (!skillSumsNewMap.containsKey(a.getRow())) {
//                skillSumsNewMap.put(a.getRow(), new TreeSet<>(new SkillByWeightComp()));
//            }
//            skillSumsNewMap.get(a.getRow())
//                    .add(new SkillDto(a.getSkillId(), a.getSkill().getName(), a.getWeight()));
//        });
//
//        ArrayList<SkillSumContainer> skillSumContainers = new ArrayList<>();
//
//        skillSumsOldMap.keySet().forEach(row -> {
//            if (skillSumsNewMap.containsKey(row)){
//
//            } else {
//                skillSumsOldMap.get(row).forEach(skillDto -> {
//                    skillSumContainers.add(new SkillSumContainer(person, row, skillDto, ContainerAction.DELETE));
//                });
//            }
//        });

        skillSumRepository.deleteByPersonId(personId);
        skillSumsNew.forEach(skillSum -> {
            skillSum.setPerson(person);
            //additional checks for butch processing
            if (skillSum.getSkill().isNew()) {
                Skill skill = skillRepository.findByName(skillSum.getSkill().getName());
                if (skill == null)
                    skillSum.setSkill(skillRepository.save(new Skill(skillSum.getSkill().getName())));
                else
                    skillSum.setSkill(skill);
            } else {
                skillSum.setSkill(skillRepository.findOne(skillSum.getSkill().getId()));
            }
            skillSum.setRow(rowRepository.findOne(skillSum.getRow().getId()));
            save(skillSum);
        });
    }

    private class SkillByWeightComp implements Comparator<SkillDto> {
        @Override
        public int compare(SkillDto s1, SkillDto s2) {
            return s1.getWeight().compareTo(s2.getWeight());
        }
    }
}
