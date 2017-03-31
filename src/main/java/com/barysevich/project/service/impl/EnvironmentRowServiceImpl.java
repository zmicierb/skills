package com.barysevich.project.service.impl;

import com.barysevich.project.model.EnvironmentRow;
import com.barysevich.project.repository.EnvironmentRowRepository;
import com.barysevich.project.service.EnvironmentRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class EnvironmentRowServiceImpl extends GenericServiceImpl<EnvironmentRow, Long> implements EnvironmentRowService {

    @Autowired
    private EnvironmentRowRepository repository;

    @Autowired
    public EnvironmentRowServiceImpl(EnvironmentRowRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
