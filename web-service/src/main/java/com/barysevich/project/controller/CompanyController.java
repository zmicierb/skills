package com.barysevich.project.controller;


import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Company;
import com.barysevich.project.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Response<Company>> saveOrUpdatePerson(@RequestBody final Company company)
    {
        return ResponseEntity.ok(Response.success(companyService.save(company)));
    }
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Response<CompanyInfo>> update(@PathVariable Long id, @RequestBody CompanyInfo company)
//    {
//        return ResponseEntity.ok(Response.success(companyInfoService.update(id, company)));
//    }
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Response> delete(@PathVariable Long id)
//    {
//        companyInfoService.remove(id);
//        return ResponseEntity.ok(Response.success());
//    }

}
