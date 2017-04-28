package com.barysevich.project.repository;

import com.barysevich.project.model.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by dima on 4/23/17.
 */
@RepositoryRestResource(exported = false)
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    List<Department> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
