package edu.bu.ist.bbws.app;

import edu.bu.ist.bbws.model.*;
import edu.bu.ist.bbws.controller.BuConnectorController;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by mkousheh on 8/20/14.
 */
class BuConnectorApp {
    private static final Logger logger = Logger.getLogger(BuConnectorApp.class.getName());

    private BuConnectorController buConnectorController;

    private static final String ROLE_ID = "S";
    private static final String ROLE_NAME = "StUdEnt";
    private static final String USERNAME = "cbaglio";  // rjaeckel kcabbott rafonso
    private static final String USER_BB_ID = "_78412_1";
    private static final String COURSE_ID = "14sum2sedme504sb1"; //00cwr_orc_labsafety_training
    private static final String COURSE_BB_ID = "_17929_1";
    private static final String COLUMN_BB_ID = "_276382_1";
    private static final String COLUMN_NAME = "Total";



    public BuConnectorApp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/resources/applicationContext.xml");
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



/*

        List<CourseBasic> allCourses = buConnectorController.getBlackboardCourses();
        logger.info("All BB courses: ");
        for (CourseBasic course : allCourses){
            logger.info(course.toString());
        }
*/



        CourseBasic courseById = buConnectorController.getCourseById(COURSE_ID);
        logger.info("getCourseById: Course info by course id: " + COURSE_ID);
        logger.info(courseById);

        CourseBasic courseByBbId = buConnectorController.getCourseByBbId(COURSE_BB_ID);
        logger.info("getCourseByBbId: Course info by course Internal BB id: " + COURSE_BB_ID);
        logger.info(courseByBbId);


        User userByUserBbId = buConnectorController.getUserByUserBbId(USER_BB_ID);
        logger.info("getUserByUserBbId: User information for user: " + USER_BB_ID);
        logger.info(userByUserBbId);



        List<User> courseUsersByCourseId = buConnectorController.getCourseUsersByCourseId(COURSE_ID);
        logger.info("getCourseUsersByCourseId: Users for course id: " + COURSE_ID);
        for (User user : courseUsersByCourseId){
            logger.info(user.toString());
        }

        List<User> enrolledStudents = buConnectorController.getCourseEnrolledStudents(COURSE_ID);
        logger.info("getCourseEnrolledStudents: Students for course id: " + COURSE_ID);
        for (User user : enrolledStudents){
            logger.info(user.toString());
        }

        List<User> intructors = buConnectorController.getCourseInstructors(COURSE_ID);
        logger.info("getCourseInstructors: Instructor(s) for course id: " + COURSE_ID);
        for (User user : intructors){
            logger.info(user.toString());
        }

        User userByUsername = buConnectorController.getUserByUsername(USERNAME);
        logger.info("getUserByUsername: User information for user: " + USERNAME);
        logger.info(userByUsername);



        CourseMembershipRole courseMembershipRoleByName= buConnectorController.getCourseMembershipRoleByName(ROLE_NAME);
        logger.info("getCourseMembershipRoleByName: CourseMembershipRole information for course membership role name: " + ROLE_NAME);
        logger.info(courseMembershipRoleByName);


        CourseMembershipRole courseMembershipRoleById= buConnectorController.getCourseMembershipRoleById(ROLE_ID);
        logger.info("getCourseMembershipRoleById: CourseMembershipRole information for course membership role name: " + ROLE_ID);
        logger.info(courseMembershipRoleById);



        List<CourseBasic> coursesForUser = buConnectorController.getCoursesForUser(USERNAME);
        logger.info("getCoursesForUser: courses for user (" + USERNAME +")");
        for (CourseBasic course : coursesForUser){
            logger.info(course.toString());
        }



        List<CourseBasic> coursesForUserInRole = buConnectorController.getCoursesForUserInRole(USERNAME, ROLE_NAME);
        logger.info("getCoursesForUserInRole: courses for user (" + USERNAME +") in role (" + ROLE_NAME +")");
        for (CourseBasic course : coursesForUserInRole){
            logger.info(course.toString());
        }


        List<CourseMembership> courseMemberships = buConnectorController.getCourseMembership(USERNAME, COURSE_ID);
        logger.info("getCourseMembership: Course membership for user (" + USERNAME +") and course id (" + COURSE_ID +")");
        for (CourseMembership courseMembership : courseMemberships){
            logger.info(courseMembership.toString());
        }

        List<Column> courseColumns = buConnectorController.getCourseColumns(COURSE_ID);
        logger.info("getCourseColumns: Course columns for course id (" + COURSE_ID +")");
        for (Column column : courseColumns){
            logger.info(column.toString());
        }

        List<Column> courseColumnsByColumnName = buConnectorController.getCourseColumnsByColumnName(COURSE_ID, COLUMN_NAME);
        logger.info("getCourseColumnsByColumnName: Column for course course id (" + COURSE_ID +") and column name (" + COLUMN_NAME + ")");
        for (Column columnName : courseColumnsByColumnName){
            logger.info(columnName.toString());
        }


        List<Score> courseTotalScores = buConnectorController.getCourseTotalScore(COURSE_ID);
        logger.info("getCourseTotalScore: Score for course id (" + COURSE_ID +")");
        for (Score score : courseTotalScores){
            logger.info(score.toString());
        }

        List<Score> courseScoresByColumn = buConnectorController.getCourseScoreByColumn(COURSE_ID, COLUMN_NAME);
        logger.info("getCourseScoreByColumn: getCourseScoreByColumn: Score for course id (" + COURSE_ID +") for column ("+ COLUMN_NAME +")");
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


        List<Score> courseScoresByColumnAfterSubmissionDate = buConnectorController.getCourseScoreByColumnAfterSubmissionDate(COURSE_ID, COLUMN_NAME, submissionDate);
        logger.info("getCourseScoreByColumnAfterSubmissionDate: Score for course id (" + COURSE_ID +") for column ("+ COLUMN_NAME +") submitted after (" +submissionDate+ ")");
        for (Score score : courseScoresByColumnAfterSubmissionDate){
            logger.info(score.toString());
        }
        if (courseScoresByColumnAfterSubmissionDate != null){
            logger.info("Total of records retrieved: "+courseScoresByColumnAfterSubmissionDate.size());

        }


        List<Score> courseScoreByUserAndColumnAfterDate = buConnectorController.getCourseScoreByUserAndColumnAfterDate(COURSE_ID, USERNAME, COLUMN_NAME, submissionDate);
        logger.info("getCourseScoreByUserAndColumnAfterDate: Score for user ("+USERNAME+") for course (" + COURSE_ID +") for column ("+ COLUMN_NAME +") submitted after (" +submissionDate+ ")");
        for (Score score : courseScoreByUserAndColumnAfterDate){
            logger.info(score.toString());
        }


        List<Score> courseScoreByUser = buConnectorController.getCourseScoreByUserAfterDate(COURSE_ID, USERNAME, submissionDate);
        logger.info("getCourseScoreByUserAfterDate: Score for user ("+USERNAME+") for course (" + COURSE_ID +") submitted after (" +submissionDate+ ")");
        for (Score score : courseScoreByUser){
            logger.info(score.toString());
        }

        buConnectorController.logout();

    }

}
