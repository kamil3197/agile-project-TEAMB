package org.kainos.ea.cli;

public class JobSpecification {
    private int RoleId;
    private String roleTitle;
    private String Summary;
    private String SpecificationLink;

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

    public JobSpecification(int roleId, String roleTitle, String summary, String specificationLink) {
        RoleId = roleId;
        this.roleTitle = roleTitle;
        Summary = summary;
        SpecificationLink = specificationLink;
    }
}