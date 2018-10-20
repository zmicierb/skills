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


    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public ResponseEntity<Response<Person>> getById(@PathVariable final Long personId)
    {
        return ResponseEntity.ok(Response.success(personService.findByPersonId(personId)));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Person>> saveOrUpdatePerson(@RequestBody final Person person)
    {
        return ResponseEntity.ok(Response.success(personService.update(person)));
    }
}
