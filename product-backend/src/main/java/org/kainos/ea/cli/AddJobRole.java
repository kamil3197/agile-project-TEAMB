package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class AddJobRole {

    @ApiModelProperty(example = "Platform Engineer", required = true)
    private String roleTitle;
    @ApiModelProperty(example = "As Platform Engineer (Associate) in Kainos, you’ll be responsible for automating, building\n" +
            "and supporting modern digital service platforms using public cloud technology.", required = true)
    private String summary;

    @ApiModelProperty(example = "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FPlatforms%2FJob%20profile%20%2D%20Platform%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FPlatforms&p=true&ga=1", required = true)
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

