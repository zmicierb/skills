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

    private Long personId;

    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;

    @OneToOne(targetEntity = Project.class, fetch = FetchType.EAGER, cascade = {MERGE, REFRESH, DETACH, REMOVE})
    private Project project;

    @OneToOne(targetEntity = CompanyInfo.class, fetch = FetchType.EAGER, cascade = {MERGE, REFRESH, DETACH})
    @JoinColumn(name = "company_id")
    private CompanyInfo companyInfo;

    public ProjectSum() {
        //default constructor
    }

    public ProjectSum(Long personId, Project project, CompanyInfo companyInfo) {
        this.personId = personId;
        this.project = project;
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
