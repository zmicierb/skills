package com.barysevich.project.service;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface GenericService<T, ID extends Serializable>
{
    T findOne(final ID id);

    Iterable<T> findAll(final Pageable pageable);

    void delete(final ID id);

    T save(final T entity);

    void save(final Iterable<T> entities);
}
