package com.barysevich.project.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.REFRESH;

/**
 * Created by BarysevichD on 2017-03-14.
 */
@Entity
@Document(indexName = "skills", type = "skillSum")
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

    @OneToOne(targetEntity = Skill.class, fetch = FetchType.LAZY, cascade = {REFRESH, DETACH})
    private Skill skill;

    @Column(name = "row_id", insertable = false, updatable = false)
    private Long rowId;

    @OneToOne(targetEntity = Row.class, fetch = FetchType.LAZY, cascade = {REFRESH, DETACH})
    private Row row;

    private Integer position;

    private Integer totalAmount;

    public SkillSum() {
        //default constructor
    }

    public SkillSum(Long personId, Skill skill, Row row, Integer position) {
        this.personId = personId;
        this.skill = skill;
        this.row = row;
        this.position = position;
    }

    public SkillSum(Long personId, Skill skill, Row row, Integer position, Integer totalAmount) {
        this.personId = personId;
        this.skill = skill;
        this.row = row;
        this.position = position;
        this.totalAmount = totalAmount;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getWeight() {
        if (this.totalAmount == null)
            return 0;
        // range (15, 40) of optimal number of skills
        int k = (this.getTotalAmount() > 15 && this.getTotalAmount() < 40)
                ? this.getTotalAmount()
                : this.getTotalAmount() <= 15 ? 15 : 40;
        return ((double) (Math.abs(k - this.getPosition()) + 1)) / (Math.abs(k - this.getTotalAmount()) + k);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AbstractPersistable that = (AbstractPersistable) o;
        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
