package com.barysevich.project.service.impl;

import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.repository.CompanyInfoRepository;
import com.barysevich.project.repository.ProjectRepository;
import com.barysevich.project.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class CompanyInfoServiceImpl extends GenericServiceImpl<CompanyInfo, Long> implements CompanyInfoService {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    public CompanyInfoServiceImpl(CompanyInfoRepository repository) {
        super(repository);
        this.companyInfoRepository = repository;
    }

    @Transactional
    @Override
    public void remove(Long id) {
        projectRepository.findByCompanyId(id).forEach(
                project -> projectRepository.remove(project.getId())
        );
    }

    @Override
    public Page<CompanyInfo> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return companyInfoRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Transactional
    @Override
    public CompanyInfo update(Long id, CompanyInfo companyInfo) {

        CompanyInfo update = companyInfoRepository.findOne(id);
        update.setName(companyInfo.getName());
        update.setStartDate(companyInfo.getStartDate());
        update.setEndDate(companyInfo.getEndDate());

        return companyInfoRepository.save(update);
    }

}
