package com.barysevich.project.service.impl;


import com.barysevich.project.service.GenericService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


/**
 * Created by BarysevichD on 2017-03-31.
 */
public class GenericServiceImpl<T extends Persistable<ID>, ID extends Serializable> implements GenericService<T, ID>
{

    protected PagingAndSortingRepository<T, ID> repository;


    public GenericServiceImpl(PagingAndSortingRepository<T, ID> repository)
    {
        this.repository = repository;
    }


    protected PagingAndSortingRepository<T, ID> getRepository()
    {
        return repository;
    }


    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id)
    {
        if (id == null)
            return null;
        return getRepository().findOne(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<T> findAll(Pageable pageable)
    {
        return getRepository().findAll(pageable);
    }


    @Override
    @Transactional
    public void delete(ID id)
    {
        T e = getRepository().findOne(id);
        if (e != null)
            getRepository().delete(e);
    }


    @Override
    @Transactional
    public T save(T entity)
    {
        getRepository().save(entity);
        return entity;
    }
}
