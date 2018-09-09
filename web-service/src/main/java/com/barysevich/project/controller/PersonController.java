package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Person;
import com.barysevich.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController
{

    private final PersonService personService;

//    @Autowired
//    private ProjectService projectService;
//
//    @Autowired
//    private SkillSumService skillSumService;


    @Autowired
    public PersonController(final PersonService personService)
    {
        this.personService = personService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Person>>> findAll(final Pageable pageable)
    {
        return ResponseEntity.ok(Response.success(personService.findAll(pageable)));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Person>> getById(@PathVariable final String id)
    {
        return ResponseEntity.ok(Response.success(personService.findOne(id)));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Person>> saveOrUpdatePerson(@RequestBody final Person person)
    {
        return ResponseEntity.ok(Response.success(personService.save(person)));
    }
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Response> delete(@PathVariable Long id)
//    {
//        personService.remove(id);
//        return ResponseEntity.ok(Response.success());
//    }
//
//
//    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
//    public ResponseEntity<Response<Iterable<Person>>> findByName(@PathVariable String name, Pageable pageable)
//    {
//        return ResponseEntity.ok(Response.success(personService.findByNameContainingIgnoreCase(name, pageable)));
//    }
//
//
//    @RequestMapping(value = "/{id}/skills", method = RequestMethod.GET)
//    public ResponseEntity<Response<HashMap<Long, PersonSkillsDto>>> findSkillsById(@PathVariable Long id)
//    {
//        HashMap<Long, PersonSkillsDto> personSkillsDtoMap = (HashMap) personService.findSkillsById(id);
//        return ResponseEntity.ok(Response.success(personSkillsDtoMap));
//    }
//
//
//    @RequestMapping(value = "/{id}/skills", method = RequestMethod.PUT)
//    public ResponseEntity<Response<Person>> updatePersonSkills(@PathVariable Long id,
//                                                               @RequestBody Iterable<SkillSum> skillSums)
//    {
//        skillSumService.update(id, skillSums);
//        return ResponseEntity.ok(Response.success());
//    }
//
//
//    @RequestMapping(value = "/{id}/projects", method = RequestMethod.GET)
//    public ResponseEntity<Response<Iterable<Project>>> findProjectsById(@PathVariable Long id)
//    {
//        return ResponseEntity.ok(Response.success(projectService.findByPersonId(id)));
//    }

}
