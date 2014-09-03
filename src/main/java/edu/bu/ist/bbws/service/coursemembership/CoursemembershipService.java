package edu.bu.ist.bbws.service.coursemembership;

import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface CoursemembershipService {

    public CourseMembershipWSStub.CourseMembershipRoleVO getCourseMembershipRoleById(String courseMembershipRoleId) throws RemoteException;

    public CourseMembershipWSStub.CourseMembershipRoleVO getCourseMembershipRoleByName(String courseMembershipRoleName) throws RemoteException;


    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembership(String username, String courseId) throws RemoteException;

    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembershipByUserBbIdAndCourseBbId(String userInternalId, String courseInternalId) throws RemoteException;

}
