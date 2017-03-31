package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Project;
import com.barysevich.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Project>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.success(projectRepository.findOne(id)));
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseEntity<Response<Project>> save(@RequestBody Project project) {
        return ResponseEntity.ok(Response.success(projectRepository.save(project)));
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response<Project>> update(@PathVariable Long id, @RequestBody Project project) {
        Project update = projectRepository.findOne(id);
        update.setPosition(project.getPosition());
        update.setResponsibility(project.getResponsibility());
        update.setResult(project.getResult());
        update.setDescription(project.getDescription());
        return ResponseEntity.ok(Response.success(projectRepository.save(update)));
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        projectRepository.remove(id);
        return ResponseEntity.ok(Response.success());
    }

}
