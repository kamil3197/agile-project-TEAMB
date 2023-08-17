package org.kainos.ea.cli;

import java.sql.Blob;

public class Capability {
    private int capabilityId;
    private String capabilityName;
    private String leadName;
    private String leadPhoto;
    private String leadMessage;

    public Capability(int capabilityId, String capabilityName, String leadName, String leadPhoto,
                      String leadMessage) {
        this.capabilityId = capabilityId;
        this.capabilityName = capabilityName;
        this.leadName = leadName;
        this.leadPhoto = leadPhoto;
        this.leadMessage = leadMessage;
    }

    public int getId() {
        return capabilityId;
    }

    public void setId(int id) {
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
