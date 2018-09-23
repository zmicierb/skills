package com.barysevich.project.service.impl;


import com.barysevich.project.service.GenericService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.Optional;


public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID>
{

    final protected PagingAndSortingRepository<T, ID> repository;


    public GenericServiceImpl(final PagingAndSortingRepository<T, ID> repository)
    {
        this.repository = repository;
    }


    protected PagingAndSortingRepository<T, ID> getRepository()
    {
        return repository;
    }


    @Override
    public T findOne(final ID id)
    {
        if (id == null)
            return null;
        return getRepository().findById(id).orElse(null);
    }


    @Override
    public Iterable<T> findAll(final Pageable pageable)
    {
        return getRepository().findAll(pageable);
    }


    @Override
//    @Transactional
    public void delete(final ID id)
    {
        Optional<T> e = getRepository().findById(id);

        e.ifPresent(value -> getRepository().delete(value));
    }


    @Override
//    @Transactional
    public T save(final T entity)
    {
        getRepository().save(entity);
        return entity;
    }

    @Override
    public void save(final Iterable<T> entities)
    {
        getRepository().saveAll(entities);
    }
}
