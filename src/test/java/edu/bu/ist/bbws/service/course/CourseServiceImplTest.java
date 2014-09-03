package edu.bu.ist.bbws.service.course;

import edu.bu.ist.bbws.controller.BuConnectorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CourseServiceImplTest {

    private static final String ROLE_ID = "OnCampusInstructor";
    private static final String ROLE_NAME = "On Campus Instructor";
    private static final String USERNAME = "mkousheh";
    private static final String FAKEUSER = "fakeuser";
    private static final String COURSE_ID = "00cwr_orc_labsafety_training";
    private static final String COURSE_BB_ID = "_2696_1";
    CourseServiceImpl courseService;
    private BuConnectorController buConnectorController;

    @BeforeMethod
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
        courseService = (CourseServiceImpl) buConnectorController.getCourseService();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        buConnectorController.logout();
    }

    @Test
    public void testGetBlackboardCourses() throws Exception {

    }

    @Test
    public void testGetCourseByBbId() throws Exception {
        assertEquals(courseService.getCourseByBbId(COURSE_BB_ID).getCourseId(), COURSE_ID);
    }

    @Test
    public void testGetCourseById() throws Exception {
        assertEquals(courseService.getCourseById(COURSE_ID).getId(), COURSE_BB_ID);
    }

    @Test
    public void testGetCoursesForUser() throws Exception {
        assertNotNull(courseService.getCoursesForUser(USERNAME));
        assertNull(courseService.getCoursesForUser(FAKEUSER));
    }

    @Test
    public void testGetCoursesForUserInRole() throws Exception {
        assertNotNull(courseService.getCoursesForUserInRole(FAKEUSER, ROLE_ID));
    }
}