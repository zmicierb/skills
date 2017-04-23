package com.barysevich.project.service;

import com.barysevich.project.model.Department;

/**
 * Created by dima on 4/23/17.
 */
public interface DepartmentService extends GenericService<Department, Long> {

    Iterable<Department> findByNameContainingIgnoreCase(String name);

}
