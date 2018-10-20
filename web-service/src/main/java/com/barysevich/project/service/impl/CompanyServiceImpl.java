package com.barysevich.project.service.impl;


import com.barysevich.project.model.Company;
import com.barysevich.project.repository.CompanyRepository;
import com.barysevich.project.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company, String> implements CompanyService
{
    final private CompanyRepository companyRepository;


    @Autowired
    public CompanyServiceImpl(final CompanyRepository repository)
    {
        super(repository);
        this.companyRepository = repository;
    }


    @Override
    public Iterable<Company> findByPersonId(final Long personId)
    {
        return companyRepository.findByPersonId(personId);
    }
}
