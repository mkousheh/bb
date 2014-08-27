package edu.bu.ist.bbws.buconnector.service.context;

import edu.bu.ist.bbws._generated.context.ContextWSStub;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.log4j.Logger;
import org.apache.rampart.handler.WSSHandlerConstants;
import org.apache.rampart.handler.config.OutflowConfiguration;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public class ContextServiceImpl implements ContextService {
    private static final Logger logger = Logger.getLogger(ContextServiceImpl.class.getName());

    private ContextWSStub contextWSStub;
    private PasswordCallbackClass pwcb;
    private HttpClient httpClient;
    private ConfigurationContext ctx = null;
    private ConnectorUtil connectorUtil;

    /**
     * login to bb webser
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean login()
            throws RemoteException {

        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        boolean loginResult;
        try {

            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            httpClient = new HttpClient(conmgr);

            contextWSStub = new ContextWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Context.WS");
            pwcb = new PasswordCallbackClass();
            client_engage(contextWSStub._getServiceClient());

            String sessionValue = contextWSStub.initialize().get_return();
            pwcb.setSessionId(sessionValue);
            ContextWSStub.LoginTool loginArgs = new ContextWSStub.LoginTool();
            loginArgs.setPassword(getConnectorUtil().getSharedSecret());
            loginArgs.setClientVendorId(getConnectorUtil().getClientVendorId());
            loginArgs.setClientProgramId(getConnectorUtil().getClientProgramId());
            loginArgs.setLoginExtraInfo("");
            loginArgs.setExpectedLifeSeconds(6000 * 6000);
            loginResult = contextWSStub.loginTool(loginArgs).get_return();
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }

        return loginResult;
    }

    /**
     * gets list cof courses for a user
      * @param username
     * @return
     * @throws RemoteException
     */
    public ContextWSStub.CourseIdVO[] getMembershipFromContext (String username)
            throws RemoteException {
        ContextWSStub.GetMemberships memberships = new ContextWSStub.GetMemberships();
        memberships.setUserid(username);
        ContextWSStub.GetMembershipsResponse getMembershipsResponse = contextWSStub.getMemberships(memberships);

        return getMembershipsResponse.get_return();
    }

    /**
     *
     * @param client
     * @throws RemoteException
     */
    public void client_engage(ServiceClient client) throws RemoteException {
        Options options = client.getOptions();
        options.setProperty(HTTPConstants.HTTP_PROTOCOL_VERSION, HTTPConstants.HEADER_PROTOCOL_10);
        options.setProperty(WSHandlerConstants.PW_CALLBACK_REF, pwcb);
        OutflowConfiguration ofc = new OutflowConfiguration();
        ofc.setActionItems("UsernameToken Timestamp");
        ofc.setUser("session");
        ofc.setPasswordType("PasswordText");
        options.setProperty(WSSHandlerConstants.OUTFLOW_SECURITY, ofc.getProperty());

        // this will retain cookies within a single wsclient, but reusing the httpclient does the same thing across wsstubs:
        // op.setManageSession( true );
        options.setTimeOutInMilliSeconds(120 * 1000);
        options.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, httpClient);
        options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, "true");

        client.engageModule("rampart");  // could throw exception type AxisFault
    }

    public ContextWSStub getContextWSStub() {
        return contextWSStub;
    }

    public void setContextWSStub(ContextWSStub contextWSStub) {
        this.contextWSStub = contextWSStub;
    }

    public PasswordCallbackClass getPwcb() {
        return pwcb;
    }

    public void setPwcb(PasswordCallbackClass pwcb) {
        this.pwcb = pwcb;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ConfigurationContext getCtx() {
        return ctx;
    }

    public void setCtx(ConfigurationContext ctx) {
        this.ctx = ctx;
    }

    ConnectorUtil getConnectorUtil() {
        return connectorUtil;
    }

    public void setConnectorUtil(ConnectorUtil connectorUtil) {
        this.connectorUtil = connectorUtil;
    }

    /**
     * Store the session id value associated with the logged
     * in Blackboard web service.
     */
    private static class PasswordCallbackClass implements CallbackHandler {

        String sessionId = null;


        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }


        public void handle(Callback[] callbacks) throws IOException,
                UnsupportedCallbackException {
            for (int i = 0; i < callbacks.length; i++) {
                WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
                String pw = "nosession";

                if (sessionId != null) {
                    pw = sessionId;
                }
                pwcb.setPassword(pw);
            }
        }
    }
}
