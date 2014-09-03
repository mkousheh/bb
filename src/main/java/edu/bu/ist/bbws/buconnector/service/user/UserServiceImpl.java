package edu.bu.ist.bbws.buconnector.service.user;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.buconnector.model.CourseMembership;
import edu.bu.ist.bbws.buconnector.model.User;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.context.ContextServiceImpl;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.course.CourseServiceImpl;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipServiceImpl;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkousheh on 8/24/14.
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());


    @Autowired
    private ConnectorUtil connectorUtil;
    @Autowired
    private ContextServiceImpl contextService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private CoursemembershipServiceImpl coursemembershipService;

    /**
     * gets user object by user name
     *
     * @param userBbId - bb internal identifier
     * @return user object by given bb user id
     * @throws RemoteException
     */
    public UserWSStub.UserVO getUserByUserBbId(String userBbId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        UserWSStub.UserVO user = null;
        try {
            UserWSStub.UserFilter userFilter = new UserWSStub.UserFilter();
            userFilter.setFilterType(2);  // By BB Internal ID
            userFilter.setAvailable(Boolean.TRUE);
            userFilter.setId(new String[]{userBbId});
            UserWSStub.GetUser getUser = new UserWSStub.GetUser();
            getUser.setFilter(userFilter);
            UserWSStub userWSStub = new UserWSStub(ctx, "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/User.WS");
            getContextService().client_engage(userWSStub._getServiceClient());
            UserWSStub.GetUserResponse userResponse = userWSStub.getUser(getUser);
            UserWSStub.UserVO[] userVOs = userResponse.get_return();
            if (userVOs != null) {
                user = userVOs[0];
            }
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return user;
    }


    /**
     * gets user object by user name
     *
     * @param username - bb batchId (alias)
     * @return user object by given user name (alias)
     * @throws RemoteException
     */
    public UserWSStub.UserVO getUserByUsername(String username) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        UserWSStub.UserVO user = null;
        try {
            UserWSStub.UserFilter userFilter = new UserWSStub.UserFilter();
            userFilter.setFilterType(3);  // GET_USER_BY_BATCH_ID_WITH_AVAILABILITY
            userFilter.setAvailable(Boolean.TRUE);
            userFilter.setBatchId(new String[]{username});
            UserWSStub.GetUser getUser = new UserWSStub.GetUser();
            getUser.setFilter(userFilter);
            UserWSStub userWSStub = new UserWSStub(ctx, "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/User.WS");
            getContextService().client_engage(userWSStub._getServiceClient());
            UserWSStub.GetUserResponse userResponse = userWSStub.getUser(getUser);
            UserWSStub.UserVO[] userVOs = userResponse.get_return();
            if (userVOs != null) {
                user = userVOs[0];
            }
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return user;
    }

    /**
     * gets all course's users for a given course id
     *
     * @param courseId - bb course identifier
     * @return list of users for a given course id
     * @throws RemoteException
     */
    public UserWSStub.UserVO[] getCourseUsersByCourseId(String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        UserWSStub.UserVO[] bbCrsUsers = null;

        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null) {
            courseInternalId = course.getId();

            try {
                UserWSStub.UserFilter userFilter = new UserWSStub.UserFilter();
                userFilter.setFilterType(4);  // GET_USER_BY_COURSE_ID_WITH_AVAILABILITY	4
                userFilter.setAvailable(Boolean.FALSE);

                userFilter.setCourseId(new String[]{courseInternalId});

                UserWSStub.GetUser getUser = new UserWSStub.GetUser();
                getUser.setFilter(userFilter);

                UserWSStub userWSStub = new UserWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/User.WS");
                getContextService().client_engage(userWSStub._getServiceClient());
                UserWSStub.GetUserResponse userResponse = userWSStub.getUser(getUser);
                bbCrsUsers = userResponse.get_return();

            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return bbCrsUsers;
    }

    /**
     * gets all course's users for a given course id
     *
     * @param courseId - bb course identifier
     * @return list of users for a given course id
     * @throws RemoteException
     */
    public List<UserWSStub.UserVO> getCourseEnrolledStudents(String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        UserWSStub.UserVO[] UserVOs = null;
        List<UserWSStub.UserVO> courseEnrolledStudents = new ArrayList<UserWSStub.UserVO>();

        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null) {
            courseInternalId = course.getId();

            try {
                UserWSStub.UserFilter userFilter = new UserWSStub.UserFilter();
                userFilter.setFilterType(4);  // GET_USER_BY_COURSE_ID_WITH_AVAILABILITY	4
                userFilter.setAvailable(Boolean.TRUE);

                userFilter.setCourseId(new String[]{courseInternalId});

                UserWSStub.GetUser getUser = new UserWSStub.GetUser();
                getUser.setFilter(userFilter);

                UserWSStub userWSStub = new UserWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/User.WS");
                getContextService().client_engage(userWSStub._getServiceClient());
                UserWSStub.GetUserResponse userResponse = userWSStub.getUser(getUser);
                UserVOs = userResponse.get_return();

            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        int i=0;
        if (UserVOs != null) {
            for (UserWSStub.UserVO userVO: UserVOs) {
                List<CourseMembership> courseMemberships= new ArrayList<CourseMembership>();
                CourseMembershipWSStub.CourseMembershipVO[] courseMembershipVOs = coursemembershipService.getCourseMembership(userVO.getName(), courseId);
                for (CourseMembershipWSStub.CourseMembershipVO courseMembershipVO:courseMembershipVOs){
                    if (courseMembershipVO != null) {
                        courseMemberships.add(new CourseMembership(courseMembershipVO, getCourseService(), this, coursemembershipService));
                        if (coursemembershipService.getCourseMembershipRoleById(courseMembershipVO.getRoleId()).getRoleIdentifier().toString().equalsIgnoreCase("S")) {
                            courseEnrolledStudents.add(userVO);
                            i++;
                        }
                    }
                }
            }
        }
        return courseEnrolledStudents;

    }

    /**
     * gets all course's users for a given course id
     *
     * @param courseId - bb course identifier
     * @return list of users for a given course id
     * @throws RemoteException
     */
    public List<UserWSStub.UserVO> getCourseInstructors(String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        UserWSStub.UserVO[] UserVOs = null;
        List<UserWSStub.UserVO> courseInstructors = new ArrayList<UserWSStub.UserVO>();

        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null) {
            courseInternalId = course.getId();

            try {
                UserWSStub.UserFilter userFilter = new UserWSStub.UserFilter();
                userFilter.setFilterType(4);  // GET_USER_BY_COURSE_ID_WITH_AVAILABILITY	4
                userFilter.setAvailable(Boolean.FALSE);

                userFilter.setCourseId(new String[]{courseInternalId});

                UserWSStub.GetUser getUser = new UserWSStub.GetUser();
                getUser.setFilter(userFilter);

                UserWSStub userWSStub = new UserWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/User.WS");
                getContextService().client_engage(userWSStub._getServiceClient());
                UserWSStub.GetUserResponse userResponse = userWSStub.getUser(getUser);
                UserVOs = userResponse.get_return();

            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        if (UserVOs != null) {
            for (UserWSStub.UserVO userVO: UserVOs) {
                List<CourseMembership> courseMemberships= new ArrayList<CourseMembership>();
                CourseMembershipWSStub.CourseMembershipVO[] courseMembershipVOs = coursemembershipService.getCourseMembership(userVO.getName(), courseId);
                for (CourseMembershipWSStub.CourseMembershipVO courseMembershipVO:courseMembershipVOs){
                    if (courseMembershipVO != null) {
                        courseMemberships.add(new CourseMembership(courseMembershipVO, getCourseService(), this, coursemembershipService));
                        if (coursemembershipService.getCourseMembershipRoleById(courseMembershipVO.getRoleId()).getRoleIdentifier().toString().toLowerCase().contains("instructor")) {
                            courseInstructors.add(userVO);
                        }
                    }
                }
            }
        }
        return courseInstructors;
    }




    public ContextServiceImpl getContextService() {
        return contextService;
    }

    public void setContextService(ContextServiceImpl contextService) {
        this.contextService = contextService;
    }

    public CourseServiceImpl getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    public ConnectorUtil getConnectorUtil() {
        return connectorUtil;
    }

    public void setConnectorUtil(ConnectorUtil connectorUtil) {
        this.connectorUtil = connectorUtil;
    }

    public CoursemembershipServiceImpl getCoursemembershipService() {
        return coursemembershipService;
    }

    public void setCoursemembershipService(CoursemembershipServiceImpl coursemembershipService) {
        this.coursemembershipService = coursemembershipService;
    }
}
