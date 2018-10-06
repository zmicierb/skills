package com.barysevich.project.model.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class SearchBySkillsRequest
{
    private final List<String> skills;


    @JsonCreator
    public SearchBySkillsRequest(@JsonProperty(value = "skills") final List<String> skills)
    {
        this.skills = skills;
    }


    public List<String> getSkills()
    {
        return skills;
    }


    @Override
    public String toString()
    {
        return "SearchBySkillsRequest{" +
                "skills=" + skills +
                '}';
    }
}
