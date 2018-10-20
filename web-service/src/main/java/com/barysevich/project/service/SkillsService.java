package com.barysevich.project.service;

import com.barysevich.project.model.Skills;

public interface SkillsService extends GenericService<Skills, String>
{
    Skills findByPersonId(final Long personId);
}
