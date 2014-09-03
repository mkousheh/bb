package edu.bu.ist.bbws.service.gradebook;

import edu.bu.ist.bbws.controller.BuConnectorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.*;

public class GradebookServiceImplTest {

    private static final String ROLE_ID = "OnCampusInstructor";
    private static final String ROLE_NAME = "On Campus Instructor";
    private static final String USERNAME = "kcabbott";
    private static final String FAKEUSER = "fakeuser";
    private static final String COURSE_ID = "00cwr_orc_labsafety_training";
    private static final String COLUMN_BB_ID = "_27514_1";
    private static final String COLUMN_NAME = "Universal Laboratory Safety Training Quiz";
    private static final String COURSE_BB_ID = "_2696_1";
    private BuConnectorController buConnectorController;
    private GradebookService gradebookService;

    @BeforeMethod
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
        gradebookService = (GradebookService) buConnectorController.getGradebookService();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        buConnectorController.logout();
    }

    @Test
    public void testGetCourseColumns() throws Exception {
        assertNotNull(gradebookService.getCourseColumns(COURSE_ID));
    }

    @Test
    public void testGetCourseColumnsByColumnName() throws Exception {
        assertEquals(gradebookService.getCourseColumnsByColumnName(COURSE_ID, COLUMN_NAME)[0].getId(), COLUMN_BB_ID);
    }

    @Test
    public void testGetCourseTotalScore() throws Exception {
        assertNotNull(gradebookService.getCourseTotalScore(COURSE_ID));
    }

    @Test
    public void testGetCourseScoreByColumn() throws Exception {
        assertNotNull(gradebookService.getCourseScoreByColumn(COURSE_ID, COLUMN_NAME));
    }

    @Test
    public void testGetCourseScoreByUsernameAndColumn() throws Exception {
        assertEquals(gradebookService.getCourseScoreByUserAndColumn(COURSE_ID, USERNAME, COLUMN_NAME)[0].getGrade(), "80.0");
    }

    @Test
    public void testGetCourseScoreByUser() throws Exception {
        Date submissionDate = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -365);
        submissionDate = cal.getTime();

        assertNotNull(gradebookService.getCourseScoreByUserAfterDate(COURSE_ID, USERNAME, submissionDate).get(0).getGrade());
    }

    @Test
    public void testGetCourseScoreByColumnAfterSubmissionDate() throws Exception {
        Date submissionDate = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        submissionDate = cal.getTime();

        assertNotNull(gradebookService.getCourseScoreByColumnAfterSubmissionDate(COURSE_ID, COLUMN_NAME, submissionDate).get(0).getGrade());
    }

    @Test
    public void testGetCourseScoreByUserAndColumnAfterDate() throws Exception {
        Date submissionDate = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        submissionDate = cal.getTime();

        assertEquals(gradebookService.getCourseScoreByUserAndColumnAfterDate(COURSE_ID, USERNAME, COLUMN_NAME, submissionDate).get(0).getGrade(), "80.0");
    }

}