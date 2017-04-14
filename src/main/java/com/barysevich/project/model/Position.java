package com.barysevich.project.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Position extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "position_id_seq",
            sequenceName = "position_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "position_id_seq")
    private Long id;

    @NotEmpty
    private String name;

    public Position() {
    }

    public Position(String name) {
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
