package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Skills;
import com.barysevich.project.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skills")
public class SkillsController
{

    private SkillsService skillsService;

    @Autowired
    public SkillsController(final SkillsService skillsService)
    {
        this.skillsService = skillsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Skills>>> findAll(Pageable pageable)
    {
        return ResponseEntity.ok(Response.success(skillsService.findAll(pageable)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Skills>> getById(@PathVariable String id)
    {
        return ResponseEntity.ok(Response.success(skillsService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Skills>> save(@RequestBody Skills skills)
    {
        return ResponseEntity.ok(Response.success(skillsService.save(skills)));
    }

//    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
//    public ResponseEntity<Response<Iterable<Skills>>> findByName(@PathVariable String name, Pageable pageable)
//    {
//        return ResponseEntity.ok(Response.success(skillsService.findByNameContainingIgnoreCase(name, pageable)));
//    }
}
