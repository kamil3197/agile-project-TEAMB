package org.kainos.ea.cli;

public class JobRole {
    private int jobRoleId;
    private String roleTitle;
    private int band_level;
    private String band_name;


    public JobRole(int jobRoleId, String roleTitle, int band_level, String band_name) {
        this.jobRoleId = jobRoleId;
        this.roleTitle = roleTitle;
        this.band_level = band_level;
        this.band_name = band_name;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public int getBand_level() {
        return band_level;
    }

    public void setBand_level(int band_level) {
        this.band_level = band_level;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }


}
