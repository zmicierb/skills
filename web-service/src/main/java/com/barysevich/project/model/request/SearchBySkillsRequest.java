package com.barysevich.project.model.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class SearchBySkillsRequest
{
    private final List<String> skills;

    private final int page;

    private final int size;


    @JsonCreator
    private SearchBySkillsRequest(@JsonProperty(value = "skills") final List<String> skills,
                                  @JsonProperty(value = "page") final int page,
                                  @JsonProperty(value = "size") final int size)
    {
        this.skills = skills;
        this.page = page;
        this.size = size;
    }


    public List<String> getSkills()
    {
        return skills;
    }


    public int getPage()
    {
        return page;
    }


    public int getSize()
    {
        return size;
    }


    @Override
    public String toString() {
        return "SearchBySkillsRequest{" +
                "skills=" + skills +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
