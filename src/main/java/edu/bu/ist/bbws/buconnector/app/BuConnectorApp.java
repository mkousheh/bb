package edu.bu.ist.bbws.buconnector.app;

import edu.bu.ist.bbws.buconnector.bean.*;
import edu.bu.ist.bbws.buconnector.controller.BuConnectorController;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by mkousheh on 8/20/14.
 */
public class BuConnectorApp {
    private static final Logger logger = Logger.getLogger(BuConnectorApp.class.getName());

    ApplicationContext ctx = new ClassPathXmlApplicationContext( "applicationContext_BuConnector.xml");
    BuConnectorController buConnectorController;

    /**
     * @param args
     */
    public static void main(String[] args) {
        BuConnectorApp app = new BuConnectorApp();
        app.run();
    }

    private void run() {
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();

        String courseBbId = "_3400_1";
        String usernBbId = "_34048_1";
        String courseMembershipBbId = "_784026_1";

        String userId = "U84206596";

        String courseId = "00cwr_orc_labsafety_training";
        String username = "rjaeckel";

        String courseMembershipRoleName = "On Campus Instructor";
        String courseMembershipRoleId = "OnCampusInstructor";

        String columnName = "Universal Laboratory Safety Training Quiz";


//       buConnectorController.getBlackboardCourses();


/*
        Course course = buConnectorController.getCourseByBbId(courseBbId);
        logger.info("Course infor course Internal BB id: " + courseBbId);
        logger.info(course);
*/

/*
        User user = buConnectorController.getUserByUserBbId(usernBbId);
        logger.info("User information for user: " + usernBbId);
        logger.info(user);
*/

/*
        List<User> users = buConnectorController.getCourseUsersByCourseId(courseId);
        logger.info("Users for course id: " + courseId);
        for (User user : users){
            logger.info(user.toString());
        }
*/

/*
       User user = buConnectorController.getUserByUsername(username);
       logger.info("User information for user: " + username);
       logger.info(user);
*/

/*
        CourseMembershipRole courseMembershipRoleByName= buConnectorController.getCourseMembershipRoleByName(courseMembershipRoleName);
        logger.info("CourseMembershipRole information for course membership role name: " + courseMembershipRoleName);
        logger.info(courseMembershipRoleByName);
*/


/*
        CourseMembershipRole courseMembershipRoleById= buConnectorController.getCourseMembershipRoleById(courseMembershipRoleId);
        logger.info("CourseMembershipRole information for course membership role name: " + courseMembershipRoleId);
        logger.info(courseMembershipRoleById);
*/

/*
        List<Course> coursesForUser = buConnectorController.getCoursesForUser(username);
        logger.info("courses for user (" + username +")");
        for (Course course : coursesForUser){
            logger.info(course.toString());
        }
*/

/*
         List<Course> coursesForUserInRole = buConnectorController.getCoursesForUserInRole(username, courseMembershipRoleName);
         logger.info("courses for user (" + username +") in role (" + courseMembershipRoleName +")");
        for (Course course : coursesForUserInRole){
            logger.info(course.toString());
        }


        List<CourseMembership> courseMemberships = buConnectorController.getCourseMembership(username, courseId);
        logger.info("Course membership for user (" + username +") and course id (" + courseId +")");
        for (CourseMembership courseMembership : courseMemberships){
            logger.info(courseMembership.toString());
        }

        List<Column> columns = buConnectorController.getCourseColumns(courseId);
        logger.info("Course columns for course id (" + courseId +")");
        for (Column column : columns){
            logger.info(column.toString());
        }
*/

        Column column = buConnectorController.getCourseColumnByColumnName(courseId, columnName);
        logger.info("Column for course course id (" + courseId +") and column name (" + columnName + ")");
        logger.info(column.toString());      //TODO check for NPE


//        buConnectorController.getCourseTotalScore("00cwr_orc_labsafety_training"); //00cwr_orc_labsafety_training _322_1
//        buConnectorController.getCourseScoreByColumn("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz"); //00cwr_orc_labsafety_training _322_1





//        buConnectorController.getCourseScoreByColumnAfterSubmissionDate("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz", ""); //00cwr_orc_labsafety_training _322_1

    }

}
