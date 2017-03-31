package com.barysevich.project.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
public class SkillRowLink extends AbstractPersistable<Long> {

    @Id
    @SequenceGenerator(name = "skill_row_link_id_seq",
            sequenceName = "skill_row_link_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "skill_row_link_id_seq")
    private Long id;

    @Column(name = "skill_id", insertable = false, updatable = false)
    private Long skillId;

    @OneToOne(targetEntity = Skill.class, fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Skill skill;

    @Column(name = "row_id", insertable = false, updatable = false)
    private Long rowId;

    @OneToOne(targetEntity = Row.class, fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Row row;

    private Integer weight;

    public SkillRowLink() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
