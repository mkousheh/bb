package edu.bu.ist.bbws.buconnector.model;

import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipServiceImpl;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/26/14.
 */
public class CourseMembershipRole {

    private String bbRoleId;
    private String courseRoleDescription;
    private String orgRoleDescription;
    private boolean defaultRole;

    public CourseMembershipRole() {
    }

    public CourseMembershipRole(CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO) {
        if (courseMembershipRoleVO != null){
            this.bbRoleId = courseMembershipRoleVO.getRoleIdentifier();
            this.courseRoleDescription = courseMembershipRoleVO.getCourseRoleDescription();
            this.orgRoleDescription = courseMembershipRoleVO.getOrgRoleDescription();
            this.defaultRole = courseMembershipRoleVO.getDefaultRole();
        }
    }

    public CourseMembershipRole(String courseMembershipRoleBbId, CoursemembershipService coursemembershipService) {
        CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO;
        try{
            courseMembershipRoleVO = coursemembershipService.getCourseMembershipRoleById(courseMembershipRoleBbId);
            if (courseMembershipRoleVO != null){
                this.bbRoleId = courseMembershipRoleVO.getRoleIdentifier();
                this.courseRoleDescription = courseMembershipRoleVO.getCourseRoleDescription();
                this.orgRoleDescription = courseMembershipRoleVO.getOrgRoleDescription();
                this.defaultRole = courseMembershipRoleVO.getDefaultRole();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public String getBbRoleId() {
        return bbRoleId;
    }

    public void setBbRoleId(String bbRoleId) {
        this.bbRoleId = bbRoleId;
    }

    public String getCourseRoleDescription() {
        return courseRoleDescription;
    }

    public void setCourseRoleDescription(String courseRoleDescription) {
        this.courseRoleDescription = courseRoleDescription;
    }

    public String getOrgRoleDescription() {
        return orgRoleDescription;
    }

    public void setOrgRoleDescription(String orgRoleDescription) {
        this.orgRoleDescription = orgRoleDescription;
    }

    public boolean isDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    @Override
    public String toString() {
        return "CourseMembershipRole{" +
                "bbRoleId='" + bbRoleId + '\'' +
                ", courseRoleDescription='" + courseRoleDescription + '\'' +
                ", orgRoleDescription='" + orgRoleDescription + '\'' +
                ", defaultRole=" + defaultRole +
                '}';
    }
}
