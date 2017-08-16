package com.barysevich.project.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "skills", type = "personSkill")
public class PersonSkill {

    private Long id;

    @Field(type = FieldType.Nested)
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
