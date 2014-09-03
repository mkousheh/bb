package edu.bu.ist.bbws.buconnector.controller;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.buconnector.model.*;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.context.ContextServiceImpl;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.course.CourseServiceImpl;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipServiceImpl;
import edu.bu.ist.bbws.buconnector.service.gradebook.GradebookService;
import edu.bu.ist.bbws.buconnector.service.gradebook.GradebookServiceImpl;
import edu.bu.ist.bbws.buconnector.service.user.UserService;
import edu.bu.ist.bbws.buconnector.service.user.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mkousheh on 8/20/14.
 */
public class BuConnectorController {
    private static final Logger logger = Logger.getLogger(BuConnectorController.class.getName());

    @Autowired
    private ContextServiceImpl contextService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private CoursemembershipServiceImpl coursemembershipService;
    @Autowired
    private GradebookServiceImpl gradebookService;
    @Autowired
    private UserServiceImpl userService;


    private BuConnectorController() {
    }

    /**
     * get all Blackboard courses
     * @return list of all BB Course objects
     */
    public List<CourseBasic> getBlackboardCourses() {
        List<CourseBasic> courses = new ArrayList<CourseBasic>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getBlackboardCourses();
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new CourseBasic(courseVO, getCourseService(), getGradebookService()));
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
     * @param courseBbId - bb local course identifier
     * @return course object
     */
    public CourseDetail getCourseDetailsByBbId(String courseBbId) {
        CourseDetail course = null;
        try {
            CourseWSStub.CourseVO courseVO = getCourseService().getCourseByBbId(courseBbId);
            if (courseVO != null) {
                course = new CourseDetail(courseVO, getUserService(), getGradebookService(), getCoursemembershipService(), getCourseService());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard title for course (" + courseBbId + ") : " + e.getMessage());
        }
        return course;
    }
    /**
     * gets course object for a given BB Internal id ("_99999_9" format)
     *
     * @param courseBbId - bb local course identifier
     * @return course object
     */
    public CourseBasic getCourseByBbId(String courseBbId) {
        CourseBasic course = null;
        try {
            CourseWSStub.CourseVO courseVO = getCourseService().getCourseByBbId(courseBbId);
            if (courseVO != null) {
                course = new CourseBasic(courseVO, getCourseService(), getGradebookService());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard title for course (" + courseBbId + ") : " + e.getMessage());
        }
        return course;
    }
    /**
     * gets course object for a given course id
     *
     * @param courseId - bb course identifier
     * @return course object
     */
    public CourseBasic getCourseById(String courseId) {
        CourseBasic course = null;
        try {
            CourseWSStub.CourseVO courseVO = getCourseService().getCourseById(courseId);
            if (courseVO != null) {
                course = new CourseBasic(courseVO, getCourseService(), getGradebookService());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard title for course (" + courseId + ") : " + e.getMessage());
        }
        return course;
    }

    /**
     * gets course object for a given course id
     *
     * @param courseId - bb course identifier
     * @return course object
     */
    public CourseDetail getCourseDetailsById(String courseId) {
        CourseDetail course = null;
        try {
            CourseWSStub.CourseVO courseVO = getCourseService().getCourseById(courseId);
            if (courseVO != null) {
                course = new CourseDetail(courseVO, getUserService(), getGradebookService(), getCoursemembershipService(), getCourseService());
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get the Blackboard title for course (" + courseId + ") : " + e.getMessage());
        }
        return course;
    }
    /**
     * gets user object by given user bb internal id ("_99999_9" format)
     *
     * @param userBbId - bb internal identifier
     * @return user object
     */
    public User getUserByUserBbId(String userBbId) {
        User user = null;
        try {
            UserWSStub.UserVO userVO = getUserService().getUserByUserBbId(userBbId);
            if (userVO != null) {
                user = new User(userVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getUserByUserBbId for param (" + userBbId + ") : " + e.getMessage());
        }
        return user;
    }

    /**
     * gets user object by given username
     *
     * @param username - bb batchId (alias)
     * @return user object
     */
    public User getUserByUsername(String username) {
        User user = null;
        try {
            UserWSStub.UserVO userVO = getUserService().getUserByUsername(username.trim());
            if (userVO != null) {
                user = new User(userVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getUserByUsername for param (" + username + ") : " + e.getMessage());
        }
        return user;
    }

    /**
     * gets all users for a given course id
     *
     * @param courseId - bb course identifier
     * @return list of users for a course
     */
    public List<User> getCourseUsersByCourseId(String courseId) {
        List<User> users = new ArrayList<User>();
        try {
            UserWSStub.UserVO[] userVOs = getUserService().getCourseUsersByCourseId(courseId.trim());
            if (userVOs != null){
                for (UserWSStub.UserVO userVO : userVOs) {
                    users.add(new User(userVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseUsersByCourseId for param (" + courseId + ") : " + e.getMessage());
        }
        return users;
    }

    /**
     * gets all users for a given course id
     *
     * @param courseId - bb course identifier
     * @return list of users for a course
     */
    public List<User> getCourseEnrolledStudents(String courseId) {
        List<User> users = new ArrayList<User>();
        try {
            List<UserWSStub.UserVO> userVOs = getUserService().getCourseEnrolledStudents(courseId.trim());
            if (userVOs != null){
                for (UserWSStub.UserVO userVO : userVOs) {
                    users.add(new User(userVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseUsersByCourseId for param (" + courseId + ") : " + e.getMessage());
        }
        return users;
    }

    /**
     * gets all users for a given course id
     *
     * @param courseId - bb course identifier
     * @return list of users for a course
     */
    public List<User> getCourseInstructors(String courseId) {
        List<User> users = new ArrayList<User>();
        try {
            List<UserWSStub.UserVO> userVOs = getUserService().getCourseInstructors(courseId.trim());
            if (userVOs != null){
                for (UserWSStub.UserVO userVO : userVOs) {
                    users.add(new User(userVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseUsersByCourseId for param (" + courseId + ") : " + e.getMessage());
        }
        return users;
    }
    /**
     * gets a role object per given role name
     *
     * @param courseMembershipRoleName - bb course membership role display name
     * @return coursemembershiprole
     */
    public CourseMembershipRole getCourseMembershipRoleByName(String courseMembershipRoleName) {
        CourseMembershipRole courseMembershipRole = null;
        try {
            CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO = coursemembershipService.getCourseMembershipRoleByName(courseMembershipRoleName.trim());
            if (courseMembershipRoleVO != null){
                courseMembershipRole = new CourseMembershipRole(courseMembershipRoleVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseMembershipRoleByName for param (" + courseMembershipRoleName + ") : " + e.getMessage());
        }
        return courseMembershipRole;
    }

    /**
     * gets a role object per given role name
     *
     * @param courseMembershipRoleId - bb course membership role identifier
     * @return cousemembership object
     */
    public CourseMembershipRole getCourseMembershipRoleById(String courseMembershipRoleId) {
        CourseMembershipRole courseMembershipRole = null;
        try {
            CourseMembershipWSStub.CourseMembershipRoleVO courseMembershipRoleVO = coursemembershipService.getCourseMembershipRoleById(courseMembershipRoleId);
            if (courseMembershipRoleVO != null){
                courseMembershipRole = new CourseMembershipRole(courseMembershipRoleVO);
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseMembershipRoleById for param (" + courseMembershipRoleId + ") : " + e.getMessage());
        }
        return courseMembershipRole;
    }


    /**
     * get all bb courses for a given user
     *
     * @param username - bb batchId (alias)
     ** @return list of courses
     */
    public List<CourseBasic> getCoursesForUser(String username) {
        List<CourseBasic> courses = new ArrayList<CourseBasic>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getCoursesForUser(username.trim());
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new CourseBasic(courseVO, getCourseService(), getGradebookService()));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCoursesForUser for param (" + username + ") : " + e.getMessage());
        }
        return courses;
    }

    /**
     * gets courses for given username and a role id
     *
     * @param username - bb batchId (alias)
     * @param courseMembershipRoleName - bb course membership role display name
     * @return list of courses
     */
    public List<CourseBasic> getCoursesForUserInRole(String username, String courseMembershipRoleName) {
        List<CourseBasic> courses = new ArrayList<CourseBasic>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getCoursesForUserInRole(username.trim(), courseMembershipRoleName.trim());
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new CourseBasic(courseVO, getCourseService(), getGradebookService()));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCoursesForUserInRole for params (" + username + ", "  + courseMembershipRoleName +") : " + e.getMessage());
        }
        return courses;
    }

    /**
     * get course membership for a given username and a course id
     *
     * @param username - bb batchId (alias)
     * @param courseId - bb course identifier
     * @return a list of CourseMembership for a given username and a course id
     */
    public List<CourseMembership> getCourseMembership(String username, String courseId) {
        List<CourseMembership> courseMemberships = new ArrayList<CourseMembership>();
        try {
            CourseMembershipWSStub.CourseMembershipVO[] courseMembershipVOs = getCoursemembershipService().getCourseMembership(username.trim(), courseId.trim());
            if (courseMembershipVOs != null){
                for (CourseMembershipWSStub.CourseMembershipVO courseMembershipVO : courseMembershipVOs){
                   courseMemberships.add(new CourseMembership(courseMembershipVO, getCourseService(),  getUserService(),  getCoursemembershipService()));

                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseMembership for param (" + courseId + ") : " + e.getMessage());
        }
        return courseMemberships;
    }


    /**
     * gets course columns for a given course Id
     *
     * @param courseId - bb course identifier
     * @return a list of Column for a givenv course id
     */
    public List<Column> getCourseColumns(String courseId) {
        List<Column> columns = new ArrayList<Column>();

        try {
            GradebookWSStub.ColumnVO[] columnVOs = getGradebookService().getCourseColumns(courseId.trim());
            if (columnVOs != null) {
                for (GradebookWSStub.ColumnVO columnVO : columnVOs) {
                    columns.add(new Column(columnVO, getCourseService()));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseColumns for param (" + courseId + ") : " + e.getMessage());
        }
    return columns;
    }

    /**
     * gets course column by column name for a course
     *
     * @param courseId - bb course identifier
     * @param columnName - bb gradebook column display name
     * @return Column object for given courseId and columnName
     */
 //   public Column getCourseColumnByColumnName(String courseId, String columnName) {
 //       Column column = null;
 //       try {
 //  /         GradebookWSStub.ColumnVO columnVO = getGradebookService().getCourseColumnByColumnName(courseId.trim(), columnName.trim());
 //           if (columnVO != null) {
 //               column = new Column(columnVO);
 //           }
 //       } catch (RemoteException e) {
 //           logger.error("There was an error when trying to execute method getCourseColumnByColumnName for params (" + courseId + ", "  + columnName +") : " + e.getMessage());
//        }
//    return column;
//    }

    /**
     * gets course column by column name for a course
     *
     * @param courseId - bb course identifier
     * @param columnName - bb gradebook column display name
     * @return Column object for given courseId and columnName
     */
    public List<Column> getCourseColumnsByColumnName(String courseId, String columnName) {
        List<Column> columns = new ArrayList<Column>();
        try {
            GradebookWSStub.ColumnVO[] columnVOs = getGradebookService().getCourseColumnsByColumnName(courseId.trim(), columnName.trim());
            if (columnVOs != null) {
                for (GradebookWSStub.ColumnVO columnVO:columnVOs) {
                    columns.add(new Column(columnVO, getCourseService()));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseColumnByColumnName for params (" + courseId + ", "  + columnName +") : " + e.getMessage());
        }
        return columns;
    }


    /**
     * gets all course scores
     *
     * @param courseId - bb course identifier
     * @return list of Score object for given courseId
     */
    public List<Score> getCourseTotalScore(String courseId) {
        List<Score> scores = new ArrayList<Score>();
        try {
            GradebookWSStub.ScoreVO[] scoreVOs = getGradebookService().getCourseTotalScore(courseId.trim());
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseTotalScore for param (" + courseId + ") : " + e.getMessage());
        }
    return scores;
    }

    /**
     * gets all scores by column for a course
     *
     * @param courseId - bb course identifier
     * @param columnName - bb gradebook column display name
     */
    public List<Score> getCourseScoreByColumn(String courseId, String columnName) {
        List<Score> scores = new ArrayList<Score>();
        try {
            GradebookWSStub.ScoreVO[] scoreVOs = getGradebookService().getCourseScoreByColumn(courseId.trim(), columnName.trim());
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseScoreByColumn for params (" + courseId + ", " + columnName + "): " + e.getMessage());
        }
    return scores;
    }

    /**
     * @param courseId - bb course identifier
     * @param columnName - bb gradebook column display name
     *
     */
    public List<Score>  getCourseScoreByUserAndColumn(String courseId, String username, String columnName) {
        List<Score> scores = new ArrayList<Score>();
        try {
           GradebookWSStub.ScoreVO[] scoreVOs = getGradebookService().getCourseScoreByUserAndColumn(courseId.trim(), username.trim(), columnName.trim());
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseScoreByColumnAfterSubmissionDate for params (" + courseId +", "+ columnName + ") : " + e.getMessage());
        }
        return scores;
    }

    /**
     * @param courseId - bb course identifier
     * @param columnName - bb gradebook column display name
     * @param submissionDate
     *
     */
    public List<Score>  getCourseScoreByUserAndColumnAfterDate(String courseId, String username, String columnName, java.util.Date submissionDate) {
        List<Score> scores = new ArrayList<Score>();
        try {
            List<GradebookWSStub.ScoreVO> scoreVOs = getGradebookService().getCourseScoreByUserAndColumnAfterDate(courseId.trim(), username.trim(), columnName.trim(), submissionDate);
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseScoreByColumnAfterSubmissionDate for params (" + courseId +", "+ columnName +", "+ submissionDate + ") : " + e.getMessage());
        }
        return scores;
    }

    /**
     * @param courseId - bb course identifier
     * @param username - bb gradebook column display name
     * @param submissionDate
     *
     */
    public List<Score>  getCourseScoreByUserAfterDate(String courseId, String username, java.util.Date submissionDate) {
        List<Score> scores = new ArrayList<Score>();
        try {
            List<GradebookWSStub.ScoreVO> scoreVOs = getGradebookService().getCourseScoreByUserAfterDate(courseId.trim(), username.trim(), submissionDate);
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseScoreByColumnAfterSubmissionDate for params (" + courseId +", "+ username +", "+ submissionDate + ") : " + e.getMessage());
        }
        return scores;
    }

    /**
     * @param courseId - bb course identifier
     * @param columnName - bb gradebook column display name
     * @param submissionDate
     *
     */
    public List<Score>  getCourseScoreByColumnAfterSubmissionDate(String courseId, String columnName, java.util.Date submissionDate) {
        List<Score> scores = new ArrayList<Score>();
        try {
            List<GradebookWSStub.ScoreVO> scoreVOs = getGradebookService().getCourseScoreByColumnAfterSubmissionDate(courseId.trim(), columnName.trim(), submissionDate);
            logger.info("Scores for Course id " + courseId + " and column " + columnName + " are:");
            if (scoreVOs != null) {
                for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
                    scores.add(new Score(scoreVO));
                }
            }
        } catch (RemoteException e) {
            logger.error("There was an error when trying to execute method getCourseScoreByColumnAfterSubmissionDate for params (" + courseId +", "+ columnName +", "+ submissionDate + ") : " + e.getMessage());
        }
    return scores;
    }


    public boolean login() {
        boolean loginResult = false;
        try {
            loginResult = getContextService().login();
            logger.info("login:" + loginResult);
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get login to the Blackboard web service" + e.getMessage());
        }
        return loginResult;
    }
    public boolean logout() {
        boolean logoutResult = false;

        try {
            logoutResult = getContextService().logout();
            logger.info("logout: " + logoutResult);
        } catch (RemoteException e) {
            logger.error("There was an error when trying to get login to the Blackboard web service" + e.getMessage());
        }
        return logoutResult;
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

    public CoursemembershipServiceImpl getCoursemembershipService() {
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

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

}
