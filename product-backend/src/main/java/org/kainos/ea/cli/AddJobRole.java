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

