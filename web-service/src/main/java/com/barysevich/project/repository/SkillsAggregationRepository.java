package com.barysevich.project.repository;


import com.barysevich.project.model.Person;

import java.util.List;


public interface SkillsAggregationRepository
{
    List<Person> findPersonIdsBySkills(List<String> skills, long offset, long limit);
}
