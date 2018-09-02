package com.barysevich.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "persons")
public class Person
{
    @Id
    private final String id;

    private final String name;

    private final String email;

    private final String position;

    private final String department;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private final LocalDate birthDate;

    private final String skillsId;

    private final String companiesId;

    public Person(final String id,
                  final String name,
                  final String email,
                  final String position,
                  final String department,
                  final LocalDate birthDate,
                  final String skillsId,
                  final String companiesId)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.department = department;
        this.birthDate = birthDate;
        this.skillsId = skillsId;
        this.companiesId = companiesId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSkillsId() {
        return skillsId;
    }

    public String getCompaniesId() {
        return companiesId;
    }
}
