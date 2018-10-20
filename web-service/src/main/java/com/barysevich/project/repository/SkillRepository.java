package com.barysevich.project.repository;

import com.barysevich.project.model.Skill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SkillRepository extends PagingAndSortingRepository<Skill, String>
{
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<Skill> findByNameRegEx(final String name, final Pageable pageable);
}
