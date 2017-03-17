package by.barysevich.project.controller;

import by.barysevich.project.controller.dto.Response;
import by.barysevich.project.model.Person;
import by.barysevich.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Person>>> findAll() {
        return ResponseEntity.ok(Response.success(personRepository.findAll()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Person>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(personRepository.findOne(id)));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Response<Person>> save(@RequestBody Person person) {
        return ResponseEntity.ok(Response.success(personRepository.save(person)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response<Person>> update(@PathVariable Long id, @RequestBody Person person) {
        Person update = personRepository.findOne(id);
        update.setName(person.getName());
        return ResponseEntity.ok(Response.success(personRepository.save(update)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        personRepository.delete(id);
        return ResponseEntity.ok(Response.success());
    }

}
