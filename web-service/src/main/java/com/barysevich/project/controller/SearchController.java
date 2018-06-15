package com.barysevich.project.controller;


import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Person;
import com.barysevich.project.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by BarysevichD on 2017-07-06.
 */
@RestController
@RequestMapping("/api/search")
public class SearchController
{

    @Autowired
    private SearchService searchService;


    @RequestMapping(value = "/find/{query}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Person>>> extendedSearch(@PathVariable String query, Pageable pageable)
    {
        return ResponseEntity.ok(Response.success(searchService.extendedSearch(query, pageable)));
    }

}
