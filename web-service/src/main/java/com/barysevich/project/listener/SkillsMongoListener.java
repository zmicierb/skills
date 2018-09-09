package com.barysevich.project.listener;

import com.barysevich.project.model.Skills;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
class SkillsMongoListener extends AbstractMongoEventListener<Skills>
{
    @Override
    public void onAfterSave(final AfterSaveEvent<Skills> event)
    {
        System.out.println("received: " + event.getDocument());
    }
}