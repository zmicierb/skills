package com.barysevich.project.listener;


import com.barysevich.project.model.Skill;
import com.barysevich.project.model.Skills;
import com.barysevich.project.repository.SkillBulkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
class SkillsMongoListener extends AbstractMongoEventListener<Skills>
{
    private final SkillBulkRepository skillBulkRepository;


    @Autowired
    public SkillsMongoListener(final SkillBulkRepository skillBulkRepository)
    {
        this.skillBulkRepository = skillBulkRepository;
    }


    @Override
    public void onAfterSave(final AfterSaveEvent<Skills> event)
    {
        final Skills skills = event.getSource();

        System.out.println("received: " + skills);

        List<String> skillNames = new ArrayList<>();

        skillNames.addAll(skills.getLangs());
        skillNames.addAll(skills.getTechs());
        skillNames.addAll(skills.getDbs());
        skillNames.addAll(skills.getServers());
        skillNames.addAll(skills.getSystems());
        skillNames.addAll(skills.getOthers());

        skillBulkRepository.save(skillNames
                .stream()
                .map(skillName -> new Skill(null, skillName))
                .collect(Collectors.toList()));

    }
}