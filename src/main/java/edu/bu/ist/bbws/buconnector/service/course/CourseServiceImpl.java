package edu.bu.ist.bbws.buconnector.service.course;

import edu.bu.ist.bbws._generated.context.ContextWSStub;
import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.buconnector.service.gradebook.GradebookService;
import edu.bu.ist.bbws.buconnector.service.user.UserService;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkousheh on 8/24/14.
 */
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = Logger.getLogger(CourseServiceImpl.class.getName());

    private ContextService contextService;
    private CoursemembershipService coursemembershipService;
    private GradebookService gradebookService;
    private UserService userService;
    private ConnectorUtil connectorUtil;

    /**
     *  this method gets all Courses in Blackboard
     * @return
     * @throws RemoteException
     */
    @Override
    public List<String> getBlackboardCourses()
            throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs = null;
        List<String> courseTitles = new ArrayList<String>();
        try{
            // get courses objects fot the list of courses found in user's membership
            CourseWSStub.GetCourse courses = new CourseWSStub.GetCourse();
            CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
            courseFilter.setFilterType(0);
            courses.setFilter(courseFilter);
            CourseWSStub courseWSStub = new CourseWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Course.WS");
            getContextService().client_engage(courseWSStub._getServiceClient());

            CourseWSStub.GetCourseResponse getCourseResponse = courseWSStub.getCourse(courses);
            courseVOs = getCourseResponse.get_return();
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        for (CourseWSStub.CourseVO courseVO : courseVOs) {
            courseTitles.add(courseVO.getName());
        }
        return courseTitles;
    }

    /**
     * retrieve course object for a given course id
     * @param courseId
     * @return
     * @throws RemoteException
     */
    public CourseWSStub.CourseVO getCourseById( String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs;
        CourseWSStub.CourseVO courseVO= null;
        try {
            CourseWSStub.GetCourse getCourse = new CourseWSStub.GetCourse();
            CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
            courseFilter.setFilterType(5);
            courseFilter.setSearchKey("CourseId");
            courseFilter.setSearchOperator("StartsWith");
            courseFilter.setSearchValue(courseId);
            Long the_time = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10)); //10 digit Long - seconds precision
            courseFilter.setSearchDate(the_time);
            courseFilter.setSearchDateOperator("LessThan");

            getCourse.setFilter(courseFilter);

            CourseWSStub courseWSStub = new CourseWSStub(ctx, "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Course.WS");
            getContextService().client_engage(courseWSStub._getServiceClient());
            CourseWSStub.GetCourseResponse getCourseResponse = courseWSStub.getCourse(getCourse);
            courseVOs = getCourseResponse.get_return();
            if (courseVOs != null) {
                courseVO = courseVOs[0];
            }
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return courseVO;

    }

    /**
     * get all bb courses for a given user
     * @param username
     * @return
     * @throws RemoteException
     */
    @Override
    public CourseWSStub.CourseVO[] getBlackboardCoursesForUser(String username)
            throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs = null;

        try {

            ContextWSStub.CourseIdVO[] courseIdVOs = getContextService().getMembershipFromContext (username);

            ArrayList<String> courseIds = new ArrayList<String>();
            if (courseIdVOs != null) {
                for (ContextWSStub.CourseIdVO courseIdVO : courseIdVOs) {
                    if (courseIdVO != null) {
                        courseIds.add(courseIdVO.getExternalId());
                    }
                }
            }
            // get courses objects fot the list of courses found in user's membership
            CourseWSStub.GetCourse courses = new CourseWSStub.GetCourse();
            CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
            courseFilter.setFilterType(3); //3 - Load by ids and (course|org) flag


            String[] courseIdsAsArr = new String[courseIds.size()];
            courseIdsAsArr = (String[])courseIds.toArray(courseIdsAsArr);

            courseFilter.setIds(courseIdsAsArr);
            courses.setFilter(courseFilter);
            CourseWSStub courseWSStub = new CourseWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Course.WS");
            getContextService().client_engage(courseWSStub._getServiceClient());

            CourseWSStub.GetCourseResponse getCourseResponse = courseWSStub.getCourse(courses);
            courseVOs = getCourseResponse.get_return();

        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }

        return courseVOs;
    }

    /**
     * gets all courses for given username and rolename
      * @param username
     * @param role
     * @return
     * @throws RemoteException
     */
    @Override
    public CourseWSStub.CourseVO[] getBlackboardCoursesForUserByRole(String username, String role)
            throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs = null;
        try{

            // get user's membership
            ContextWSStub.CourseIdVO[] courseIdVOs = getContextService().getMembershipFromContext (username);

            String[] courseIds = new String[courseIdVOs.length];
            int i = 0;
            for (ContextWSStub.CourseIdVO courseIdVO : courseIdVOs) {
                courseIds[i] = courseIdVO.getExternalId();
                i++;
            }

            String roleInternalId = getCoursemembershipService().getRole(role).getRoleIdentifier();
            String userInternalId = getUserService().getUserByUsername(username).getId();

            logger.info("before");
            ArrayList<String> filteredCourseIds = new ArrayList<String>();
            for (String courseId : courseIds){
                CourseMembershipWSStub.CourseMembershipVO[] courseMemberships = getCoursemembershipService().getCourseMembershipByKey (userInternalId, courseId);
                for (CourseMembershipWSStub.CourseMembershipVO courseMembership : courseMemberships){
                    if (courseMembership.getRoleId().equals(roleInternalId)){
                        filteredCourseIds.add(courseId);
                    }
                }
            }

            String[] fCourseIds = filteredCourseIds.toArray(new String[filteredCourseIds.size()]);

            // get courses objects fot the list of courses found in user's membership
            CourseWSStub.GetCourse courses = new CourseWSStub.GetCourse();
            CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
            courseFilter.setFilterType(3); //3 - Load by ids and (course|org) flag
            courseFilter.setIds(fCourseIds);
            courses.setFilter(courseFilter);
            CourseWSStub courseWSStub = new CourseWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Course.WS");
            getContextService().client_engage(courseWSStub._getServiceClient());

            CourseWSStub.GetCourseResponse getCourseResponse = courseWSStub.getCourse(courses);
            courseVOs = getCourseResponse.get_return();

        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        logger.info("after");
        return courseVOs;
    }













    public List<String> getCoursesForUserInRole(String modulePath,
                                                String blackboardServerURL, String sharedSecret, String vendorId,
                                                String clientProgramId, String userId, String role) throws RemoteException {

        return null;
    }

    public ContextService getContextService() {
        return contextService;
    }

    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }

    public CoursemembershipService getCoursemembershipService() {
        return coursemembershipService;
    }

    public void setCoursemembershipService(CoursemembershipService coursemembershipService) {
        this.coursemembershipService = coursemembershipService;
    }

    public GradebookService getGradebookService() {
        return gradebookService;
    }

    public void setGradebookService(GradebookService gradebookService) {
        this.gradebookService = gradebookService;
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
