package org.kainos.ea.cli;

public class JobSpecification {
    private int RoleId;
    private String roleTitle;
    private String Summary;
    private String SpecificationLink;
    private int band_level;
    private String band_name;


    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getSpecificationLink() {
        return SpecificationLink;
    }

    public void setSpecificationLink(String specificationLink) {
        SpecificationLink = specificationLink;
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

    public JobSpecification(int roleId, String roleTitle, String summary, String specificationLink, int band_level, String band_name) {
        RoleId = roleId;
        this.roleTitle = roleTitle;
        Summary = summary;
        SpecificationLink = specificationLink;
        this.band_level = band_level;
        this.band_name = band_name;
    }
}