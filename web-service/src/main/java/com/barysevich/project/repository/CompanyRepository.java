package com.barysevich.project.repository;

import com.barysevich.project.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompanyRepository extends PagingAndSortingRepository<Company, String>
{
    List<Company> findByPersonId(String personId);
}
