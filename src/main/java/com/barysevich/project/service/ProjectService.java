package com.barysevich.project.service;

import com.barysevich.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface ProjectService extends GenericService<Project, Long> {

    void remove(Long id);

    Page<Project> findByResponsibilityContainingIgnoreCase(String responsibility, Pageable pageable);

}
