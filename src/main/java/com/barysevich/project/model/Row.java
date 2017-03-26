package com.barysevich.project.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Row implements Serializable {

    @Id
    @SequenceGenerator(name = "row_id_seq",
            sequenceName = "row_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "row_id_seq")
    private Long id;

    private String name;

    protected Row() {
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
