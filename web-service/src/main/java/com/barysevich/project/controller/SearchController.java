package com.barysevich.project.controller;


import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.request.SearchBySkillsRequest;
import com.barysevich.project.repository.SkillsAggregationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/search",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController
{
    private final SkillsAggregationRepository repository;


    @Autowired
    public SearchController(SkillsAggregationRepository repository)
    {
        this.repository = repository;
    }


    @PostMapping(value = "/find")
    public ResponseEntity<Response<Iterable<Person>>> searchPersonsBySkills(
            @RequestBody final SearchBySkillsRequest request)
    {
        return ResponseEntity.ok(Response.success(
                repository.findPersonIdsBySkills(
                        request.getSkills(),
                        request.getPage(),
                        request.getSize())));
    }
}
