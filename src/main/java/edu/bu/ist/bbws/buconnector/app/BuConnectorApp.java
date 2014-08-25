package edu.bu.ist.bbws.buconnector.app;

import edu.bu.ist.bbws.buconnector.bean.Course;
import edu.bu.ist.bbws.buconnector.bean.User;
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

        String courseId = "00cwr_orc_labsafety_training";

//       buConnectorController.getBlackboardCourses();


        Course course = buConnectorController.getCourseById(courseId);
        logger.info("Course infor course id " + courseId);
        logger.info(course);



//        List<User> users = buConnectorController.getCourseUsersByCourseId(courseId);
//        logger.info("Users for course id " + courseId);
//        for (User user : users){
//            logger.info(user.toString());
//        }

//       buConnectorController.getUserByUsername("rjaeckel");
//        buConnectorController.getRole("On Campus Instructor");
//        buConnectorController.getBlackboardCoursesForUser("rjaeckel");
//        buConnectorController.getBlackboardCoursesForUserByRole("rjaeckel", "On Campus Instructor");
//        buConnectorController.getCourseMembership("rjaeckel", "00cwr_orc_labsafety_training"); //  _322_1 /00fallsfamu479_a1
//        buConnectorController.getCourseColumns("00cwr_orc_labsafety_training");
//        buConnectorController.getCourseColumnByColumnName("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz"); //
//        buConnectorController.getCourseTotalScore("00cwr_orc_labsafety_training"); //00cwr_orc_labsafety_training _322_1
//        buConnectorController.getCourseScoreByColumn("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz"); //00cwr_orc_labsafety_training _322_1





//        buConnectorController.getCourseScoreByColumnAfterSubmissionDate("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz", ""); //00cwr_orc_labsafety_training _322_1

    }

}
