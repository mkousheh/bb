package edu.bu.ist.bbws.buconnector.service.context;

import edu.bu.ist.bbws._generated.context.ContextWSStub;
import org.apache.axis2.client.ServiceClient;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface ContextService{

    public boolean login() throws RemoteException;

    public boolean logout() throws RemoteException;

    public void client_engage(ServiceClient client) throws RemoteException;

    public ContextWSStub.CourseIdVO[] getMembershipFromContext (String username)
            throws RemoteException;
    }
