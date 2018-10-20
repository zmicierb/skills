package com.barysevich.project.service.impl;


import com.barysevich.project.model.Skills;
import com.barysevich.project.repository.SkillsRepository;
import com.barysevich.project.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public Skills findByPersonId(final Long personId)
    {
        return skillsRepository.findByPersonId(personId);
    }
}
