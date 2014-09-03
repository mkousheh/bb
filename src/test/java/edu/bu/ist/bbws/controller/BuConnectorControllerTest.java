package edu.bu.ist.bbws.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.*;

public class BuConnectorControllerTest {

    private BuConnectorController buConnectorController;

    private static final String ROLE_ID = "S";
    private static final String ROLE_NAME = "StUdEnt";
    private static final String USERNAME = "cbaglio";
    private static final String USER_BB_ID = "_78412_1";
    private static final String COURSE_ID = "14sum2sedme504sb1";
    private static final String COURSE_BB_ID = "_17929_1";
    private static final String COLUMN_BB_ID = "_276382_1";
    private static final String COLUMN_NAME = "Total";

    @BeforeMethod
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        buConnectorController.logout();
    }


    @Test
    public void testGetBlackboardCourses() throws Exception {
  //     assertNotNull(buConnectorController.getBlackboardCourses());
    }

    @Test
    public void testGetCourseByBbId() throws Exception {
        assertEquals(buConnectorController.getCourseByBbId(COURSE_BB_ID).getCourseId(), COURSE_ID);
    }

    @Test
    public void testGetCourseDetailsByBbId() throws Exception {
        assertNotNull(buConnectorController.getCourseDetailsByBbId(COURSE_BB_ID).getEnrolledStudents());
    }

    @Test
    public void testGetCourseById() throws Exception {
        assertEquals(buConnectorController.getCourseById(COURSE_ID).getBbId(), COURSE_BB_ID);
    }

    @Test
    public void testGetCourseDetailsById() throws Exception {
        assertNotNull(buConnectorController.getCourseDetailsById(COURSE_ID));
    }

    @Test
    public void testGetUserByUserBbId() throws Exception {
        assertNotNull(buConnectorController.getUserByUserBbId(USER_BB_ID));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
       assertNotNull(buConnectorController.getUserByUsername(USERNAME));
    }

    @Test
    public void testGetCourseUsersByCourseId() throws Exception {
        assertNotNull(buConnectorController.getCourseUsersByCourseId(COURSE_ID));
    }

    @Test
    public void testGetCourseEnrolledStudents() throws Exception {
       assertNotNull(buConnectorController.getCourseEnrolledStudents(COURSE_ID));
    }

    @Test
    public void testGetCourseInstructors() throws Exception {
        assertNotNull(buConnectorController.getCourseInstructors(COURSE_ID));
    }
    @Test
    public void testGetCourseMembershipRoleByName() throws Exception {
        assertNotNull(buConnectorController.getCourseMembershipRoleByName(ROLE_NAME));
    }

    @Test
    public void testGetCourseMembershipRoleById() throws Exception {
        assertNotNull(buConnectorController.getCourseMembershipRoleById(ROLE_ID));
    }

    @Test
    public void testGetCoursesForUser() throws Exception {
        assertNotNull(buConnectorController.getCoursesForUser(USERNAME));
    }

    @Test
    public void testGetCoursesForUserInRole() throws Exception {
        assertNotNull(buConnectorController.getCoursesForUserInRole(USERNAME, ROLE_NAME));
    }

    @Test
    public void testGetCourseMembership() throws Exception {
        assertNotNull(buConnectorController.getCourseMembership(USERNAME, COURSE_ID));
    }

    @Test
    public void testGetCourseColumns() throws Exception {
        assertNotNull(buConnectorController.getCourseColumns(COURSE_ID));
    }

    @Test
    public void testGetCourseColumnsByColumnName() throws Exception {
        assertNotNull(buConnectorController.getCourseColumnsByColumnName(COURSE_ID, COLUMN_NAME));
    }

    @Test
    public void testGetCourseTotalScore() throws Exception {
        assertNotNull(buConnectorController.getCourseTotalScore(COURSE_ID));
    }

    @Test
    public void testGetCourseScoreByColumn() throws Exception {
        assertNotNull(buConnectorController.getCourseScoreByColumn(COURSE_ID, COLUMN_NAME));
    }

    @Test
    public void testGetCourseScoreByUserAndColumn() throws Exception {
        assertNotNull(buConnectorController.getCourseScoreByUserAndColumn(COURSE_ID, USERNAME, COLUMN_NAME));
    }

    @Test
    public void testGetCourseScoreByUserAndColumnAfterDate() throws Exception {
        Date submissionDate = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2000);
        submissionDate = cal.getTime();

        assertNotNull(buConnectorController.getCourseScoreByUserAndColumnAfterDate(COURSE_ID, USERNAME, COLUMN_NAME,submissionDate ));
    }

    @Test
    public void testGetCourseScoreByUserAfterDate() throws Exception {
        Date submissionDate = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        submissionDate = cal.getTime();

        assertNotNull(buConnectorController.getCourseScoreByUserAfterDate(COURSE_ID, USERNAME, submissionDate));
    }

    @Test
    public void testGetCourseScoreByColumnAfterSubmissionDate() throws Exception {
        Date submissionDate = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        submissionDate = cal.getTime();

        assertNotNull(buConnectorController.getCourseScoreByColumnAfterSubmissionDate(COURSE_ID, COLUMN_NAME, submissionDate));
    }

    @Test
    public void testLogin() throws Exception {
       assertTrue(buConnectorController.login());
    }

    @Test
    public void testLogout() throws Exception {
        assertTrue(buConnectorController.logout());

    }
}