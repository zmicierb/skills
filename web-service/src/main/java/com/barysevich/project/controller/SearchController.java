package com.barysevich.project.controller;


import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.repository.SkillsAggregationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/api/search")
public class SearchController
{
    private final SkillsAggregationRepository repository;


    @Autowired
    public SearchController(SkillsAggregationRepository repository)
    {
        this.repository = repository;
    }


    @RequestMapping(value = "/find/{query}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<String>>> searchPersonsBySkills(@PathVariable String query, Pageable pageable)
    {
        return ResponseEntity.ok(
                Response.success(
                        repository.findPersonIdsBySkill(
                                Arrays.asList(query),
                                pageable.getOffset(),
                                pageable.getPageSize()
                        )));
    }

}
