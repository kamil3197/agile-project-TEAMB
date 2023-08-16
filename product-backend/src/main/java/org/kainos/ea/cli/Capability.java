package org.kainos.ea.cli;

import java.sql.Blob;

public class Capability {
    private int id;
    private String capabilityName;
    private String leadName;
    private Blob leadPhoto;
    private String leadMessage;

    public Capability(int id, String capabilityName, String leadName, Blob leadPhoto, String leadMessage) {
        this.id = id;
        this.capabilityName = capabilityName;
        this.leadName = leadName;
        this.leadPhoto = leadPhoto;
        this.leadMessage = leadMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
