package edu.bu.ist.bbws.buconnector.controller;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.buconnector.model.*;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.buconnector.service.gradebook.GradebookService;
import edu.bu.ist.bbws.buconnector.service.user.UserService;
import org.apache.log4j.Logger;

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

    private ContextService contextService;
    private CourseService courseService;
    private CoursemembershipService coursemembershipService;
    private GradebookService gradebookService;
    private UserService userService;


    private BuConnectorController() {
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
     * @return list of all BB Course objects
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
     * get all Blackboard courses
     * @return list of all BB Course objects
     */
    public List<Course> getAvailableCourses() {
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
     * @param courseBbId - bb local course identifier
     * @return course object
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
     * @param courseId - bb course identifier
     * @return course object
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
    public List<Course> getCoursesForUser(String username) {
        List<Course> courses = new ArrayList<Course>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getCoursesForUser(username.trim());
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new Course(courseVO));
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
    public List<Course> getCoursesForUserInRole(String username, String courseMembershipRoleName) {
        List<Course> courses = new ArrayList<Course>();
        try {
            CourseWSStub.CourseVO[] courseVOs = getCourseService().getCoursesForUserInRole(username.trim(), courseMembershipRoleName.trim());
            if (courseVOs != null){
                for (CourseWSStub.CourseVO courseVO : courseVOs) {
                    courses.add(new Course(courseVO));
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
                    columns.add(new Column(columnVO));
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
                    columns.add(new Column(columnVO));
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
     * @param submissionDate
     *
     */
    public List<Score>  getCourseScoreByUserAndColumn(String courseId, String username, String columnName, java.util.Date submissionDate) {
        List<Score> scores = new ArrayList<Score>();
        try {
            List<GradebookWSStub.ScoreVO> scoreVOs = getGradebookService().getCourseScoreByUserAndColumn(courseId.trim(), username.trim(), columnName.trim(), submissionDate);
            logger.info("Scores for Course id " + courseId + " for user " + username +" and column " + columnName + " are:");
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


    ContextService getContextService() {
        return contextService;
    }

    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }

    CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    CoursemembershipService getCoursemembershipService() {
        return coursemembershipService;
    }

    public void setCoursemembershipService(CoursemembershipService coursemembershipService) {
        this.coursemembershipService = coursemembershipService;
    }

    GradebookService getGradebookService() {
        return gradebookService;
    }

    public void setGradebookService(GradebookService gradebookService) {
        this.gradebookService = gradebookService;
    }

    UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }





    User loadUserByKey(String userBbId) {
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
                    Collections.addAll(instRoles, userVO.getInsRoles());
                    user.setInsRoles(instRoles);
                }
                if (userVO.getSystemRoles() != null) {
                    List<String> systemRoles = new ArrayList<String>();
                    Collections.addAll(systemRoles, userVO.getSystemRoles());
                    user.setSystemRoles(systemRoles);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    return user;
    }

Course loadCourseByKey(String courseBbId){
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



    CourseMembershipRole loadCourseMembershipRoleByKey(String courseMembershipRoleId){
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
