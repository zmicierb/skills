package com.barysevich.project.service;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface GenericService<T extends Persistable<ID>, ID extends Serializable> {

    T findOne(ID id);

    Iterable<T> findAll();

    void delete(ID id);

    T save(T entities);

}
