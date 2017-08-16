package com.barysevich.project.model;

public class SkillSumSearch {

    private Long id;

    private Long skillId;

    private String skillName;

    private Integer position;

    public SkillSumSearch() {

    }

    public SkillSumSearch(SkillSum skillSum) {
        this.id = skillSum.getId();
        this.skillId = skillSum.getSkill().getId();
        this.skillName = skillSum.getSkill().getName();
        this.position = skillSum.getPosition();
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
