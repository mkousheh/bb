package edu.bu.ist.bbws.service.user;

import edu.bu.ist.bbws.controller.BuConnectorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class UserServiceImplTest {

    private static final String ROLE_ID = "OnCampusInstructor";
    private static final String ROLE_NAME = "On Campus Instructor";
    private static final String USERNAME = "mkousheh";
    private static final String USER_BB_ID = "_82381_1";
    private static final String FAKEUSER = "fakeuser";
    private static final String COURSE_ID = "00cwr_orc_labsafety_training";
    private static final String COURSE_BB_ID = "_2696_1";
    private BuConnectorController buConnectorController;
    private UserServiceImpl userService;

    @BeforeMethod
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
        userService = (UserServiceImpl) buConnectorController.getUserService();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        buConnectorController.logout();
    }

    @Test
    public void testGetUserByUserBbId() throws Exception {
        assertEquals(userService.getUserByUserBbId(USER_BB_ID).getName(), USERNAME);
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        assertEquals(userService.getUserByUsername(USERNAME).getId(), USER_BB_ID);
    }

    @Test
    public void testGetCourseUsersByCourseId() throws Exception {
        assertNotNull(userService.getCourseUsersByCourseId(COURSE_ID));
    }
}