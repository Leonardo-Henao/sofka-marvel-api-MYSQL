package com.sofka.marvelcomics.models;

public class ModelCharacter {
    protected String id;

    protected String name;
    protected String description;

    public ModelCharacter(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
