package org.kainos.ea.cli;

import java.sql.Blob;

public class Capability {
    private int capabilityId;
    private String capabilityName;
    private String leadName;
    private Blob leadPhoto;

    public Capability(int capabilityId, String capabilityName, String leadName, Blob leadPhoto, String leadMessage) {
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

    public Blob getLeadPhoto() {
        return leadPhoto;
    }

    public void setLeadPhoto(Blob leadPhoto) {
        this.leadPhoto = leadPhoto;
    }

    public String getLeadMessage() {
        return leadMessage;
    }

    public void setLeadMessage(String leadMessage) {
        this.leadMessage = leadMessage;
    }

    private String leadMessage;
}
