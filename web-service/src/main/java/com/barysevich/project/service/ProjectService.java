package com.barysevich.project.service;


import com.barysevich.project.model.Project;


/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface ProjectService extends GenericService<Project, Long>
{

    void remove(Long id);


    Project update(Long id, Project project);


    Iterable<Project> findByPersonId(Long personId);


    Iterable<Project> findByDescriptionContainingIgnoreCaseForTest(String description);


    Iterable<Project> findByCompanyId(Long id);

}
