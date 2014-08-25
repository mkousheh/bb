package edu.bu.ist.bbws.buconnector.bean;

/**
 * Created by mkousheh on 8/25/14.
 */
public class Course {

    boolean available;
    String batchUid;
    String duration;
    String courseId;
    String courseServiceLevel;
    String description;
    String enrollmentType;
    String name;
    String institutionName;
    String bbLocalId;
    String startDate;
    String endDate;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBatchUid() {
        return batchUid;
    }

    public void setBatchUid(String batchUid) {
        this.batchUid = batchUid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseServiceLevel() {
        return courseServiceLevel;
    }

    public void setCourseServiceLevel(String courseServiceLevel) {
        this.courseServiceLevel = courseServiceLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnrollmentType() {
        return enrollmentType;
    }

    public void setEnrollmentType(String enrollmentType) {
        this.enrollmentType = enrollmentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getBbLocalId() {
        return bbLocalId;
    }

    public void setBbLocalId(String bbLocalId) {
        this.bbLocalId = bbLocalId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
