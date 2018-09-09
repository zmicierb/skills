package com.barysevich.project.service.impl;

import com.barysevich.project.model.Skills;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.repository.SkillsRepository;
import com.barysevich.project.service.SkillsService;
import com.google.common.collect.ObjectArrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SkillsServiceImpl extends GenericServiceImpl<Skills, String> implements SkillsService
{

    final private SkillsRepository skillsRepository;

    @Autowired
    public SkillsServiceImpl(final SkillsRepository skillsRepository)
    {
        super(skillsRepository);
        this.skillsRepository = skillsRepository;
    }

    @Override
    public Skills findByPersonId(final String personId)
    {
        return skillsRepository.findByPersonId(personId);
    }

//
//    @Override
//    @Transactional
//    public Skill save(Skill skillNew)
//    {
//        //additional checks due to editable typeahead
//        if (skillNew.isNew())
//        {
//            Skill skillOld = skillRepository.findByName(skillNew.getName());
//            if (skillOld == null)
//                return skillRepository.save(new Skill(skillNew.getName()));
//            else
//                return skillOld;
//        } else
//        {
//            return skillRepository.findOne(skillNew.getId());
//        }
//    }
}
