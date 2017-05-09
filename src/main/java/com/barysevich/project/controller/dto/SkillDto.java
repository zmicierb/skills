package com.barysevich.project.controller.dto;

/**
 * Created by dima on 5/9/17.
 */
public class SkillDto {

    private Long skillId;

    private String skillName;

    private Integer weight;

    public SkillDto() {

    }

    public SkillDto(Long skillId, String skillName, Integer weight) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.weight = weight;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
