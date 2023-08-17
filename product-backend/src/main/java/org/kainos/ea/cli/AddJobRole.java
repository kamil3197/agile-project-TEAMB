package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddJobRole {

    private String roleTitle;
    private String summary;
    private String link;

    private int band_id;

    @JsonCreator
    public AddJobRole(
            @JsonProperty("roleTitle") String roleTitle,
            @JsonProperty("summary") String summary,
            @JsonProperty("link") String link,
            @JsonProperty("band_id") int band_id) {
        this.roleTitle = roleTitle;
        this.summary = summary;
        this.link = link;
        this.band_id = band_id;
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

    public int getBand_id() {
        return band_id;
    }

    public void setBand_id(int band_id) {
        this.band_id = band_id;
    }

}

