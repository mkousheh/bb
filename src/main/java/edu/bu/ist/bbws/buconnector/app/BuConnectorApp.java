package edu.bu.ist.bbws.buconnector.app;

import edu.bu.ist.bbws.buconnector.controller.BuConnectorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mkousheh on 8/20/14.
 */
public class BuConnectorApp {
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

//       buConnectorController.getBlackboardCourses();
//        buConnectorController.getCourseById("00cwr_orc_labsafety_training");
//       buConnectorController.getCourseUsersByCourseId("00cwr_orc_labsafety_training");
//       buConnectorController.getUserByUsername("rjaeckel");
//        buConnectorController.getRole("On Campus Instructor");
//        buConnectorController.getBlackboardCoursesForUser("rjaeckel");
//        buConnectorController.getBlackboardCoursesForUserByRole("rjaeckel", "On Campus Instructor");
//        buConnectorController.getCourseMembership("rjaeckel", "00cwr_orc_labsafety_training"); //  _322_1 /00fallsfamu479_a1
//        buConnectorController.getCourseColumns("00cwr_orc_labsafety_training");
//        buConnectorController.getCourseColumnByColumnName("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz"); //
//        buConnectorController.getCourseTotalScore("00cwr_orc_labsafety_training"); //00cwr_orc_labsafety_training _322_1
        buConnectorController.getCourseScoreByColumn("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz"); //00cwr_orc_labsafety_training _322_1





//        buConnectorController.getCourseScoreByColumnAfterSubmissionDate("00cwr_orc_labsafety_training", "Universal Laboratory Safety Training Quiz", ""); //00cwr_orc_labsafety_training _322_1

    }

}
