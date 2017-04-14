package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Person;
import com.barysevich.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RestController
@RequestMapping("/app/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Person>>> findAll() {
        return ResponseEntity.ok(Response.success(personService.findAll()));
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

    @RequestMapping(value = "/find/name={name}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Person>>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(Response.success(personService.findByNameContainingIgnoreCase(name)));
    }

}
