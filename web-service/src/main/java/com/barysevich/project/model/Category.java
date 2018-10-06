package com.barysevich.project.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum Category
{
    LANGS("langs"),
    TECHS("techs"),
    SERVERS("servers"),
    DBS("dbs"),
    SYSTEMS("systems"),
    OTHERS("others"),;


    private final String name;


    Category(final String name)
    {
        this.name = name;
    }


    @JsonCreator
    public static Category getByName(final String name)
    {
        return Category.valueOf(name.trim().toLowerCase());
    }


    @JsonValue
    public String getName()
    {
        return name;
    }
}
