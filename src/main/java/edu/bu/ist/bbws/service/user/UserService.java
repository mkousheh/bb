package edu.bu.ist.bbws.service.user;

import edu.bu.ist.bbws._generated.user.UserWSStub;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface UserService {

    public UserWSStub.UserVO[] getCourseUsersByCourseId(String courseId) throws RemoteException;

    public List<UserWSStub.UserVO> getCourseEnrolledStudents(String courseId) throws RemoteException;

    public List<UserWSStub.UserVO> getCourseInstructors(String courseId) throws RemoteException;

    public UserWSStub.UserVO getUserByUserBbId(String userBbId) throws RemoteException;

    public UserWSStub.UserVO getUserByUsername(String username) throws RemoteException;

}
