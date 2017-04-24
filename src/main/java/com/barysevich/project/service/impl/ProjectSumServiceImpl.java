package com.barysevich.project.service.impl;

import com.barysevich.project.model.ProjectSum;
import com.barysevich.project.repository.ProjectSumRepository;
import com.barysevich.project.service.ProjectSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class ProjectSumServiceImpl extends GenericServiceImpl<ProjectSum, Long> implements ProjectSumService {

    @Autowired
    private ProjectSumRepository projectSumRepository;

    @Autowired
    public ProjectSumServiceImpl(ProjectSumRepository repository) {
        super(repository);
        this.projectSumRepository = repository;
    }

}
