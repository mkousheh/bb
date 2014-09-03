package edu.bu.ist.bbws;

import edu.bu.ist.bbws.buconnector.controller.BuConnectorController;
import edu.bu.ist.bbws.buconnector.model.CourseBasic;
import edu.bu.ist.bbws.buconnector.model.CourseDetail;
import edu.bu.ist.bbws.buconnector.model.User;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by mkousheh on 8/29/14.
 */
public class BbWsConnector {

    private static final Logger logger = Logger.getLogger(BbWsConnector.class.getName() );
    private BuConnectorController buConnectorController;

    public BbWsConnector() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_BuConnector.xml");
        buConnectorController = (BuConnectorController) ctx.getBean("buConnectorController");
        buConnectorController.login();
    }

    public List<String> getSystemRolesByUserName(String username){
        List<String> userSysRoles = new ArrayList<String>();
        User user = buConnectorController.getUserByUsername(username.trim());
        for (String sysRole: user.getSystemRoles()){
            userSysRoles.add(sysRole);
        }
        return userSysRoles;
    }

    public List<String> getInsRolesByUserName(String username){
        List<String> userInsRoles = new ArrayList<String>();
        User user = buConnectorController.getUserByUsername(username.trim());
        for (String sysRole: user.getInsRoles()){
            userInsRoles.add(sysRole);
        }
        return userInsRoles;
    }

    public boolean doesCourseExist(String courseId){
        CourseBasic course = buConnectorController.getCourseById(courseId.trim());
        if (course != null){
            return true;
        }
        return false;
    }

    public String getCourseInstitutionName(String courseId){
        CourseBasic course = buConnectorController.getCourseById(courseId.trim());
        if (course != null){
            return course.getInstitutionName();
        }
        return null;
    }

    public List<String[]> getCourseByUserId(String username, String roleName){
        String crsInfo[][];
        List<CourseBasic> coursesForUserInRole = buConnectorController.getCoursesForUserInRole(username.trim(), roleName.trim());
        crsInfo = new String[coursesForUserInRole.size()][3];
        int i=0;
        for (CourseBasic course :coursesForUserInRole) {
            crsInfo[i][0] = course.getBbId();
            crsInfo[i][1] = course.getCourseId();
            crsInfo[i][2] = course.getName();
            i++;
        }
        return Arrays.asList(crsInfo);
    }

    public HashSet<String> getRolesByUserId(String userId){
        HashSet<String> roleSet = null;
//        roleSet = buConnectorController.getCourseMembershipRoleById();
        return null;
    }

    public void getBlackboardScore(){

    }

}
