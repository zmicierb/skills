//package com.barysevich.project.repository;
//
//import com.barysevich.project.model.EnvironmentSkill;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
///**
// * Created by BarysevichD on 2017-03-15.
// */
//@RepositoryRestResource(exported = false)
//public interface EnvironmentSkillRepository extends PagingAndSortingRepository<EnvironmentSkill, Long> {
//
//    @Query("SELECT p.personId " +
//            "FROM EnvironmentSkill es" +
//            "  LEFT JOIN Skill s ON es.skillId = s.id " +
//            "  LEFT JOIN Project p ON es.projectId = p.id " +
//            "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',CONCAT(:name, '%')))")
//    Iterable<Long> findPersonIdBySkillNameContainingIgnoreCase(@Param("name") String name);
//
//    /**
//     * @return an list of array of objects where element with index 0 - EnvironmentSkill object,
//     * element with index 1 - Long object
//     */
//    @Query("SELECT es, p.personId " +
//            "FROM EnvironmentSkill es" +
//            "  LEFT JOIN Skill s ON es.skillId = s.id " +
//            "  LEFT JOIN Project p ON es.projectId = p.id " +
//            "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',CONCAT(:name, '%')))")
//    Iterable<EnvironmentSkill> findWithPersonIdBySkillNameContainingIgnoreCase(@Param("name") String name);
//
//}
