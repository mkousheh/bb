package edu.bu.ist.bbws.buconnector.service.context;

import edu.bu.ist.bbws.buconnector.controller.BuConnectorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class ContextServiceImplTest {
    private BuConnectorController buConnectorController;
    private ContextServiceImpl contextService;

    private static final String USERNAME = "mkousheh";

    @BeforeMethod
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_BuConnector.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
        contextService = (ContextServiceImpl) buConnectorController.getContextService();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        buConnectorController.logout();
    }

    @Test
    public void testLogin() throws Exception {
        assertTrue(contextService.login());
    }

    @Test
    public void testLogout() throws Exception {
        assertTrue(contextService.logout());
    }

    @Test
    public void testGetMembershipFromContext() throws Exception {
        assertNotNull(contextService.getMembershipFromContext(USERNAME));

    }
}