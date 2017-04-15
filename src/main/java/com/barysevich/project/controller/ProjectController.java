package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Project;
import com.barysevich.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Project>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(projectService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response<Project>> save(@RequestBody Project project) {
        return ResponseEntity.ok(Response.success(projectService.save(project)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response<Project>> update(@PathVariable Long id, @RequestBody Project project) {
        Project update = projectService.findOne(id);
        update.setPosition(project.getPosition());
        update.setResponsibility(project.getResponsibility());
        update.setResult(project.getResult());
        update.setDescription(project.getDescription());
        return ResponseEntity.ok(Response.success(projectService.save(update)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        projectService.remove(id);
        return ResponseEntity.ok(Response.success());
    }

}
