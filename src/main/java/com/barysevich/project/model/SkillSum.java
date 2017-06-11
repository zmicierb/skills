package com.barysevich.project.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.REFRESH;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class SkillSum extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "skill_sum_id_seq",
            sequenceName = "skill_sum_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "skill_sum_id_seq")
    private Long id;

    private Long personId;

    @Column(name = "skill_id", insertable = false, updatable = false)
    private Long skillId;

    @OneToOne(targetEntity = Skill.class, fetch = FetchType.EAGER, cascade = {REFRESH, DETACH})
    private Skill skill;

    @Column(name = "row_id", insertable = false, updatable = false)
    private Long rowId;

    @OneToOne(targetEntity = Row.class, fetch = FetchType.EAGER, cascade = {REFRESH, DETACH})
    private Row row;

    private Integer weight;

    public SkillSum() {
        //default constructor
    }

    public SkillSum(Long personId, Skill skill, Row row, Integer weight) {
        this.personId = personId;
        this.skill = skill;
        this.row = row;
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
