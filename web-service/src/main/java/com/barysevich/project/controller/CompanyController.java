package com.barysevich.project.controller;


import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Company;
import com.barysevich.project.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/person")
public class CompanyController
{
    private final CompanyService companyService;


    @Autowired
    public CompanyController(final CompanyService companyService)
    {
        this.companyService = companyService;
    }


    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Company>>> getByPersonId(@PathVariable final String personId)
    {
        return ResponseEntity.ok(Response.success(companyService.findByPersonId(personId)));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Company>> saveOrUpdateCompany(@RequestBody final List<Company> companies)
    {
        companyService.save(companies);
        return ResponseEntity.ok(Response.success());
    }


    @RequestMapping(value = "/{companyId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final String companyId)
    {
        companyService.delete(companyId);
        return ResponseEntity.ok(Response.success());
    }
}
