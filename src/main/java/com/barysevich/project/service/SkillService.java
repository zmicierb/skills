package com.barysevich.project.service;

import com.barysevich.project.model.Skill;
import org.springframework.data.domain.Pageable;

/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface SkillService extends GenericService<Skill, Long> {

    Iterable<Skill> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
