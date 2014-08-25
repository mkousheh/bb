package edu.bu.ist.bbws.buconnector.controller;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.buconnector.bean.Course;
import edu.bu.ist.bbws.buconnector.bean.User;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.buconnector.service.gradebook.GradebookService;
import edu.bu.ist.bbws.buconnector.service.user.UserService;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;
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
    public void getBlackboardCourses() {
        try {
            List<String> courseTitles = getCourseService().getBlackboardCourses();
            logger.info("Course in blackboard  are: " + courseTitles);
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard courses " + e.getMessage());
        }
    }


    /**
     * gets course object for a given course id
     *
     * @param courseId
     */
    public Course getCourseById(String courseId) {
        Course course = new Course();
        try {
            CourseWSStub.CourseVO courseV0 = getCourseService().getCourseById(courseId);
            if (courseV0 != null) {
                course = new Course(courseV0);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard title for course " + courseId + ": " + e.getMessage());
        }
        return course;
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
            logger.info("Users for Course id " + courseId + " are ");
            if (userVOs != null){
                for (UserWSStub.UserVO userVO : userVOs) {
                    users.add(new User(userVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get Course users for course id  " + courseId + ": " + e.getMessage());
        }
        return users;
    }


    /**
     * get all bb courses for a given user
     *
     * @param username
     */
    public void getBlackboardCoursesForUser(String username) {
        try {
            CourseWSStub.CourseVO[] courses = getCourseService().getBlackboardCoursesForUser(username);
            logger.info("Course titles for user " + username);
            if (courses != null) {
                for (CourseWSStub.CourseVO course : courses) {
                    logger.info("Name: " + course.getCourseId() + " " + course.getDescription());
                }
            } else {
                logger.info("No courses found for user " + username);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard courses for user " + username + ": " + e.getMessage());
        }
    }

    /**
     * gets user object by given username
     *
     * @param username
     */
    public void getUserByUsername(String username) {
        try {
            UserWSStub.UserVO userVO = getUserService().getUserByUsername(username);
            //TODO retun user
            // return new User(userVO)
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get user info for user id  " + username + ": " + e.getMessage());
        }
    }

    /**
     * gets a role object per given role name
     *
     * @param role
     */
    public void getRole(String role) {
        try {
            CourseMembershipWSStub.CourseMembershipRoleVO bbRole = coursemembershipService.getRole(role);
            logger.info("Course role " + role + " is " + bbRole.getCourseRoleDescription().replace(":", ""));
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the role object for given role (" + role + ") " + e.getMessage());
        }
    }

    /**
     * gets courses for given username and a rolename
     *
     * @param username
     * @param role
     */
    public void getBlackboardCoursesForUserByRole(String username, String role) {
        try {
            CourseWSStub.CourseVO[] courses = getCourseService().getBlackboardCoursesForUserByRole(username, role);
            logger.info("Course for user " + username + " in role " + role + "  are: ");
            for (CourseWSStub.CourseVO course : courses) {
                logger.info("Name: " + course.getCourseId() + " " + course.getDescription());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard courses for user " + username + ": " + e.getMessage());
        }
    }

    /**
     * get course membership for a given username and a course id
     *
     * @param username
     * @param courseId
     */
    public void getCourseMembership(String username, String courseId) {
        try {
            CourseMembershipWSStub.CourseMembershipVO[] memberships = getCoursemembershipService().getCourseMembership(username, courseId);
            logger.info("membership for Course id " + courseId + " are ");
            for (CourseMembershipWSStub.CourseMembershipVO membership : memberships) {
                logger.info("Course: " + membership.getCourseId() + " enrolled on: " + membership.getEnrollmentDate());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get members for course id  " + courseId + ": " + e.getMessage());
        }

    }

    /**
     * gets all course scores
     *
     * @param courseId
     */
    public void getCourseTotalScore(String courseId) {
        try {
            GradebookWSStub.ScoreVO[] scores = getGradebookService().getCourseTotalScore(courseId);
            logger.info("Scores for Course id " + courseId + " are ");
            for (GradebookWSStub.ScoreVO score : scores) {
                logger.info(score.getColumnId() + " " + score.getGrade() + " member id:" + score.getMemberId());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get Course users for course id  " + courseId + ": " + e.getMessage());
        }

    }

    /**
     * gets course columns for a given course Id
     *
     * @param courseId
     */
    public void getCourseColumns(String courseId) {
        try {
            GradebookWSStub.ColumnVO[] columns = getGradebookService().getCourseColumns(courseId);
            logger.info("Columns for Course id " + courseId + " are ");
            for (GradebookWSStub.ColumnVO column : columns) {
                logger.info("ID: " + column.getId() + "Name: " + column.getColumnName() + " Display Name: " + column.getColumnDisplayName());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get members for user id  " + courseId + ": " + e.getMessage());
        }
    }

    /**
     * gets course column by column name for a course
     *
     * @param courseId
     * @param columnName
     */
    public void getCourseColumnByColumnName(String courseId, String columnName) {
        try {
            GradebookWSStub.ColumnVO column = getGradebookService().getCourseColumnByColumnName(courseId, columnName);
            if (column != null) {
                logger.info("Column Id for Course id " + courseId + " is " + column.getId());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get members for user id  " + courseId + ": " + e.getMessage());
        }
    }

    /**
     * gets all scores by column for a course
     *
     * @param courseId
     * @param columnName
     */
    public void getCourseScoreByColumn(String courseId, String columnName) {
        try {
            GradebookWSStub.ScoreVO[] scores = getGradebookService().getCourseScoreByColumn(courseId, columnName);
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

}
