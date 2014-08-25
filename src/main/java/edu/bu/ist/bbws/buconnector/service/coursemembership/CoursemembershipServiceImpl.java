package edu.bu.ist.bbws.buconnector.service.coursemembership;

import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.user.UserService;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public class CoursemembershipServiceImpl implements CoursemembershipService {
    private static final Logger logger = Logger.getLogger(CoursemembershipServiceImpl.class.getName());

    private ContextService contextService;
    private CourseService courseService;
    private UserService userService;
    private ConnectorUtil connectorUtil;

    /**
     * gets a role object by role name
      * @param role
     * @return
     * @throws RemoteException
     */
    public CourseMembershipWSStub.CourseMembershipRoleVO getRole(String role) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseMembershipWSStub.CourseMembershipRoleVO[] courseMembershipRoles = null;
        CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRole = null;

        try {
            CourseMembershipWSStub.CourseRoleFilter courseRolesFilter = new CourseMembershipWSStub.CourseRoleFilter();
            courseRolesFilter.setRoleIds(new String[]{role});

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
        for (CourseMembershipWSStub.CourseMembershipRoleVO rol: courseMembershipRoles){
            if (rol.getCourseRoleDescription().replace(":","").compareToIgnoreCase(role)==0) {
                courseMembershipRole = rol;
                logger.error(rol.getCourseRoleDescription());
                break;
            }
        }
        return courseMembershipRole;
    }

    /**
     * get course membership for a given username and a course id
     * @param username
     * @param courseId
     * @return
     * @throws RemoteException
     */
    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembership(String username, String courseId) throws RemoteException {
        String userInternalId = getUserService().getUserByUsername(username).getId();
        String courseInternalId = getCourseService().getCourseById(courseId).getId();

        return getCourseMembershipByKey(userInternalId, courseInternalId);
    }

    /**
     * get course membership for a given username and a course id
     * @param userInternalId
     * @param courseInternalId
     * @return
     * @throws RemoteException
     */
    public CourseMembershipWSStub.CourseMembershipVO[] getCourseMembershipByKey(String userInternalId, String courseInternalId) throws RemoteException {

        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseMembershipWSStub.CourseMembershipVO[] bbCrsMemberships=null;
        try {

            CourseMembershipWSStub.MembershipFilter membershipFilter = new CourseMembershipWSStub.MembershipFilter();
            membershipFilter.setFilterType(5); //records by course and user Id's.
            membershipFilter.setUserIds(new String[]{userInternalId});

            CourseMembershipWSStub.GetCourseMembership courseMembership = new CourseMembershipWSStub.GetCourseMembership();
            courseMembership.setF(membershipFilter);
            courseMembership.setCourseId(courseInternalId);

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

    public ContextService getContextService() {
        return contextService;
    }

    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ConnectorUtil getConnectorUtil() {
        return connectorUtil;
    }

    public void setConnectorUtil(ConnectorUtil connectorUtil) {
        this.connectorUtil = connectorUtil;
    }
}
