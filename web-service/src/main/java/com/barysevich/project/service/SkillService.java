package com.barysevich.project.service;

import com.barysevich.project.model.Skill;
import org.springframework.data.domain.Pageable;

public interface SkillService extends GenericService<Skill, String>
{
    Iterable<Skill> findByNameRegEx(String name, Pageable pageable);

//    Iterable<Skill> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
