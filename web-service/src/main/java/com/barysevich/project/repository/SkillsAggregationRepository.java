package com.barysevich.project.repository;

import com.barysevich.project.model.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SkillsAggregationRepository
{
    Page<Person> findPersonIdsBySkills(final List<String> skills, final int page, final int size);
}
