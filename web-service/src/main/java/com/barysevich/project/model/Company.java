package com.barysevich.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "companies")
public class Company
{
    @Id
    private final String id;

    private final String name;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final List<Project> projects;

    public Company(final String id,
                   final String name,
                   final LocalDate startDate,
                   final LocalDate endDate,
                   final List<Project> projects)
    {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projects = projects;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
