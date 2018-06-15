package com.barysevich.project.model;

import java.util.ArrayList;
import java.util.List;

public class PersonSkill {

    private Long id;

    private List<SkillSumSearch> skills = new ArrayList<>();

    public PersonSkill() {
        //default constructor
    }

    public PersonSkill(SkillSum skillSum) {
        this.id = skillSum.getPersonId();
        this.skills.add(new SkillSumSearch(skillSum));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SkillSumSearch> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillSumSearch> skills) {
        this.skills = skills;
    }

}
