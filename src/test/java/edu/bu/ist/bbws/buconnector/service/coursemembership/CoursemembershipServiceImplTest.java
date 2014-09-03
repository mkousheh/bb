package edu.bu.ist.bbws.buconnector.service.coursemembership;

import edu.bu.ist.bbws.buconnector.controller.BuConnectorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CoursemembershipServiceImplTest {

    private static final String ROLE_ID = "OnCampusInstructor";
    private static final String ROLE_NAME = "On Campus Instructor";
    private static final String USERNAME = "mkousheh";
    private static final String FAKEUSER = "fakeuser";
    private static final String COURSE_ID = "00cwr_orc_labsafety_training";
    private static final String COURSE_BB_ID = "_2696_1";
    CoursemembershipServiceImpl coursemembershipService;
    private BuConnectorController buConnectorController;

    @BeforeMethod
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_BuConnector.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
        coursemembershipService = (CoursemembershipServiceImpl) buConnectorController.getCoursemembershipService();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        buConnectorController.logout();
    }

    @Test
    public void testGetCourseMembershipRoleById() throws Exception {
        assertEquals(coursemembershipService.getCourseMembershipRoleById(ROLE_ID).getOrgRoleDescription(), ":"+ROLE_NAME);
    }

    @Test
    public void testGetCourseMembershipRoleByName() throws Exception {
        assertEquals(coursemembershipService.getCourseMembershipRoleByName(ROLE_NAME).getRoleIdentifier(), ROLE_ID);
    }

    @Test
    public void testGetCourseMembership() throws Exception {
        assertNotNull(coursemembershipService.getCourseMembership(USERNAME, COURSE_ID));
    }

}