package com.barysevich.project.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Position implements Serializable {

    @Id
    @SequenceGenerator(name = "position_id_seq",
            sequenceName = "position_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "position_id_seq")
    private Long id;

    private String name;

    protected Position() {
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
