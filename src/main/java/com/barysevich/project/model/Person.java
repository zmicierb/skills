package com.barysevich.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.REFRESH;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Person extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "person_id_seq",
            sequenceName = "person_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "person_id_seq")
    private Long id;

    @Column(name = "name", length = 1000)
    @NotEmpty
    private String name;

    @Column(name = "email", length = 256, unique = true)
    @NotEmpty
    private String email;

    @OneToOne(targetEntity = Position.class, fetch = FetchType.EAGER, cascade = {REFRESH, DETACH})
    private Position position;

    @OneToOne(targetEntity = Department.class, fetch = FetchType.EAGER, cascade = {REFRESH, DETACH})
    private Department department;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    private LocalDate birthDate;

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    public Person() {
        //default constructor
    }

    public Person(String name, String email, Position position, Department department, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.department = department;
        this.birthDate = birthDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
