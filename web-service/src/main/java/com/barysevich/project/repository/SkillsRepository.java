package com.barysevich.project.repository;

import com.barysevich.project.model.Skills;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillsRepository extends PagingAndSortingRepository<Skills, String>
{
    Skills findByPersonId(final Long personId);
}
