package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Skill;
import com.barysevich.project.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/api/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Skill>>> findAll(Pageable pageable) {
        return ResponseEntity.ok(Response.success(skillService.findAll(pageable)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Skill>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(skillService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Skill>> save(@RequestBody Skill skill) {
        return ResponseEntity.ok(Response.success(skillService.save(skill)));
    }

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Skill>>> findByName(@PathVariable String name, Pageable pageable) {
        return ResponseEntity.ok(Response.success(skillService.findByNameContainingIgnoreCase(name, pageable)));
    }

}
