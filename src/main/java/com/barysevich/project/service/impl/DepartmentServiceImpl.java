package com.barysevich.project.service.impl;

import com.barysevich.project.model.Department;
import com.barysevich.project.repository.DepartmentRepository;
import com.barysevich.project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Iterable<Department> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return departmentRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    @Transactional
    public Department save(Department departmentNew) {
        //additional checks due to editable typeahead
        if (departmentNew.isNew()) {
            Department departmentOld = departmentRepository.findByName(departmentNew.getName());
            if (departmentOld == null)
                return departmentRepository.save(new Department(departmentNew.getName()));
            else
                return departmentOld;
        } else {
            return departmentRepository.findOne(departmentNew.getId());
        }
    }

}
