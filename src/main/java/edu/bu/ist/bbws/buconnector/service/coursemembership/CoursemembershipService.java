package edu.bu.ist.bbws.buconnector.service.coursemembership;

import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface CoursemembershipService {

    public CourseMembershipWSStub.CourseMembershipRoleVO getRole(String role) throws RemoteException;

    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembership(String username, String courseId) throws RemoteException;

    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembershipByKey(String userInternalId, String courseInternalId) throws RemoteException;

}
