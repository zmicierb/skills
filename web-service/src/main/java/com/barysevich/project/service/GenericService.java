package com.barysevich.project.service;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface GenericService<T, ID extends Serializable>
{
    T findOne(ID id);

    Iterable<T> findAll(Pageable pageable);

    void delete(ID id);

    T save(T entities);
}
