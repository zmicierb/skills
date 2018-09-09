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

//    @Autowired
//    private ProjectRepository projectRepository;


    @Autowired
    public CompanyServiceImpl(final CompanyRepository repository)
    {
        super(repository);
        this.companyRepository = repository;
    }


    @Override
    public Iterable<Company> findByPersonId(final String personId)
    {
        return companyRepository.findByPersonId(personId);
    }


//    @Transactional
//    @Override
//    public void remove(Long id)
//    {
//        projectRepository.findByCompanyId(id).forEach(
//            project -> projectRepository.remove(project.getId())
//        );
//    }
//
//
//    @Override
//    public Page<CompanyInfo> findByNameContainingIgnoreCase(String name, Pageable pageable)
//    {
//        return companyInfoRepository.findByNameContainingIgnoreCase(name, pageable);
//    }
//
//
//    @Transactional
//    @Override
//    public CompanyInfo update(Long id, CompanyInfo companyInfo)
//    {
//
//        CompanyInfo update = companyInfoRepository.findOne(id);
//        update.setName(companyInfo.getName());
//        update.setStartDate(companyInfo.getStartDate());
//        update.setEndDate(companyInfo.getEndDate());
//
//        return companyInfoRepository.save(update);
//    }
}
