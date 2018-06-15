package com.barysevich.project.service;


import com.barysevich.project.model.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface CompanyInfoService extends GenericService<CompanyInfo, Long>
{

    void remove(Long id);


    Page<CompanyInfo> findByNameContainingIgnoreCase(String name, Pageable pageable);


    CompanyInfo update(Long id, CompanyInfo companyInfo);
}
