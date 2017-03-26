package com.barysevich.project.repository;

import com.barysevich.project.model.CompanyInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface CompanyInfoRepository extends PagingAndSortingRepository<CompanyInfo, Long> {

    List<CompanyInfo> findByName(String name);

}
