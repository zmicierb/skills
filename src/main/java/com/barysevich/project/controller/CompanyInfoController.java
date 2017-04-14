package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/app/api/company")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<CompanyInfo>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(companyInfoService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<CompanyInfo>> save(@RequestBody CompanyInfo company) {
        return ResponseEntity.ok(Response.success(companyInfoService.save(company)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response<CompanyInfo>> update(@PathVariable Long id, @RequestBody CompanyInfo company) {
        CompanyInfo update = companyInfoService.findOne(id);
        update.setPosition(company.getPosition());
        update.setName(company.getName());
        update.setStartDate(company.getStartDate());
        update.setEndDate(company.getEndDate());
        return ResponseEntity.ok(Response.success(companyInfoService.save(update)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        companyInfoService.remove(id);
        return ResponseEntity.ok(Response.success());
    }

}
