package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Row;
import com.barysevich.project.repository.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/api")
public class RowController {

    @Autowired
    private RowRepository rowRepository;

    @RequestMapping(value = "/rows", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Row>>> findAll() {
        return ResponseEntity.ok(Response.success(rowRepository.findAll()));
    }

    @RequestMapping(value = "/row/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Row>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(rowRepository.findOne(id)));
    }

    @RequestMapping(value = "/row", method = RequestMethod.POST)
    public ResponseEntity<Response<Row>> save(@RequestBody Row row) {
        return ResponseEntity.ok(Response.success(rowRepository.save(row)));
    }

}
