package com.barysevich.project.service.impl;

import com.barysevich.project.model.Row;
import com.barysevich.project.repository.RowRepository;
import com.barysevich.project.service.RowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class RowServiceImpl extends GenericServiceImpl<Row, Long> implements RowService {

    @Autowired
    private RowRepository repository;

    @Autowired
    public RowServiceImpl(RowRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
