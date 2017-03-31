package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Position;
import com.barysevich.project.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/api")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Position>>> findAll() {
        return ResponseEntity.ok(Response.success(positionRepository.findAll()));
    }

    @RequestMapping(value = "/position/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Position>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(positionRepository.findOne(id)));
    }

    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public ResponseEntity<Response<Position>> save(@RequestBody Position position) {
        return ResponseEntity.ok(Response.success(positionRepository.save(position)));
    }

    @RequestMapping(value = "/position/find/name={name}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Position>>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(Response.success(positionRepository.findByNameContainingIgnoreCase(name)));
    }

}
