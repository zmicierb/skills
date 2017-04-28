package com.barysevich.project.service.impl;

import com.barysevich.project.model.Skill;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class SkillServiceImpl extends GenericServiceImpl<Skill, Long> implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository repository) {
        super(repository);
        this.skillRepository = repository;
    }

    @Override
    public Iterable<Skill> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return skillRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
