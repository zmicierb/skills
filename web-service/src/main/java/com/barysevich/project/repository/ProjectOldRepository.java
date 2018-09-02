//package com.barysevich.project.repository;
//
//import com.barysevich.project.model.Project;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by BarysevichD on 2017-03-15.
// */
//@RepositoryRestResource(exported = false)
//public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE Project p SET p.deleted=1 WHERE p.id = :id ")
//    void remove(@Param("id") Long id);
//
//    @Query("SELECT p FROM Project p WHERE lower(p.responsibility) like lower(concat('%',concat(:responsibility, '%'))) and p.deleted<>1")
//    Page<Project> findByResponsibilityContainingIgnoreCase(@Param("responsibility") String responsibility, Pageable pageable);
//
//    @Query("SELECT p FROM Project p WHERE p.deleted<>1")
//    Page<Project> findAll(Pageable pageable);
//
//    @Query("SELECT p FROM Project p WHERE p.personId = :id and p.deleted<>1")
//    Iterable<Project> findByPersonId(@Param("id") Long id);
//
//    @Query("SELECT p FROM Project p WHERE p.companyInfo.id = :id and p.deleted<>1")
//    Iterable<Project> findByCompanyId(@Param("id") Long id);
//
//    @Query("SELECT p FROM Project p WHERE lower(p.description) like lower(concat('%',concat(:description, '%')))")
//    Iterable<Project> findByDescriptionContainingIgnoreCaseForTest(@Param("description") String description);
//
//    @Query("SELECT p.personId FROM Project p WHERE p.id = :projectId")
//    Long findPersonIdById(@Param("projectId") Long projectId);
//
//}
