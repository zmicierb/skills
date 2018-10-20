package com.barysevich.project.repository;

import com.barysevich.project.model.Skill;

import java.util.List;

public interface SkillBulkRepository
{
    void save(final List<Skill> skills);
}
