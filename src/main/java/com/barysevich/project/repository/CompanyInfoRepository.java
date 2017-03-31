package com.barysevich.project.repository;

import com.barysevich.project.model.CompanyInfo;
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
public interface CompanyInfoRepository extends PagingAndSortingRepository<CompanyInfo, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE CompanyInfo c SET c.deleted=1 WHERE c.id = :id ")
    void remove(@Param("id") Long id);

}
