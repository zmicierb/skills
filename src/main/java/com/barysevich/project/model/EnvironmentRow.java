package com.barysevich.project.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class EnvironmentRow extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "environment_row_id_seq",
            sequenceName = "environment_row_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "environment_row_id_seq")
    private Long id;

    @Column
    private Long projectId;

    @Column(name = "skill_id", insertable = false, updatable = false)
    private Long skillId;

    @OneToOne(targetEntity = Skill.class, fetch = FetchType.EAGER, cascade = {MERGE, REFRESH, DETACH})
    private Skill skill;

    private Integer weight;

    public EnvironmentRow() {
        //default constructor
    }

    public EnvironmentRow(Long projectId, Skill skill, Integer weight) {
        this.projectId = projectId;
        this.skill = skill;
        this.weight = weight;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
