package edu.bu.ist.bbws.buconnector.bean;

import java.util.ArrayList;

/**
 * Created by mkousheh on 8/25/14.
 */
public class User {

    String userName;
    String studentId;
    String bbId;
    String emailAddress;
    String familyName;
    String givenName;
    String genderType;
    String educationLevel;
    String title;
    boolean isAvailable;
    ArrayList<String> insRoles;
    ArrayList<String> systemRoles;

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

    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
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

    public ArrayList<String> getInsRoles() {
        return insRoles;
    }

    public void setInsRoles(ArrayList<String> insRoles) {
        this.insRoles = insRoles;
    }

    public ArrayList<String> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(ArrayList<String> systemRoles) {
        this.systemRoles = systemRoles;
    }
}
