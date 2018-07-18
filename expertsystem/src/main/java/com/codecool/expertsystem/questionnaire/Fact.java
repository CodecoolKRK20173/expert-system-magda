package com.codecool.expertsystem.questionnaire;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;


public class Fact {

    private Map<String, Boolean> valueMap;
    private String description;
    private String id;

    public Fact(String id, String description) {

        initializeMap();
        this.id = id;
        this.description = description;

    }

    private void initializeMap() {

        this.valueMap = new HashMap<>();

    }

    public void setFactValueById(String id, boolean value) {

        this.valueMap.put(id, value);

    }

    public String getDescription() {

        return this.description;

    }

    public boolean getValueById(String id) {

        return this.valueMap.get(id);

    }

}