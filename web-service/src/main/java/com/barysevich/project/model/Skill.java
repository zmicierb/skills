package com.barysevich.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skill")
public class Skill {
    @Id
    private final String id;

    @Indexed(unique = true)
    private final String name;


    @JsonCreator
    public Skill(@JsonProperty(value = "id") final String id,
                 @JsonProperty(value = "name") final String name)
    {
        this.id = id;
        this.name = name;
    }

    @JsonProperty(value = "id")
    public String getId() {
        return id;
    }

    @JsonProperty(value = "name")
    public String getName() {
        return name;
    }
}
