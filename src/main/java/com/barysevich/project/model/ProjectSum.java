package com.barysevich.project.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class ProjectSum extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "project_sum_id_seq",
            sequenceName = "project_sum_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "project_sum_id_seq")
    private Long id;

    @Column(name = "person_id", insertable = false, updatable = false)
    private Long personId;

    @OneToOne(targetEntity = Person.class, fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Person person;

    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;

    @OneToOne(targetEntity = Project.class, fetch = FetchType.LAZY, cascade = {ALL})
    private Project project;

    @OneToOne(targetEntity = CompanyInfo.class, fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private CompanyInfo companyInfo;

    public ProjectSum() {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }
}
