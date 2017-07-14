package com.barysevich.project.controller.dto;

/**
 * Created by dima on 5/9/17.
 */
public class SkillDto {

    private Long skillId;

    private String skillName;

    private Integer position;

    public SkillDto() {

    }

    public SkillDto(Long skillId, String skillName, Integer position) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.position = position;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
