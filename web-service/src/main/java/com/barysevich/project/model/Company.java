package com.barysevich.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "companies")
public class Company
{
    @Id
    private final String id;

    private final String personId;

    private final String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private final LocalDate startDate;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private final LocalDate endDate;

    private final List<Project> projects;


    @JsonCreator
    public Company(@JsonProperty(value = "id") final String id,
                   @JsonProperty(value = "personId") final String personId,
                   @JsonProperty(value = "name") final String name,
                   @JsonProperty(value = "startDate") final LocalDate startDate,
                   @JsonProperty(value = "endDate") final LocalDate endDate,
                   @JsonProperty(value = "projects") final List<Project> projects)
    {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projects = projects;
    }


    @JsonProperty(value = "id")
    public String getId()
    {
        return id;
    }

    @JsonProperty(value = "personId")
    public String getPersonId()
    {
        return personId;
    }

    @JsonProperty(value = "name")
    public String getName()
    {
        return name;
    }

    @JsonProperty(value = "startDate")
    public LocalDate getStartDate()
    {
        return startDate;
    }

    @JsonProperty(value = "endDate")
    public LocalDate getEndDate()
    {
        return endDate;
    }

    @JsonProperty(value = "projects")
    public List<Project> getProjects()
    {
        return projects;
    }
}
