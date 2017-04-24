package com.barysevich.project.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by dima on 4/23/17.
 */
@Entity
public class Department extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "department_id_seq",
            sequenceName = "department_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "department_id_seq")
    private Long id;

    @Column(name = "name", length = 500)
    @NotEmpty
    private String name;

    public Department() {
        //default constructor
    }

    public Department(String name) {
        this.name = name;
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
}
