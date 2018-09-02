package com.barysevich.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "experience")
public class Experience
{
    @Id
    final String id;

    final List<Project> projects;

    public Experience(final String id,
                      final List<Project> projects)
    {
        this.id = id;
        this.projects = projects;
    }

    public String getId() {
        return id;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
