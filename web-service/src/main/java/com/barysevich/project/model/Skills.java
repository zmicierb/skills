package com.barysevich.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skills")
public class Skills
{
    @Id
    private final String id;

    private final String[] langs;

    private final String[] techs;

    private final String[] servers;

    private final String[] dbs;

    private final String[] systems;

    private final String[] others;

    public Skills(final String id,
                  final String[] langs,
                  final String[] techs,
                  final String[] servers,
                  final String[] dbs,
                  final String[] systems,
                  final String[] others)
    {
        this.id = id;
        this.langs = langs;
        this.techs = techs;
        this.servers = servers;
        this.dbs = dbs;
        this.systems = systems;
        this.others = others;
    }

    public String getId() {
        return id;
    }

    public String[] getLangs() {
        return langs;
    }

    public String[] getTechs() {
        return techs;
    }

    public String[] getServers() {
        return servers;
    }

    public String[] getDbs() {
        return dbs;
    }

    public String[] getSystems() {
        return systems;
    }

    public String[] getOthers() {
        return others;
    }
}
