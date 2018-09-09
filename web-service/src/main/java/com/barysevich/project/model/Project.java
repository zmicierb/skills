package com.barysevich.project.model;

import java.util.List;

public class Project
{
    private final List<String> environment;

    private final String position;

    private final String description;

    private final String responsibility;

    private final String result;

    public Project(final List<String> environment,
                   final String position,
                   final String description,
                   final String responsibility,
                   final String result)
    {
        this.environment = environment;
        this.position = position;
        this.description = description;
        this.responsibility = responsibility;
        this.result = result;
    }

    public List<String> getEnvironment() {
        return environment;
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
