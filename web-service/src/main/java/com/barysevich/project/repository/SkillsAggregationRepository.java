package com.barysevich.project.repository;

import java.util.List;

public interface SkillsAggregationRepository
{
    List<String> findPersonIdsBySkill(List<String> skills, long offset, long limit);
}
