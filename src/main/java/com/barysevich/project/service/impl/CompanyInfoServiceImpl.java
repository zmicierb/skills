package com.barysevich.project.service.impl;

import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.repository.CompanyInfoRepository;
import com.barysevich.project.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class CompanyInfoServiceImpl extends GenericServiceImpl<CompanyInfo, Long> implements CompanyInfoService {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    public CompanyInfoServiceImpl(CompanyInfoRepository repository) {
        super(repository);
        this.companyInfoRepository = repository;
    }

    @Override
    public void remove(Long id) {
        companyInfoRepository.remove(id);
    }

    @Override
    public Page<CompanyInfo> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return companyInfoRepository.findByNameContainingIgnoreCase(name, pageable);
    }

}
