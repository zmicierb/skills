package com.barysevich.project.controller;


import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Department;
import com.barysevich.project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by dima on 4/23/17.
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController
{

    @Autowired
    private DepartmentService departmentService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Department>>> findAll(Pageable pageable)
    {
        return ResponseEntity.ok(Response.success(departmentService.findAll(pageable)));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Department>> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(Response.success(departmentService.findOne(id)));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Department>> save(@RequestBody Department department)
    {
        return ResponseEntity.ok(Response.success(departmentService.save(department)));
    }


    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response<Iterable<Department>>> findByName(@PathVariable String name, Pageable pageable)
    {
        return ResponseEntity.ok(Response.success(departmentService.findByNameContainingIgnoreCase(name, pageable)));
    }

}
