package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {

    private String name;
    private int level;
    private String responsibilities;

    @JsonCreator
    public Band(
            @JsonProperty("name") String name,
            @JsonProperty("level") int level,
            @JsonProperty("responsibilities") String responsibilities) {
        this.name = name;
        this.level = level;
        this.responsibilities = responsibilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

}
