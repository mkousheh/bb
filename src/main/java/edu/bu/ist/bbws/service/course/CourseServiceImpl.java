package edu.bu.ist.bbws.service.course;

import edu.bu.ist.bbws._generated.context.ContextWSStub;
import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.service.context.ContextServiceImpl;
import edu.bu.ist.bbws.service.coursemembership.CoursemembershipServiceImpl;
import edu.bu.ist.bbws.service.gradebook.GradebookServiceImpl;
import edu.bu.ist.bbws.service.user.UserServiceImpl;
import edu.bu.ist.bbws.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by mkousheh on 8/24/14.
 */
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = Logger.getLogger(CourseServiceImpl.class.getName());

    //Course Filter for Course Web Service
    public static final int GET_ALL_COURSES = 0;
    public static final int GET_COURSE_BY_COURSEID = 1;
    public static final int GET_COURSE_BY_BATCHUID = 2;
    public static final int GET_COURSE_BY_ID = 3;
    public static final int GET_COURSE_BY_CATEGORY_ID = 4;
    public static final int GET_COURSE_BY_SEARCH_KEYVALUE = 5;
    @Autowired
    private ContextServiceImpl contextService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CoursemembershipServiceImpl coursemembershipService;
    @Autowired
    private GradebookServiceImpl gradebookService;
    @Autowired
    private ConnectorUtil connectorUtil;

    /**
     *  this method gets all Courses in Blackboard
     * @return list of bb courses
     * @throws RemoteException
     */
    @Override
    public CourseWSStub.CourseVO[] getBlackboardCourses()
            throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs = null;

        try{
            // get courses objects fot the list of courses found in user's membership
            CourseWSStub.GetCourse courses = new CourseWSStub.GetCourse();
            CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
            courseFilter.setFilterType(GET_ALL_COURSES);
            courses.setFilter(courseFilter);
            CourseWSStub courseWSStub = new CourseWSStub(ctx,  "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Course.WS");
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
             * retrieve course object for a given course id
             * @param courseBbId - bb local course identifier
             * @return bb course if exist
             * @throws RemoteException
             */
    public CourseWSStub.CourseVO getCourseByBbId( String courseBbId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs;
        CourseWSStub.CourseVO courseVO= null;
        try {
            CourseWSStub.GetCourse getCourse = new CourseWSStub.GetCourse();
            CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
            courseFilter.setFilterType(GET_COURSE_BY_ID);
            courseFilter.setIds(new String[]{courseBbId});
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
     * retrieve course object for a given course id
     * @param courseId - bb course identifier
     * @return bb course if exist
     * @throws RemoteException
     */
    public CourseWSStub.CourseVO getCourseById( String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs;
        CourseWSStub.CourseVO courseVO= null;
        try {
            CourseWSStub.GetCourse getCourse = new CourseWSStub.GetCourse();
            CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
            courseFilter.setCourseIds(new String[]{courseId});
            courseFilter.setFilterType(GET_COURSE_BY_COURSEID);
/*            courseFilter.setFilterType(5);
            courseFilter.setSearchKey("CourseId");
            courseFilter.setSearchOperator("StartsWith");
            courseFilter.setSearchValue(courseId);
            Long the_time = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10)); //10 digit Long - seconds precision
            courseFilter.setSearchDate(the_time);
            courseFilter.setSearchDateOperator("LessThan");*/

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
     * @param username - bb batchId (alias)
     * @return list of bb courses
     * @throws RemoteException
     */
    @Override
    public CourseWSStub.CourseVO[] getCoursesForUser(String username)
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
            courseFilter.setFilterType(GET_COURSE_BY_ID);

            String[] courseIdsAsArr = courseIds.toArray(new String[courseIds.size()]);

            if (courseIdsAsArr.length > 0) {
                courseFilter.setIds(courseIdsAsArr);
                courses.setFilter(courseFilter);
                CourseWSStub courseWSStub = new CourseWSStub(ctx, "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Course.WS");
                getContextService().client_engage(courseWSStub._getServiceClient());
                CourseWSStub.GetCourseResponse getCourseResponse = courseWSStub.getCourse(courses);
                courseVOs = getCourseResponse.get_return();
            }
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
      * @param username - bb batchId (alias)
     * @param courseMembershipRoleName - bb course membership role display name
     * @return list of bb courses
     * @throws RemoteException
     */
    @Override
    public CourseWSStub.CourseVO[] getCoursesForUserInRole(String username, String courseMembershipRoleName)
            throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        CourseWSStub.CourseVO[] courseVOs = null;
        try{
            // get user's membership
            ContextWSStub.CourseIdVO[] courseIdVOs = getContextService().getMembershipFromContext (username);

            ArrayList<String> courseIds = new ArrayList<String>();
            if (courseIdVOs != null) {
                for (ContextWSStub.CourseIdVO courseIdVO : courseIdVOs) {
                    if (courseIdVO != null) {
                        courseIds.add(courseIdVO.getExternalId());
                    }
                }
            }

            String roleInternalId = null;
            CourseMembershipWSStub.CourseMembershipRoleVO role = getCoursemembershipService().getCourseMembershipRoleByName(courseMembershipRoleName);
            if (role != null) {
                roleInternalId = role.getRoleIdentifier();
            }

            String userInternalId = null;
            UserWSStub.UserVO user = getUserService().getUserByUsername(username);
            if (user != null) {
                userInternalId = user.getId();
            }

            if (userInternalId != null & roleInternalId != null) {
                ArrayList<String> filteredCourseIds = new ArrayList<String>();
                for (String courseId : courseIds) {
                    CourseMembershipWSStub.CourseMembershipVO[] courseMemberships = getCoursemembershipService().getCourseMembershipByUserBbIdAndCourseBbId(userInternalId, courseId);
                    for (CourseMembershipWSStub.CourseMembershipVO courseMembership : courseMemberships) {
                        if (courseMembership.getRoleId().equals(roleInternalId)) {
                            filteredCourseIds.add(courseId);
                        }
                    }
                }

                String[] fCourseIds = filteredCourseIds.toArray(new String[filteredCourseIds.size()]);

                // get courses objects fot the list of courses found in user's membership
                if (fCourseIds.length > 0) {
                    CourseWSStub.GetCourse courses = new CourseWSStub.GetCourse();
                    CourseWSStub.CourseFilter courseFilter = new CourseWSStub.CourseFilter();
                    courseFilter.setFilterType(GET_COURSE_BY_ID);
                    courseFilter.setIds(fCourseIds);
                    courses.setFilter(courseFilter);
                    CourseWSStub courseWSStub = new CourseWSStub(ctx,  "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Course.WS");
                    getContextService().client_engage(courseWSStub._getServiceClient());
                    CourseWSStub.GetCourseResponse getCourseResponse = courseWSStub.getCourse(courses);
                    courseVOs = getCourseResponse.get_return();
                }
            }
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return courseVOs;
    }


    ContextServiceImpl getContextService() {
        return contextService;
    }

    public void setContextService(ContextServiceImpl contextService) {
        this.contextService = contextService;
    }

    CoursemembershipServiceImpl getCoursemembershipService() {
        return coursemembershipService;
    }

    public void setCoursemembershipService(CoursemembershipServiceImpl coursemembershipService) {
        this.coursemembershipService = coursemembershipService;
    }

    public GradebookServiceImpl getGradebookService() {
        return gradebookService;
    }

    public void setGradebookService(GradebookServiceImpl gradebookService) {
        this.gradebookService = gradebookService;
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
