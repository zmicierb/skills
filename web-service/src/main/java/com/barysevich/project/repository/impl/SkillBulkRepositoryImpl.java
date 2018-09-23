package com.barysevich.project.repository.impl;


import com.barysevich.project.model.Skill;
import com.barysevich.project.repository.SkillBulkRepository;
import com.mongodb.MongoBulkWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SkillBulkRepositoryImpl implements SkillBulkRepository
{
    private final MongoOperations template;


    @Autowired
    public SkillBulkRepositoryImpl(final MongoOperations template)
    {
        this.template = template;
    }

    @Override
    public void save(List<Skill> skills)
    {
        BulkOperations ops = template.bulkOps(BulkOperations.BulkMode.UNORDERED, Skill.class);
        ops.insert(skills);

        try
        {
            ops.execute();
        } catch (final MongoBulkWriteException e)
        {}
    }
}
