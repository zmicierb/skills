package com.barysevich.project.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "skills")
public class Skills
{
    @Id
    private final String id;

    @Indexed(unique = true)
    private final String personId;

    @Indexed
    private final List<String> langs;

    @Indexed
    private final List<String> techs;

    @Indexed
    private final List<String> servers;

    @Indexed
    private final List<String> dbs;

    @Indexed
    private final List<String> systems;

    @Indexed
    private final List<String> others;


    @JsonCreator
    public Skills(@JsonProperty(value = "id") final String id,
                  @JsonProperty(value = "personId") final String personId,
                  @JsonProperty(value = "langs") final List<String> langs,
                  @JsonProperty(value = "techs") final List<String> techs,
                  @JsonProperty(value = "servers") final List<String> servers,
                  @JsonProperty(value = "dbs") final List<String> dbs,
                  @JsonProperty(value = "systems") final List<String> systems,
                  @JsonProperty(value = "others") final List<String> others)
    {
        this.id = id;
        this.personId = personId;
        this.langs = langs;
        this.techs = techs;
        this.servers = servers;
        this.dbs = dbs;
        this.systems = systems;
        this.others = others;
    }


    @JsonProperty(value = "id")
    public String getId()
    {
        return id;
    }


    @JsonProperty(value = "personId")
    public String getPersonId()
    {
        return personId;
    }


    @JsonProperty(value = "langs")
    public List<String> getLangs()
    {
        return langs;
    }


    @JsonProperty(value = "techs")
    public List<String> getTechs()
    {
        return techs;
    }


    @JsonProperty(value = "servers")
    public List<String> getServers()
    {
        return servers;
    }


    @JsonProperty(value = "dbs")
    public List<String> getDbs()
    {
        return dbs;
    }


    @JsonProperty(value = "systems")
    public List<String> getSystems()
    {
        return systems;
    }


    @JsonProperty(value = "others")
    public List<String> getOthers()
    {
        return others;
    }


    @Override
    public String toString()
    {
        return "Skills{" +
                "id='" + id + '\'' +
                ", personId='" + personId + '\'' +
                ", langs=" + langs +
                ", techs=" + techs +
                ", servers=" + servers +
                ", dbs=" + dbs +
                ", systems=" + systems +
                ", others=" + others +
                '}';
    }
}
