package com.barysevich.project.repository;

import com.barysevich.project.model.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface CompanyInfoRepository extends PagingAndSortingRepository<CompanyInfo, Long> {

    Page<CompanyInfo> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

}
