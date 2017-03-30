package com.barysevich.project.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.*;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Project implements Serializable {

    @Id
    @SequenceGenerator(name = "project_id_seq",
            sequenceName = "project_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "project_id_seq")
    private Long id;

    @OneToOne(targetEntity = Position.class, fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Position position;

    private String description;

    private String result;

    private String responsibility;

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    protected Project() {
    }

    public Project(Position position, String description, String result, String responsibility) {
        this.position = position;
        this.description = description;
        this.result = result;
        this.responsibility = responsibility;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
