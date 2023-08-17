package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Band {

    @ApiModelProperty(example = "Manager", required = true)
    private String name;
    @ApiModelProperty(example = "3", required = true)
    private int level;
    @ApiModelProperty(example = "organize work", required = true)
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
