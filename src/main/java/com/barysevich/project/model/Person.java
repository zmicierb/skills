package com.barysevich.project.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Person implements Serializable {

    @Id
    @SequenceGenerator(name = "person_id_seq",
            sequenceName = "person_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "person_id_seq")
    private Long id;

    @Column(name = "name", length = 1000)
    private String name;

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    protected Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
