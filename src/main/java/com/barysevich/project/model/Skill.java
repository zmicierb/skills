package com.barysevich.project.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Skill implements Serializable {

    @Id
    @SequenceGenerator(name = "skill_id_seq",
            sequenceName = "skill_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "skill_id_seq")
    private Long id;

    private String name;

    protected Skill() {
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
}
