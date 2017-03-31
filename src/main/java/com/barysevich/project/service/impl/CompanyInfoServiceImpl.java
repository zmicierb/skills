package com.barysevich.project.service.impl;

import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.repository.CompanyInfoRepository;
import com.barysevich.project.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class CompanyInfoServiceImpl extends GenericServiceImpl<CompanyInfo, Long> implements CompanyInfoService {

    @Autowired
    private CompanyInfoRepository repository;

    @Autowired
    public CompanyInfoServiceImpl(CompanyInfoRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
