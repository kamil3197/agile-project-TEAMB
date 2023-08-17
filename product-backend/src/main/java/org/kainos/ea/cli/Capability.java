package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCapability {
    private int capabilityId;
    private String capabilityName;
    private String leadName;
    private String leadPhoto;
    private String leadMessage;

    @JsonCreator
    public RequestCapability(
            @JsonProperty("capabilityId") int capabilityId,
            @JsonProperty("capabilityName") String capabilityName,
            @JsonProperty("leadName") String leadName,
            @JsonProperty("leadPhoto") String leadPhoto,
            @JsonProperty("leadMessage") String leadMessage) {
        this.capabilityId = capabilityId;
        this.capabilityName = capabilityName;
        this.leadName = leadName;
        this.leadPhoto = leadPhoto;
        this.leadMessage = leadMessage;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        this.capabilityId = capabilityId;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadPhoto() {
        return leadPhoto;
    }

    public void setLeadPhoto(String leadPhoto) {
        this.leadPhoto = leadPhoto;
    }

    public String getLeadMessage() {
        return leadMessage;
    }

    public void setLeadMessage(String leadMessage) {
        this.leadMessage = leadMessage;
    }
}
