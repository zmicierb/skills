package com.barysevich.project.repository;

import com.barysevich.project.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, String>
{

}
