package com.barysevich.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Project
{
    private final List<String> environment;

    private final String position;

    private final String description;

    private final String responsibility;

    private final String result;


    @JsonCreator
    public Project(@JsonProperty(value = "environment") final List<String> environment,
                   @JsonProperty(value = "position") final String position,
                   @JsonProperty(value = "description") final String description,
                   @JsonProperty(value = "responsibility") final String responsibility,
                   @JsonProperty(value = "result") final String result)
    {
        this.environment = environment;
        this.position = position;
        this.description = description;
        this.responsibility = responsibility;
        this.result = result;
    }


    @JsonProperty(value = "environment")
    public List<String> getEnvironment()
    {
        return environment;
    }


    @JsonProperty(value = "position")
    public String getPosition()
    {
        return position;
    }


    @JsonProperty(value = "description")
    public String getDescription()
    {
        return description;
    }


    @JsonProperty(value = "responsibility")
    public String getResponsibility()
    {
        return responsibility;
    }


    @JsonProperty(value = "result")
    public String getResult()
    {
        return result;
    }
}
