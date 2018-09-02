package com.barysevich.project.service.impl;

import com.barysevich.project.service.GenericService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID>
{

    protected PagingAndSortingRepository<T, ID> repository;


    public GenericServiceImpl(PagingAndSortingRepository<T, ID> repository) {
        this.repository = repository;
    }


    protected PagingAndSortingRepository<T, ID> getRepository() {
        return repository;
    }

    @Override
    public T findOne(ID id) {
        if (id == null)
            return null;
        return getRepository().findOne(id);
    }


    @Override
    public Iterable<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }


    @Override
//    @Transactional
    public void delete(ID id) {
        T e = getRepository().findOne(id);
        if (e != null)
            getRepository().delete(e);
    }


    @Override
//    @Transactional
    public T save(T entity) {
        getRepository().save(entity);
        return entity;
    }
}
