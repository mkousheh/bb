package edu.bu.ist.bbws.buconnector.utils;

/**
 * Created by mkousheh on 8/24/14.
 */
public class ConnectorUtil {
    private String blackboardServerURL;
    private String clientVendorId;
    private String clientProgramId;
    private String modulePath;
    private String sharedSecret;
    private String username;

    public String getBlackboardServerURL() {
        return blackboardServerURL;
    }

    public void setBlackboardServerURL(String blackboardServerURL) {
        this.blackboardServerURL = blackboardServerURL;
    }

    public String getClientVendorId() {
        return clientVendorId;
    }

    public void setClientVendorId(String clientVendorId) {
        this.clientVendorId = clientVendorId;
    }

    public String getClientProgramId() {
        return clientProgramId;
    }

    public void setClientProgramId(String clientProgramId) {
        this.clientProgramId = clientProgramId;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
