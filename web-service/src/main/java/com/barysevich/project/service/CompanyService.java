package com.barysevich.project.service;

import com.barysevich.project.model.Company;

public interface CompanyService extends GenericService<Company, String>
{
    Iterable<Company> findByPersonId(final Long personId);
}
