package edu.bu.ist.bbws.buconnector.controller;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.buconnector.bean.*;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.buconnector.service.gradebook.GradebookService;
import edu.bu.ist.bbws.buconnector.service.user.UserService;
import edu.bu.ist.bbws.buconnector.service.user.UserServiceImpl;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkousheh on 8/20/14.
 */
public class BuConnectorController {
    private static final Logger logger = Logger.getLogger(BuConnectorController.class.getName());

    /*
     * Values for these instance fields are injected using Spring.
     * See the Spring configuration file applicationContext_BlackboardCoursesForUser.xml.
     */
    private ConfigurationContext ctx;

    private ContextService contextService;
    private CourseService courseService;
    private CoursemembershipService coursemembershipService;
    private GradebookService gradebookService;
    private UserService userService;
    private ConnectorUtil connectorUtil;


    public BuConnectorController() {
    }



    public void login() {
        try {
            boolean loginResult = getContextService().login();
            logger.info("login flag is: " + loginResult);
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get login to the Blackboard web service" + e.getMessage());
        }

    }

    /**
     * get all Blackboard courses
     */
    public List<Course> getBlackboardCourses() {
        List<Course> courses = new ArrayList<Course>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getBlackboardCourses();
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new Course(courseVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying getBlackboardCourses : " + e.getMessage());
        }
        return courses;
    }

