package com.barysevich.project.repository;

import com.barysevich.project.model.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Project p SET p.deleted=1 WHERE p.id = :id ")
    void remove(@Param("id") Long id);

}
