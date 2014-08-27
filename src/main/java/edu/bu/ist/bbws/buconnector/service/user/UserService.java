package edu.bu.ist.bbws.buconnector.service.user;

import edu.bu.ist.bbws._generated.user.UserWSStub;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface UserService {

    public UserWSStub.UserVO[] getCourseUsersByCourseId(String courseId) throws RemoteException;

    public UserWSStub.UserVO getUserByUserBbId(String userBbId) throws RemoteException;

    public UserWSStub.UserVO getUserByUsername(String username) throws RemoteException;

}