    /**
     * gets course object for a given BB Internal id ("_99999_9" format)
     *
     * @param courseBbId
     */
    public Course getCourseByBbId(String courseBbId) {
        Course course = null;
        try {
            CourseWSStub.CourseVO courseV0 = getCourseService().getCourseByBbId(courseBbId);
            if (courseV0 != null) {
                course = new Course(courseV0);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard title for course (" + courseBbId + ") : " + e.getMessage());
        }
        return course;
    }
    /**
     * gets course object for a given course id
     *
     * @param courseId
     */
    public Course getCourseById(String courseId) {
        Course course = null;
        try {
            CourseWSStub.CourseVO courseV0 = getCourseService().getCourseById(courseId);
            if (courseV0 != null) {
                course = new Course(courseV0);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard title for course (" + courseId + ") : " + e.getMessage());
        }
        return course;
    }

    /**
     * gets user object by given user bb internal id ("_99999_9" format)
     *
     * @param userBbId
     */
    public User getUserByUserBbId(String userBbId) {
        User user = null;
        try {
            UserWSStub.UserVO userVO = getUserService().getUserByUserBbId(userBbId);
            if (userVO != null) {
                user = new User(userVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get user info for username  (" + userBbId + ") : " + e.getMessage());
        }
        return user;
    }

    /**
     * gets user object by given username
     *
     * @param username
     */
    public User getUserByUsername(String username) {
        User user = null;
        try {
            UserWSStub.UserVO userVO = getUserService().getUserByUsername(username);
            if (userVO != null) {
                user = new User(userVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get user info for username  (" + username + ") : " + e.getMessage());
        }
        return user;
    }

    /**
     * gets all users for a given course id
     *
     * @param courseId
     */
    public List<User> getCourseUsersByCourseId(String courseId) {
        List<User> users = new ArrayList<User>();
        try {
            UserWSStub.UserVO[] userVOs = getUserService().getCourseUsersByCourseId(courseId);
            if (userVOs != null){
                for (UserWSStub.UserVO userVO : userVOs) {
                    users.add(new User(userVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get Course users for course id  (" + courseId + ") : " + e.getMessage());
        }
        return users;
    }

    /**
     * gets a role object per given role name
     *
     * @param courseMembershipRoleName
     */
    public CourseMembershipRole getCourseMembershipRoleByName(String courseMembershipRoleName) {
        CourseMembershipRole courseMembershipRole = null;
        try {
            CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO = coursemembershipService.getCourseMembershipRoleByName(courseMembershipRoleName);
            if (courseMembershipRoleVO != null){
                courseMembershipRole = new CourseMembershipRole(courseMembershipRoleVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the course membership role  for role name (" + courseMembershipRoleName + ") : " + e.getMessage());
        }
        return courseMembershipRole;
    }

    /**
     * gets a role object per given role name
     *
     * @param courseMembershipRoleId
     */
    public CourseMembershipRole getCourseMembershipRoleById(String courseMembershipRoleId) {
        CourseMembershipRole courseMembershipRole = null;
        try {
            CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO = coursemembershipService.getCourseMembershipRoleById(courseMembershipRoleId);
            if (courseMembershipRoleVO != null){
                courseMembershipRole = new CourseMembershipRole(courseMembershipRoleVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the course membership role  for role id (" + courseMembershipRoleId + ") : " + e.getMessage());
        }
        return courseMembershipRole;
    }


    /**
     * get all bb courses for a given user
     *
     * @param username
     */
    public List<Course> getCoursesForUser(String username) {
        List<Course> courses = new ArrayList<Course>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getCoursesForUser(username);
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new Course(courseVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the courses for user (" + username + ") : " + e.getMessage());
        }
        return courses;
    }

    /**
     * gets courses for given username and a role id
     *
     * @param username
     * @param courseMembershipRoleName
     */
    public List<Course> getCoursesForUserInRole(String username, String courseMembershipRoleName) {
        List<Course> courses = new ArrayList<Course>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getCoursesForUserInRole(username, courseMembershipRoleName);
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new Course(courseVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the courses for user (" + username + ") in role ("  + courseMembershipRoleName +") : " + e.getMessage());
        }
        return courses;
    }

    /**
     * get course membership for a given username and a course id
     *
     * @param username
     * @param courseId
     */
    public List<CourseMembership> getCourseMembership(String username, String courseId) {
        List<CourseMembership> courseMemberships = new ArrayList<CourseMembership>();
        try {
            CourseMembershipWSStub.CourseMembershipVO[] courseMembershipVOs = getCoursemembershipService().getCourseMembership(username, courseId);
            if (courseMembershipVOs != null){
                for (CourseMembershipWSStub.CourseMembershipVO courseMembershipVO : courseMembershipVOs){

                    CourseMembership membership = new CourseMembership();

                    membership.setUser(loadUserByKey(courseMembershipVO.getUserId()));
                    membership.setBbId(courseMembershipVO.getId());
                    membership.setAvailable(courseMembershipVO.getAvailable());
                    membership.setEnrollmentDate(new Date(courseMembershipVO.getEnrollmentDate()*1000));
                    membership.setCourse(loadCourseByKey(courseMembershipVO.getCourseId()));
                    membership.setCourseMembershipRole(loadCourseMembershipRoleByKey(courseMembershipVO.getRoleId()));

                    courseMemberships.add(membership);

//                    courseMemberships.add(new CourseMembership(courseMembershipVO));

                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get members for course id  " + courseId + ": " + e.getMessage());
        }
        return courseMemberships;
    }


    /**
     * gets course columns for a given course Id
     *
     * @param courseId
     */
    public List<Column> getCourseColumns(String courseId) {
        List<Column> columns = new ArrayList<Column>();

        try {
            GradebookWSStub.ColumnVO[] columnVOs = getGradebookService().getCourseColumns(courseId);
            if (columnVOs != null) {
                for (GradebookWSStub.ColumnVO columnVO : columnVOs) {
                    columns.add(new Column(columnVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get members for user id  " + courseId + ": " + e.getMessage());
        }
    return columns;
    }

    /**
     * gets course column by column name for a course
     *
     * @param courseId
     * @param columnName
     */
    public Column getCourseColumnByColumnName(String courseId, String columnName) {
        Column column = null;
        try {
            GradebookWSStub.ColumnVO columnVO = getGradebookService().getCourseColumnByColumnName(courseId, columnName);
            if (columnVO != null) {
                column = new Column(columnVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get column object for course id  " + courseId + ": " + e.getMessage());
        }
    return column;
    }

    /**
     * gets all course scores
     *
     * @param courseId
     */
    public List<Score> getCourseTotalScore(String courseId) {
        List<Score> scores = new ArrayList<Score>();
        try {
            GradebookWSStub.ScoreVO[] scoreVOs = getGradebookService().getCourseTotalScore(courseId);
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get Course users for course id  " + courseId + ": " + e.getMessage());
        }
    return scores;
    }

    /**
     * gets all scores by column for a course
     *
     * @param courseId
     * @param columnName
     */
    public List<Score> getCourseScoreByColumn(String courseId, String columnName) {
        List<Score> scores = new ArrayList<Score>();
        try {
            GradebookWSStub.ScoreVO[] scoreVOs = getGradebookService().getCourseScoreByColumn(courseId, columnName);
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get scores for course id  " + courseId + " for column (" + columnName + "): " + e.getMessage());
        }
    return scores;
    }

    /**
     * @param courseId
     * @param columnName
     * @param submissionDate
     */
    public void getCourseScoreByColumnAfterSubmissionDate(String courseId, String columnName, String submissionDate) {
        try {
            GradebookWSStub.ScoreVO[] scores = getGradebookService().getCourseScoreByColumnAfterSubmissionDate(courseId, columnName, submissionDate);
            logger.info("Scores for Course id " + courseId + " and column " + columnName + " are:");
            if (scores != null) {
                for (GradebookWSStub.ScoreVO score : scores) {
                    logger.info(score.getColumnId() + " " + score.getGrade() + " member id:" + score.getMemberId());
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get Course users for course id  " + courseId + ": " + e.getMessage());
        }

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





    public User loadUserByKey(String userBbId) {
        User user = new User();
        UserWSStub.UserVO userVO;
        try {
            userVO = getUserService().getUserByUserBbId(userBbId);
            if (userVO != null){
                user.setAvailable(userVO.getIsAvailable());
                user.setBbLocalId(userVO.getId());
                user.setEducationLevel(userVO.getEducationLevel());
                user.setEmailAddress(userVO.getExtendedInfo().getEmailAddress());
                user.setFamilyName(userVO.getExtendedInfo().getFamilyName());
                user.setGivenName(userVO.getExtendedInfo().getGivenName());
                user.setGenderType(userVO.getGenderType());
                user.setStudentId(userVO.getStudentId());
                user.setTitle(userVO.getTitle());
                user.setUserName(userVO.getName());
                if (userVO.getInsRoles() != null) {
                    List<String> instRoles = new ArrayList<String>();
                    for (String insRole : userVO.getInsRoles()) {
                        instRoles.add(insRole);
                    }
                    user.setInsRoles(instRoles);
                }
                if (userVO.getSystemRoles() != null) {
                    List<String> systemRoles = new ArrayList<String>();
                    for (String systemRole : userVO.getSystemRoles()) {
                        systemRoles.add(systemRole);
                    }
                    user.setSystemRoles(systemRoles);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    return user;
    }

public Course loadCourseByKey(String courseBbId){
    Course course = new Course();
    CourseWSStub.CourseVO courseVO;
    try {
    courseVO = getCourseService().getCourseByBbId(courseBbId);
    if (courseVO != null) {
        course.setBbId(courseVO.getId());
        course.setCourseId(courseVO.getCourseId());
        course.setBatchUid(courseVO.getBatchUid());
        course.setName(courseVO.getName());
        course.setDescription(courseVO.getDescription());
        course.setEnrollmentType(courseVO.getEnrollmentType());
        course.setDuration(courseVO.getCourseDuration());
        course.setAvailable(courseVO.getAvailable());
        course.setInstitutionName(courseVO.getInstitutionName());
        course.setCourseServiceLevel(courseVO.getCourseServiceLevel());
    }
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    return course;
}



    public CourseMembershipRole loadCourseMembershipRoleByKey(String courseMembershipRoleId){
        CourseMembershipRole courseMembershipRole = new CourseMembershipRole();
        CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO;
        try {
            courseMembershipRoleVO = getCoursemembershipService().getCourseMembershipRoleById(courseMembershipRoleId);
            if (courseMembershipRoleVO != null) {
                courseMembershipRole.setBbRoleId(courseMembershipRoleVO.getRoleIdentifier());
                courseMembershipRole.setCourseRoleDescription(courseMembershipRoleVO.getCourseRoleDescription());
                courseMembershipRole.setOrgRoleDescription(courseMembershipRoleVO.getOrgRoleDescription());
                courseMembershipRole.setDefaultRole(courseMembershipRoleVO.getDefaultRole());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return courseMembershipRole;
    }
}
