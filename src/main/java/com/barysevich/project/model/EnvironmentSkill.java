package com.barysevich.project.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.REFRESH;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class EnvironmentSkill extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "environment_skill_id_seq",
            sequenceName = "environment_skill_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "environment_skill_id_seq")
    private Long id;

    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;

    @Column(name = "skill_id", insertable = false, updatable = false)
    private Long skillId;

    @OneToOne(targetEntity = Skill.class, fetch = FetchType.EAGER, cascade = {REFRESH, DETACH})
    private Skill skill;

    private Integer weight;

    public EnvironmentSkill() {
        //default constructor
    }

    public EnvironmentSkill(Skill skill, Integer weight) {
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