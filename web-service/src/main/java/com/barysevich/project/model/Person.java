package com.barysevich.project.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "persons")
public class Person
{
    @Id
    private final String id;

    @Indexed(unique = true)
    private final Long personId;

    private final String name;

    private final String email;

    private final String position;

    private final String department;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private final LocalDate birthDate;


    @JsonCreator
    public Person(@JsonProperty(value = "id") final String id,
                  @JsonProperty(value = "personId") final Long personId,
                  @JsonProperty(value = "name") final String name,
                  @JsonProperty(value = "email") final String email,
                  @JsonProperty(value = "position") final String position,
                  @JsonProperty(value = "department") final String department,
                  @JsonProperty(value = "birthDate") final LocalDate birthDate)
    {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.position = position;
        this.department = department;
        this.birthDate = birthDate;
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty(value = "id")
    public String getId()
    {
        return id;
    }


    @JsonProperty(value = "personId")
    public Long getPersonId()
    {
        return personId;
    }


    @JsonProperty(value = "name")
    public String getName()
    {
        return name;
    }


    @JsonProperty(value = "email")
    public String getEmail()
    {
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
    public LocalDate getBirthDate()
    {
        return birthDate;
    }


    @Override
    public String toString()
    {
        return "Person{" +
                "id='" + id + '\'' +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }


    public static final class Builder
    {
        private Person person;

        private Long personId;


        public Builder withPerson(final Person person)
        {
            this.person = person;
            return this;
        }


        public Builder withPersonId(final Long personId)
        {
            this.personId = personId;
            return this;
        }


        public Person build()
        {
            return new Person(
                    person.getId(),
                    personId,
                    person.getName(),
                    person.getEmail(),
                    person.getPosition(),
                    person.getDepartment(),
                    person.getBirthDate());
        }
    }
}
