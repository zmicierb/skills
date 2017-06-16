package com.barysevich.project.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class Project extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "project_id_seq",
            sequenceName = "project_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "project_id_seq")
    private Long id;

    private Long personId;

    @OneToOne(targetEntity = Position.class, fetch = FetchType.EAGER, cascade = {REFRESH, DETACH})
    private Position position;

    private String description;

    @OneToMany(targetEntity = EnvironmentSkill.class, fetch = FetchType.EAGER, cascade = {PERSIST, MERGE, REFRESH, DETACH, REMOVE})
    @JoinColumn(name = "project_id", nullable = false)
    private List<EnvironmentSkill> environmentSkills = new ArrayList<>();

    private String result;

    private String responsibility;

    @OneToOne(targetEntity = CompanyInfo.class, fetch = FetchType.EAGER, cascade = {MERGE, REFRESH, DETACH})
    @JoinColumn(name = "company_id")
    private CompanyInfo companyInfo;

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    public Project() {
        //default constructor
    }

    public Project(Long personId, Position position, String description, String result, String responsibility, List<EnvironmentSkill> environmentSkills, CompanyInfo companyInfo) {
        this.personId = personId;
        this.position = position;
        this.description = description;
        this.result = result;
        this.responsibility = responsibility;
        this.environmentSkills = environmentSkills;
        this.companyInfo = companyInfo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public List<EnvironmentSkill> getEnvironmentSkills() {
        return environmentSkills;
    }

    public void setEnvironmentSkills(List<EnvironmentSkill> environmentSkills) {
        this.environmentSkills = environmentSkills;
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

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
