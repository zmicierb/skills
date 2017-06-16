package com.barysevich.project.service.impl;

import com.barysevich.project.model.EnvironmentSkill;
import com.barysevich.project.repository.EnvironmentSkillRepository;
import com.barysevich.project.service.EnvironmentRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class EnvironmentRowServiceImpl extends GenericServiceImpl<EnvironmentSkill, Long> implements EnvironmentRowService {

    @Autowired
    private EnvironmentSkillRepository environmentSkillRepository;

    @Autowired
    public EnvironmentRowServiceImpl(EnvironmentSkillRepository repository) {
        super(repository);
        this.environmentSkillRepository = repository;
    }

}
