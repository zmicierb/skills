package com.barysevich.project.model.result;


public class PersonIdBySkillsSearchResult
{
    private final String personId;

    private final int weight;


    public PersonIdBySkillsSearchResult(final String personId,
                                        final int weight)
    {
        this.personId = personId;
        this.weight = weight;
    }


    public String getPersonId()
    {
        return personId;
    }


    public int getWeight()
    {
        return weight;
    }


    @Override
    public String toString()
    {
        return "PersonIdBySkillSearchResult{" +
                "personId='" + personId + '\'' +
                ", weight=" + weight +
                '}';
    }
}
