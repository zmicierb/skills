package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Skill;
import com.barysevich.project.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill")
public class SkillController
{

    private final SkillService skillService;

    @Autowired
    public SkillController(final SkillService skillService)
    {
        this.skillService = skillService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Skill>>> findAll(final Pageable pageable)
    {
        return ResponseEntity.ok(Response.success(skillService.findAll(pageable)));
    }


    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Skill>>> findByName(@PathVariable final String name,
                                                                final Pageable pageable)
    {
        return ResponseEntity.ok(Response.success(skillService.findByNameRegEx(name, pageable)));
    }
}
