package edu.bu.ist.bbws.buconnector.bean;

import edu.bu.ist.bbws._generated.user.UserWSStub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkousheh on 8/25/14.
 */
public class User {

    String userName;
    String studentId;
    String bbLocalId;
    String emailAddress;
    String familyName;
    String givenName;
    String genderType;
    String educationLevel;
    String title;
    boolean isAvailable;
    List<String> insRoles;
    List<String> systemRoles;

    public User() {
    }

        public User(UserWSStub.UserVO userVO) {
        this.setAvailable(userVO.getIsAvailable());
        this.setBbLocalId(userVO.getId());
        this.setEducationLevel(userVO.getEducationLevel());

        if (userVO.getExtendedInfo().getEmailAddress() != null) {
            this.setEmailAddress(userVO.getExtendedInfo().getEmailAddress());
        }

        if (userVO.getExtendedInfo().getFamilyName() != null) {
            this.setFamilyName(userVO.getExtendedInfo().getFamilyName());
        }
        if (userVO.getExtendedInfo().getGivenName() != null) {
            this.setGivenName(userVO.getExtendedInfo().getGivenName());
        }
        this.setGenderType(userVO.getGenderType());
        this.setStudentId(userVO.getStudentId());
        this.setTitle(userVO.getTitle());
        this.setUserName(userVO.getName());

        if (userVO.getInsRoles() != null) {
            List<String> instRoles = new ArrayList<String>();
            for (String insRole : userVO.getInsRoles()) {
                instRoles.add(insRole);
            }
            this.setInsRoles(instRoles);
        }

        if (userVO.getSystemRoles() != null) {
            List<String> systemRoles = new ArrayList<String>();
            for (String systemRole : userVO.getSystemRoles()) {
                systemRoles.add(systemRole);
            }
            this.setSystemRoles(systemRoles);
        }   }


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
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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
                ", isAvailable=" + isAvailable +
                ", insRoles=" + insRoles +
                ", systemRoles=" + systemRoles +
                '}';
    }
}
