package com.barysevich.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project
{
    @Id
    private final String id;

    private final String[] skills;

    private final String position;

    private final String description;

    private final String responsibility;

    private final String result;

    public Project(final String id,
                   final String[] skills,
                   final String position,
                   final String description,
                   final String responsibility,
                   final String result)
    {
        this.id = id;
        this.skills = skills;
        this.position = position;
        this.description = description;
        this.responsibility = responsibility;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public String[] getSkills() {
        return skills;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public String getResult() {
        return result;
    }
}
