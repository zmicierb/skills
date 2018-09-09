package com.barysevich.project.service;

import com.barysevich.project.model.Company;

public interface CompanyService extends GenericService<Company, String>
{
    Iterable<Company> findByPersonId(String personId);

//    void remove(Long id);
//
//
//    Page<CompanyInfo> findByNameContainingIgnoreCase(String name, Pageable pageable);
//
//
//    CompanyInfo update(Long id, CompanyInfo companyInfo);
}
