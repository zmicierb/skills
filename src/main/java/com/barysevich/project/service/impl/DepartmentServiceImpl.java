package com.barysevich.project.service.impl;

import com.barysevich.project.model.Department;
import com.barysevich.project.repository.DepartmentRepository;
import com.barysevich.project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dima on 4/23/17.
 */
@Service
public class DepartmentServiceImpl extends GenericServiceImpl<Department, Long> implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        super(repository);
        this.departmentRepository = repository;
    }

    @Override
    public Iterable<Department> findByNameContainingIgnoreCase(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }

}
