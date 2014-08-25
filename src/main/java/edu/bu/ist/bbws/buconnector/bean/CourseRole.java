package edu.bu.ist.bbws.buconnector.bean;

/**
 * Created by mkousheh on 8/25/14.
 */
public class CourseRole {
    String courseRoleDescription;
    boolean defaultRole;
    String orgRoleDescription;
    String roleId;

    public String getCourseRoleDescription() {
        return courseRoleDescription;
    }

    public void setCourseRoleDescription(String courseRoleDescription) {
        this.courseRoleDescription = courseRoleDescription;
    }

    public boolean isDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    public String getOrgRoleDescription() {
        return orgRoleDescription;
    }

    public void setOrgRoleDescription(String orgRoleDescription) {
        this.orgRoleDescription = orgRoleDescription;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

