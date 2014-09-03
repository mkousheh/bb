package edu.bu.ist.bbws.buconnector.app;

import edu.bu.ist.bbws.buconnector.model.*;
import edu.bu.ist.bbws.buconnector.controller.BuConnectorController;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by mkousheh on 8/20/14.
 */
class BuConnectorApp {
    private static final Logger logger = Logger.getLogger(BuConnectorApp.class.getName());

    private BuConnectorController buConnectorController;

    public BuConnectorApp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_BuConnector.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        BuConnectorApp app = new BuConnectorApp();
        app.run();
    }

    private void run() {

        String courseBbId = "_17929_1"; // _3400_1
        String usernBbId = "_64517_1"; //  _34048_1
        String courseMembershipBbId = "_784026_1";

        String userId = "U84206596";

        String courseId = "00cwr_orc_labsafety_training"; //00cwr_orc_labsafety_training   14sum2sedme504sb1 _17929_1
        String username = "mkousheh"; // rafonso rjaeckel kcabbott

        String courseMembershipRoleName = "On Campus Instructor"; // STUDENT  On Campus Instructor
        String courseMembershipRoleId = "OnCampusInstructor";

        String columnName = "Universal Laboratory Safety Training Quiz"; // Homework #5 Universal Laboratory Safety Training Quiz

/*
        List<CourseBasic> allCourses = buConnectorController.getBlackboardCourses();
        logger.info("All BB courses: ");
        for (CourseBasic course : allCourses){
            logger.info(course.toString());
        }

*/

/*
        CourseBasic courseById = buConnectorController.getCourseById(courseId);
        logger.info("Course info by course id: " + courseId);
        logger.info(courseById);

        CourseBasic courseByBbId = buConnectorController.getCourseByBbId(courseBbId);
        logger.info("Course info by course Internal BB id: " + courseBbId);
        logger.info(courseByBbId);


        User userByUserBbId = buConnectorController.getUserByUserBbId(usernBbId);
        logger.info("User information for user: " + usernBbId);
        logger.info(userByUserBbId);



        List<User> courseUsersByCourseId = buConnectorController.getCourseUsersByCourseId(courseId);
        logger.info("Users for course id: " + courseId);
        for (User user : courseUsersByCourseId){
            logger.info(user.toString());
        }
*/
       List<User> enrolledStudents = buConnectorController.getCourseEnrolledStudents(courseId);
        logger.info("Users for course id: " + courseId);
        for (User user : enrolledStudents){
            logger.info(user.toString());
        }

        List<User> intructors = buConnectorController.getCourseInstructors(courseId);
        logger.info("Users for course id: " + courseId);
        for (User user : enrolledStudents){
            logger.info(user.toString());
        }
/*
       User userByUsername = buConnectorController.getUserByUsername(username);
       logger.info("User information for user: " + username);
       logger.info(userByUsername);

/*

        CourseMembershipRole courseMembershipRoleByName= buConnectorController.getCourseMembershipRoleByName(courseMembershipRoleName);
        logger.info("CourseMembershipRole information for course membership role name: " + courseMembershipRoleName);
        logger.info(courseMembershipRoleByName);


        CourseMembershipRole courseMembershipRoleById= buConnectorController.getCourseMembershipRoleById(courseMembershipRoleId);
        logger.info("CourseMembershipRole information for course membership role name: " + courseMembershipRoleId);
        logger.info(courseMembershipRoleById);



        List<CourseBasic> coursesForUser = buConnectorController.getCoursesForUser(username);
        logger.info("courses for user (" + username +")");
        for (Course course : coursesForUser){
            logger.info(course.toString());
        }



         List<CourseBasic> coursesForUserInRole = buConnectorController.getCoursesForUserInRole(username, courseMembershipRoleName);
         logger.info("courses for user (" + username +") in role (" + courseMembershipRoleName +")");
        for (Course course : coursesForUserInRole){
            logger.info(course.toString());
        }
*/

        List<CourseMembership> courseMemberships = buConnectorController.getCourseMembership(username, courseId);
        logger.info("Course membership for user (" + username +") and course id (" + courseId +")");
        for (CourseMembership courseMembership : courseMemberships){
            logger.info(courseMembership.toString());
        }
/*
        List<Column> columns = buConnectorController.getCourseColumns(courseId);
        logger.info("Course columns for course id (" + courseId +")");
        for (Column column : columns){
            logger.info(column.toString());
        }


//        Column column = buConnectorController.getCourseColumnByColumnName(courseId, columnName);
//        logger.info("Column for course course id (" + courseId +") and column name (" + columnName + ")");
//        logger.info(column);


*/
        List<Column> columns = buConnectorController.getCourseColumnsByColumnName(courseId, columnName);
        logger.info("Column for course course id (" + courseId +") and column name (" + columnName + ")");
        for (Column column : columns){
            logger.info(column.toString());
        }

/*
        List<Score> courseTotalScores = buConnectorController.getCourseTotalScore(courseId);
        logger.info("Score for course id (" + courseId +")");
        for (Score score : courseTotalScores){
            logger.info(score.toString());
        }



        List<Score> courseScoresByColumn = buConnectorController.getCourseScoreByColumn(courseId, columnName);
        logger.info("Score for course id (" + courseId +") for column ("+ columnName +")");
        for (Score score : courseScoresByColumn){
            logger.info(score.toString());
        }


        Date submissionDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -365);
        submissionDate = cal.getTime();


        String string = "August 09, 2014 22:19:43 ";
        try {
            submissionDate = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss", Locale.ENGLISH).parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<Score> courseScoresByColumnAfterSubmissionDate = buConnectorController.getCourseScoreByColumnAfterSubmissionDate(courseId, columnName, submissionDate);
        logger.info("Score for course id (" + courseId +") for column ("+ columnName +")");
        for (Score score : courseScoresByColumnAfterSubmissionDate){
            logger.info(score.toString());
        }
        if (courseScoresByColumnAfterSubmissionDate != null){
            logger.info("Total of records retrieved: "+courseScoresByColumnAfterSubmissionDate.size());

        }
/*

        List<Score> courseScoreByUserAndColumnAfterDate = buConnectorController.getCourseScoreByUserAndColumnAfterDate(courseId, username, columnName, submissionDate);
        logger.info("Score for user ("+username+") for course (" + courseId +") for column ("+ columnName +")");
        for (Score score : courseScoreByUserAndColumnAfterDate){
            logger.info(score.toString());
        }


        List<Score> courseScoreByUser = buConnectorController.getCourseScoreByUser(courseId, username, submissionDate);
        logger.info("Score for user ("+username+") for course (" + courseId +")");
        for (Score score : courseScoreByUser){
            logger.info(score.toString());
        }
*/

  //      buConnectorController.getAttempts(courseId);

//        buConnectorController.getCourseScoreByColumnAfterSubmissionDate("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz", ""); //00cwr_orc_labsafety_training _322_1

    }

}
