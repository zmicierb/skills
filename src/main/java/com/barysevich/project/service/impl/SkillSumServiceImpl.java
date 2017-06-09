package com.barysevich.project.service.impl;

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
    public void update(Long personId, Iterable<SkillSum> skillSums) {
        skillSumRepository.deleteByPersonId(personId);
        Person person = personRepository.findOne(personId);
        skillSums.forEach(skillSum -> {
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

}
