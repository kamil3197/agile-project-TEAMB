package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddJobRole {

    private String roleTitle;
    private String summary;
    private String link;

    @JsonCreator
    public AddJobRole(
            @JsonProperty("roleTitle") String roleTitle,
            @JsonProperty("summary") String summary,
            @JsonProperty("link") String link) {
        this.roleTitle = roleTitle;
        this.summary = summary;
        this.link = link;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
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

