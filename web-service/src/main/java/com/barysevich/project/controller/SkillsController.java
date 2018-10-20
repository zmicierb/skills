package com.barysevich.project.controller;


import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Skills;
import com.barysevich.project.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/skills/person")
public class SkillsController
{

    private final SkillsService skillsService;


    @Autowired
    public SkillsController(final SkillsService skillsService)
    {
        this.skillsService = skillsService;
    }


    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public ResponseEntity<Response<Skills>> getByPersonId(@PathVariable final Long personId)
    {
        return ResponseEntity.ok(Response.success(skillsService.findByPersonId(personId)));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Skills>> saveOrUpdatePerson(@RequestBody final Skills skills)
    {
        return ResponseEntity.ok(Response.success(skillsService.save(skills)));
    }
}
