package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddJobRole {

    private String role_title;
    private String summary;
    private String link;

    @JsonCreator
    public AddJobRole(
            @JsonProperty("role_title") String role_title,
            @JsonProperty("summary") String summary,
            @JsonProperty("link") String link) {
        this.role_title = role_title;
        this.summary = summary;
        this.link = link;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

