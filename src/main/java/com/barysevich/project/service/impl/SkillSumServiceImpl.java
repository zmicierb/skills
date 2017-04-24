package com.barysevich.project.service.impl;

import com.barysevich.project.model.SkillSum;
import com.barysevich.project.repository.SkillSumRepository;
import com.barysevich.project.service.SkillSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class SkillSumServiceImpl extends GenericServiceImpl<SkillSum, Long> implements SkillSumService {

    @Autowired
    private SkillSumRepository skillSumRepository;

    @Autowired
    public SkillSumServiceImpl(SkillSumRepository repository) {
        super(repository);
        this.skillSumRepository = repository;
    }

}
