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
public class CompanyInfo extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "company_info_id_seq",
            sequenceName = "company_info_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "company_info_id_seq")
    private Long id;

    @NotEmpty
    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    private LocalDate endDate;

    @OneToOne(targetEntity = Position.class, fetch = FetchType.EAGER, cascade = {REFRESH, DETACH})
    private Position position;

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    public CompanyInfo() {
        //default constructor
    }

    public CompanyInfo(String name, LocalDate startDate, LocalDate endDate, Position position) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
