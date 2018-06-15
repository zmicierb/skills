package com.barysevich.project.controller.dto;


import com.barysevich.project.model.SkillSum;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dima on 5/9/17.
 */
public class PersonSkillsDto
{

    private Long personId;

    private Long rowId;

    private String rowName;

    private List<SkillDto> skills = new ArrayList<>();


    public PersonSkillsDto()
    {
        //default constructor
    }


    public PersonSkillsDto(SkillSum skillSum)
    {
        this.personId = skillSum.getPersonId();
        this.rowId = skillSum.getRowId();
        this.rowName = skillSum.getRow().getName();
        this.skills.add(new SkillDto(skillSum.getSkillId(), skillSum.getSkill().getName(), skillSum.getPosition()));
    }


    public Long getPersonId()
    {
        return personId;
    }


    public void setPersonId(Long personId)
    {
        this.personId = personId;
    }


    public Long getRowId()
    {
        return rowId;
    }


    public void setRowId(Long rowId)
    {
        this.rowId = rowId;
    }


    public String getRowName()
    {
        return rowName;
    }


    public void setRowName(String rowName)
    {
        this.rowName = rowName;
    }


    public List<SkillDto> getSkills()
    {
        return skills;
    }


    public void setSkills(List<SkillDto> skills)
    {
        this.skills = skills;
    }
}
