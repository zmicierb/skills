package com.barysevich.project.service.impl;

import com.barysevich.project.model.Position;
import com.barysevich.project.repository.PositionRepository;
import com.barysevich.project.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class PositionServiceImpl extends GenericServiceImpl<Position, Long> implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository repository) {
        super(repository);
        this.positionRepository = repository;
    }

    @Override
    public Iterable<Position> findByNameContainingIgnoreCase(String name) {
        return positionRepository.findByNameContainingIgnoreCase(name);
    }
}
