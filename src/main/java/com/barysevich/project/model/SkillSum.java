package com.barysevich.project.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

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

    @Column(name = "person_id", insertable = false, updatable = false)
    private Long personId;

    @OneToOne(targetEntity = Person.class, fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Person person;

    @Column(name = "skill_row_id", insertable = false, updatable = false)
    private Long skillRowLinkId;

    @OneToOne(targetEntity = SkillRowLink.class, fetch = FetchType.LAZY, cascade = {ALL})
    private SkillRowLink skillRowLink;

    public SkillSum() {
        //default constructor
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

    public Long getSkillRowLinkId() {
        return skillRowLinkId;
    }

    public void setSkillRowLinkId(Long skillRowLinkId) {
        this.skillRowLinkId = skillRowLinkId;
    }

    public SkillRowLink getSkillRowLink() {
        return skillRowLink;
    }

    public void setSkillRowLink(SkillRowLink skillRowLink) {
        this.skillRowLink = skillRowLink;
    }
}
