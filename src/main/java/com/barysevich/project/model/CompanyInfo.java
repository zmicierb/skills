package com.barysevich.project.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.*;

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

    private String name;

    private Date startDate;

    private Date endDate;

    @OneToOne(targetEntity = Position.class, fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Position position;

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    public CompanyInfo() {
    }

    public CompanyInfo(String name, Date startDate, Date endDate, Position position) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
