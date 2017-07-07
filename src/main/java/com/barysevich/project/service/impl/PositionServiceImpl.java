package com.barysevich.project.service.impl;

import com.barysevich.project.model.Position;
import com.barysevich.project.repository.PositionRepository;
import com.barysevich.project.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Position> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return positionRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    @Transactional
    public Position save(Position positionNew) {
        //additional checks due to editable typeahead
        if (positionNew.isNew()) {
            Position positionOld = positionRepository.findByName(positionNew.getName());
            if (positionOld == null)
                return positionRepository.save(new Position(positionNew.getName()));
            else
                return positionOld;
        } else {
            return positionRepository.findOne(positionNew.getId());
        }
    }
}
