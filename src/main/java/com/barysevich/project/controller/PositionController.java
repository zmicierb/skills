package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Position;
import com.barysevich.project.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Position>>> findAll() {
        return ResponseEntity.ok(Response.success(positionService.findAll()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Position>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(positionService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Position>> save(@RequestBody Position position) {
        return ResponseEntity.ok(Response.success(positionService.save(position)));
    }

    @RequestMapping(value = "/find/name={name}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Position>>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(Response.success(positionService.findByNameContainingIgnoreCase(name)));
    }

}
