package com.nathaniel.munro.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Munro {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Height (m)")
    private double height;
    @JsonProperty("Post 1997")
    private String hillCategory;
    @JsonProperty("Grid Ref")
    private String gridReference;

    public Munro() {
    }

    public Munro(String name, double height, String hillCategory, String gridReference) {
        this.name = name;
        this.height = height;
        this.hillCategory = hillCategory;
        this.gridReference = gridReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHillCategory() {
        return hillCategory;
    }

    public void setHillCategory(String hillCategory) {
        this.hillCategory = hillCategory;
    }

    public String getGridReference() {
        return gridReference;
    }

    public void setGridReference(String gridReference) {
        this.gridReference = gridReference;
    }
}
