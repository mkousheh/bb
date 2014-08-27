package edu.bu.ist.bbws.buconnector.model;

import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.buconnector.service.user.UserServiceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mkousheh on 8/25/14.
 */
public class User {

    private String userName;
    private String studentId;
    private String bbLocalId;
    private String emailAddress;
    private String familyName;
    private String givenName;
    private String genderType;
    private String educationLevel;
    private String title;
    private boolean available;
    private List<String> insRoles;
    private List<String> systemRoles;


    public User() {
    }

    public User(String userBbId) {
        UserWSStub.UserVO userVO;
        try {
            userVO = new UserServiceImpl().getUserByUserBbId(userBbId);
            if (userVO != null){
                this.available = userVO.getIsAvailable();
                this.bbLocalId = userVO.getId();
                this.educationLevel = userVO.getEducationLevel();
                this.emailAddress = userVO.getExtendedInfo().getEmailAddress();
                this.familyName = userVO.getExtendedInfo().getFamilyName();
                this.givenName = userVO.getExtendedInfo().getGivenName();
                this.genderType = userVO.getGenderType();
                this.studentId = userVO.getStudentId();
                this.title = userVO.getTitle();
                this.userName = userVO.getName();
                if (userVO.getInsRoles() != null) {
                    List<String> instRoles = new ArrayList<String>();
                    Collections.addAll(instRoles, userVO.getInsRoles());
                    this.insRoles = instRoles;
                }
                if (userVO.getSystemRoles() != null) {
                    List<String> systemRoles = new ArrayList<String>();
                    Collections.addAll(systemRoles, userVO.getSystemRoles());
                    this.systemRoles = systemRoles;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public User(UserWSStub.UserVO userVO) {
        if (userVO != null) {
            this.available = userVO.getIsAvailable();
            this.bbLocalId = userVO.getId();
            this.educationLevel = userVO.getEducationLevel();
            this.emailAddress = userVO.getExtendedInfo().getEmailAddress();
            this.familyName = userVO.getExtendedInfo().getFamilyName();
            this.givenName = userVO.getExtendedInfo().getGivenName();
            this.genderType = userVO.getGenderType();
            this.studentId = userVO.getStudentId();
            this.title = userVO.getTitle();
            this.userName = userVO.getName();

            if (userVO.getInsRoles() != null) {
                List<String> instRoles = new ArrayList<String>();
                Collections.addAll(instRoles, userVO.getInsRoles());
                this.insRoles = instRoles;
            }

            if (userVO.getSystemRoles() != null) {
                List<String> systemRoles = new ArrayList<String>();
                Collections.addAll(systemRoles, userVO.getSystemRoles());
                this.systemRoles = systemRoles;
            }
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBbLocalId() {
        return bbLocalId;
    }

    public void setBbLocalId(String bbLocalId) {
        this.bbLocalId = bbLocalId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<String> getInsRoles() {
        return insRoles;
    }

    public void setInsRoles(List<String> insRoles) {
        this.insRoles = insRoles;
    }

    public List<String> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(List<String> systemRoles) {
        this.systemRoles = systemRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", bbLocalId='" + bbLocalId + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", genderType='" + genderType + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", insRoles=" + insRoles +
                ", systemRoles=" + systemRoles +
                '}';
    }

}
