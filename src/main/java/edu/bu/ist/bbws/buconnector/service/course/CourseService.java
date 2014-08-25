package edu.bu.ist.bbws.buconnector.service.course;

import edu.bu.ist.bbws._generated.course.CourseWSStub;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface CourseService {

    public List<String> getBlackboardCourses() throws RemoteException;

    public CourseWSStub.CourseVO getCourseById(String courseId) throws RemoteException;

    public CourseWSStub.CourseVO[] getBlackboardCoursesForUser(String username) throws RemoteException;

    public CourseWSStub.CourseVO[] getBlackboardCoursesForUserByRole(String username, String role) throws RemoteException;





    public List<String> getCoursesForUserInRole(String modulePath,
                                                String blackboardServerURL, String sharedSecret, String vendorId,
                                                String clientProgramId, String userId, String role) throws RemoteException;

}
