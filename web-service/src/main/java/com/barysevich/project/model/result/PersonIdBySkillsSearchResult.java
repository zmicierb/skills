package com.barysevich.project.model.result;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonIdBySkillsSearchResult
{
    private final Long personId;

    private final int weight;

    @JsonCreator
    private PersonIdBySkillsSearchResult(@JsonProperty(value = "personId") final Long personId,
                                         @JsonProperty(value = "weight") final int weight)
    {
        this.personId = personId;
        this.weight = weight;
    }


    @JsonProperty(value = "personId")
    public Long getPersonId()
    {
        return personId;
    }


    @JsonProperty(value = "weight")
    public int getWeight()
    {
        return weight;
    }


    @Override
    public String toString()
    {
        return "PersonIdBySkillsSearchResult{" +
                "personId=" + personId +
                ", weight=" + weight +
                '}';
    }
}
