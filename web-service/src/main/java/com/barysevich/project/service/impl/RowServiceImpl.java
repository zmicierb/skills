//package com.barysevich.project.service.impl;
//
//
//import com.barysevich.project.model.Row;
//import com.barysevich.project.repository.RowRepository;
//import com.barysevich.project.service.RowService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//
///**
// * Created by BarysevichD on 2017-03-31.
// */
//@Service
//public class RowServiceImpl extends GenericServiceImpl<Row, Long> implements RowService
//{
//
//    @Autowired
//    private RowRepository rowRepository;
//
//
//    @Autowired
//    public RowServiceImpl(RowRepository repository)
//    {
//        super(repository);
//        this.rowRepository = repository;
//    }
//
//
//    @Override
//    public Iterable<Row> findByNameContainingIgnoreCase(String name, Pageable pageable)
//    {
//        return rowRepository.findByNameContainingIgnoreCase(name, pageable);
//    }
//
//}
