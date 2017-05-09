package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.PersonSkillsDto;
import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.controller.dto.SkillDto;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.ProjectSum;
import com.barysevich.project.service.PersonService;
import com.barysevich.project.service.ProjectSumService;
import com.barysevich.project.service.SkillSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ProjectSumService projectSumService;

    @Autowired
    private SkillSumService skillSumService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Person>>> findAll(Pageable pageable) {
        return ResponseEntity.ok(Response.success(personService.findAll(pageable)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Person>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(personService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Person>> save(@RequestBody Person person) {
        return ResponseEntity.ok(Response.success(personService.save(person)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response<Person>> update(@PathVariable Long id, @RequestBody Person person) {
        Person update = personService.findOne(id);
        update.setName(person.getName());
        return ResponseEntity.ok(Response.success(personService.save(update)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        personService.remove(id);
        return ResponseEntity.ok(Response.success());
    }

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Person>>> findByName(@PathVariable String name, Pageable pageable) {
        return ResponseEntity.ok(Response.success(personService.findByNameContainingIgnoreCase(name, pageable)));
    }

    @RequestMapping(value = "/{id}/skills")
    public ResponseEntity<Response<HashMap<Long, PersonSkillsDto>>> findSkillsById(@PathVariable Long id) {
        HashMap<Long, PersonSkillsDto> personSkillsDto = new HashMap<>();
        skillSumService.findByPersonId(id).forEach(a -> {
            if (personSkillsDto.containsKey(a.getRowId())) {
                personSkillsDto.get(a.getRowId()).getSkills()
                        .add(new SkillDto(a.getSkillId(), a.getSkill().getName(), a.getWeight()));
            } else {
                personSkillsDto.put(a.getRowId(), new PersonSkillsDto(a));
            }
        });
        return ResponseEntity.ok(Response.success(personSkillsDto));
    }

    @RequestMapping(value = "/{id}/projects")
    public ResponseEntity<Response<Iterable<ProjectSum>>> findProjectsById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(projectSumService.findByPersonId(id)));
    }

}
