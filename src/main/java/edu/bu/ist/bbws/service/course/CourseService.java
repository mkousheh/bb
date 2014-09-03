package edu.bu.ist.bbws.service.course;

import edu.bu.ist.bbws._generated.course.CourseWSStub;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface CourseService {

    public CourseWSStub.CourseVO[] getBlackboardCourses() throws RemoteException;

    public CourseWSStub.CourseVO getCourseByBbId( String courseBbId) throws RemoteException;

    public CourseWSStub.CourseVO getCourseById(String courseId) throws RemoteException;

    public CourseWSStub.CourseVO[] getCoursesForUser(String username) throws RemoteException;

    public CourseWSStub.CourseVO[] getCoursesForUserInRole(String username, String role) throws RemoteException;


}
