package edu.bu.ist.bbws;

import org.apache.log4j.Logger;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/12/14.
 */
public class MkClass {
    private static final Logger logger = Logger.getLogger(MkClass.class.getName() );
    public static void main(String[] args) throws RemoteException{
        BbWsConnector bbWsConnector = new BbWsConnector();


        logger.debug("Course Exist: "+bbWsConnector.doesCourseExist("ORC Laboratory Safety Training"));
        logger.info(bbWsConnector.getCourseByUserId("rjaeckel", "On Campus Instructor"));
       //bbWsConnector.getCourseMembership();
        //bbWsConnector.getBlackboardScore();
        logger.info(bbWsConnector.getCourseInstitutionName("ORC Laboratory Safety Training"));

        logger.info(bbWsConnector.getSystemRolesByUserName("rjaeckel"));
        logger.info(bbWsConnector.getInsRolesByUserName("rjaeckel"));

    }
}