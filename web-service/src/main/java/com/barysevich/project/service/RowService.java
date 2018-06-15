package com.barysevich.project.service;


import com.barysevich.project.model.Row;
import org.springframework.data.domain.Pageable;


/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface RowService extends GenericService<Row, Long>
{

    Iterable<Row> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
