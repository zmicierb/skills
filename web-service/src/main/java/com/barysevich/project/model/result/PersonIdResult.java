package com.barysevich.project.model.result;


public class PersonIdResult
{
    private final String personId;


    public PersonIdResult(final String personId)
    {
        this.personId = personId;
    }


    public String getPersonId()
    {
        return personId;
    }


    @Override
    public String toString()
    {
        return "PersonIdResult{" +
                "personId='" + personId + '\'' +
                '}';
    }
}
