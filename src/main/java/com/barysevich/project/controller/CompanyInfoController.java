package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.repository.CompanyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/api")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<CompanyInfo>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(companyInfoRepository.findOne(id)));
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public ResponseEntity<Response<CompanyInfo>> save(@RequestBody CompanyInfo company) {
        return ResponseEntity.ok(Response.success(companyInfoRepository.save(company)));
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response<CompanyInfo>> update(@PathVariable Long id, @RequestBody CompanyInfo company) {
        CompanyInfo update = companyInfoRepository.findOne(id);
        update.setPosition(company.getPosition());
        update.setName(company.getName());
        update.setStartDate(company.getStartDate());
        update.setEndDate(company.getEndDate());
        return ResponseEntity.ok(Response.success(companyInfoRepository.save(update)));
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        companyInfoRepository.remove(id);
        return ResponseEntity.ok(Response.success());
    }

}
