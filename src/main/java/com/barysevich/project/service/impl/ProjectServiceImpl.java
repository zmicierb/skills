package com.barysevich.project.service.impl;

import com.barysevich.project.model.Project;
import com.barysevich.project.repository.ProjectRepository;
import com.barysevich.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class ProjectServiceImpl extends GenericServiceImpl<Project, Long> implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void remove(Long id) {
        repository.remove(id);
    }
}
