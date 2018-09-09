package com.barysevich.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public Person(@JsonProperty(value = "id") final String id,
                  @JsonProperty(value = "name") final String name,
                  @JsonProperty(value = "email") final String email,
                  @JsonProperty(value = "position") final String position,
                  @JsonProperty(value = "department") final String department,
                  @JsonProperty(value = "birthDate") final LocalDate birthDate)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.department = department;
        this.birthDate = birthDate;
    }

    @JsonProperty(value = "id")
    public String getId() {
        return id;
    }

    @JsonProperty(value = "name")
    public String getName() {
        return name;
    }

    @JsonProperty(value = "email")
    public String getEmail() {
        return email;
    }

    @JsonProperty(value = "position")
    public String getPosition()
    {
        return position;
    }

    @JsonProperty(value = "department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty(value = "birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }
}
