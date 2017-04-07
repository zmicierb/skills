package com.barysevich.project.model;

import javax.persistence.*;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Skill extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "skill_id_seq",
            sequenceName = "skill_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "skill_id_seq")
    private Long id;

    private String name;

    public Skill() {
    }

    public Skill(String name) {
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
