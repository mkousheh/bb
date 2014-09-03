package edu.bu.ist.bbws.service.coursemembership;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.service.context.ContextServiceImpl;
import edu.bu.ist.bbws.service.course.CourseServiceImpl;
import edu.bu.ist.bbws.service.user.UserServiceImpl;
import edu.bu.ist.bbws.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public class CoursemembershipServiceImpl implements CoursemembershipService {
    private static final Logger logger = Logger.getLogger(CoursemembershipServiceImpl.class.getName());


    public static final int BY_ID = 1;
    public static final int BY_CRS_ID = 2;
    public static final int BY_CRS_MEM_ID = 3;
    public static final int BY_GRP_ID = 4;
    public static final int BY_USER_ID = 5;
    public static final int BY_CRS_ID_AND_USER_ID = 6;
    public static final int BY_CRS_ID_AND_ROLE_ID = 7;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private ContextServiceImpl contextService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ConnectorUtil connectorUtil;

    /**
     * gets a role object by role name
      * @param courseMembershipRoleId - bb course membership role identifier
     * @return
     * @throws RemoteException
     */
    public CourseMembershipWSStub.CourseMembershipRoleVO getCourseMembershipRoleById(String courseMembershipRoleId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseMembershipWSStub.CourseMembershipRoleVO[] courseMembershipRoles = null;
        CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRole = null;

        try {
            CourseMembershipWSStub.CourseRoleFilter courseMembershipRoleFilter = new CourseMembershipWSStub.CourseRoleFilter();
            courseMembershipRoleFilter.setRoleIds(new String[]{courseMembershipRoleId});

            CourseMembershipWSStub.GetCourseRoles courseRoles = new CourseMembershipWSStub.GetCourseRoles();
            courseRoles.getF();

            CourseMembershipWSStub courseMembershipWSStub = new CourseMembershipWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/CourseMembership.WS");
            getContextService().client_engage(courseMembershipWSStub._getServiceClient());
            CourseMembershipWSStub.GetCourseRolesResponse courseRolesResponse = courseMembershipWSStub.getCourseRoles(courseRoles);
            courseMembershipRoles = courseRolesResponse.get_return();
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        for (CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO: courseMembershipRoles){
            if (courseMembershipRoleVO.getRoleIdentifier().equalsIgnoreCase(courseMembershipRoleId)) {
                courseMembershipRole = courseMembershipRoleVO;
                break;
            }
        }

        return courseMembershipRole;
    }

    /**
     * gets a role object by role name
     * @param courseMembershipRoleName - bb course membership role display name
     * @return
     * @throws RemoteException
     */
    public CourseMembershipWSStub.CourseMembershipRoleVO getCourseMembershipRoleByName(String courseMembershipRoleName) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseMembershipWSStub.CourseMembershipRoleVO[] courseMembershipRoles = null;
        CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRole = null;

        try {
            CourseMembershipWSStub.CourseRoleFilter courseRolesFilter = new CourseMembershipWSStub.CourseRoleFilter();
            courseRolesFilter.setRoleIds(new String[]{courseMembershipRoleName});

            CourseMembershipWSStub.GetCourseRoles courseRoles = new CourseMembershipWSStub.GetCourseRoles();
            courseRoles.setF(null);

            CourseMembershipWSStub courseMembershipWSStub = new CourseMembershipWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/CourseMembership.WS");
            getContextService().client_engage(courseMembershipWSStub._getServiceClient());
            CourseMembershipWSStub.GetCourseRolesResponse courseRolesResponse = courseMembershipWSStub.getCourseRoles(courseRoles);
            courseMembershipRoles = courseRolesResponse.get_return();
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        for (CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO: courseMembershipRoles){
            if (courseMembershipRoleVO.getCourseRoleDescription().replace(":","").equalsIgnoreCase(courseMembershipRoleName)) {
                courseMembershipRole = courseMembershipRoleVO;
                break;
            }
        }
        return courseMembershipRole;
    }

    /**
     * get course membership for a given username and a course id
     * @param membershipBbId
     * @return
     * @throws RemoteException
     */
    private CourseMembershipWSStub.CourseMembershipVO[] getCourseMembershipByBbId(String membershipBbId) throws RemoteException {

        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseMembershipWSStub.CourseMembershipVO[] bbCrsMemberships=null;
        try {

            CourseMembershipWSStub.MembershipFilter membershipFilter = new CourseMembershipWSStub.MembershipFilter();
            membershipFilter.setFilterType(BY_ID); //will load CourseMembershipVO records by Id's.

            CourseMembershipWSStub.GetCourseMembership courseMembership = new CourseMembershipWSStub.GetCourseMembership();
            courseMembership.setF(membershipFilter);

            CourseMembershipWSStub courseMembershipWSStub = new CourseMembershipWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/CourseMembership.WS");
            getContextService().client_engage(courseMembershipWSStub._getServiceClient());
            CourseMembershipWSStub.GetCourseMembershipResponse courseMembershipResponse = courseMembershipWSStub.getCourseMembership(courseMembership);
            bbCrsMemberships = courseMembershipResponse.get_return();
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return bbCrsMemberships;
    }
    /**
     * get course membership for a given username and a course id
     * @param username - bb batchId (alias)
     * @param courseId - bb course identifier
     * @return
     * @throws RemoteException
     */
    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembership(String username, String courseId) throws RemoteException {

        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null) {
            courseInternalId = course.getId();
        }

        String userInternalId = null;
        UserWSStub.UserVO user = getUserService().getUserByUsername(username);
        if (user != null) {
            userInternalId = user.getId();
        }

        return getCourseMembershipByUserBbIdAndCourseBbId(userInternalId, courseInternalId);
    }

    /**
     * get course membership for a given username and a course id
     * @param userBbId
     * @param courseBbId
     * @return
     * @throws RemoteException
     */
    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembershipByUserBbIdAndCourseBbId(String userBbId, String courseBbId) throws RemoteException {

        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseMembershipWSStub.CourseMembershipVO[] bbCrsMemberships=null;
        if (userBbId != null && courseBbId != null ) {
            try {
                CourseMembershipWSStub.MembershipFilter membershipFilter = new CourseMembershipWSStub.MembershipFilter();
                membershipFilter.setFilterType(BY_CRS_ID_AND_USER_ID);
                membershipFilter.setUserIds(new String[]{userBbId});

                CourseMembershipWSStub.GetCourseMembership courseMembership = new CourseMembershipWSStub.GetCourseMembership();
                courseMembership.setF(membershipFilter);
                courseMembership.setCourseId(courseBbId);

                CourseMembershipWSStub courseMembershipWSStub = new CourseMembershipWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/CourseMembership.WS");
                getContextService().client_engage(courseMembershipWSStub._getServiceClient());
                CourseMembershipWSStub.GetCourseMembershipResponse courseMembershipResponse = courseMembershipWSStub.getCourseMembership(courseMembership);
                bbCrsMemberships = courseMembershipResponse.get_return();
            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return bbCrsMemberships;
    }



    ContextServiceImpl getContextService() {
        return contextService;
    }

    public void setContextService(ContextServiceImpl contextService) {
        this.contextService = contextService;
    }

    CourseServiceImpl getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    ConnectorUtil getConnectorUtil() {
        return connectorUtil;
    }

    public void setConnectorUtil(ConnectorUtil connectorUtil) {
        this.connectorUtil = connectorUtil;
    }
}
