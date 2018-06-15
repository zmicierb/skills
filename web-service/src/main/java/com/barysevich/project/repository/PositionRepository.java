package com.barysevich.project.repository;

import com.barysevich.project.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface PositionRepository extends PagingAndSortingRepository<Position, Long> {

    Page<Position> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Position findByName(String name);

}
