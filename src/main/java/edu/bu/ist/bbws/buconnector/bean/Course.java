package edu.bu.ist.bbws.buconnector.bean;

import edu.bu.ist.bbws._generated.course.CourseWSStub;

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

    public Course() {
    }

    public Course(CourseWSStub.CourseVO courseV0) {
        this.courseId = courseV0.getCourseId();
        this.available = courseV0.getAvailable();
        this.batchUid = courseV0.getBatchUid();
        this.bbLocalId = courseV0.getId();
        this.courseServiceLevel = courseV0.getCourseServiceLevel();
        this.courseId = courseV0.getCourseId();
        this.description = courseV0.getDescription();
        this.duration = courseV0.getCourseDuration();
        this.enrollmentType = courseV0.getEnrollmentType();
        this.institutionName = courseV0.getInstitutionName();
        this.name = courseV0.getName();
//                this.startDate = courseV0.getStartDate();
//                this.endDate = courseV0.getEndDate();

    }

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

    @Override
    public String toString() {
        return "Course{" +
                "available=" + available +
                ", batchUid='" + batchUid + '\'' +
                ", duration='" + duration + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseServiceLevel='" + courseServiceLevel + '\'' +
                ", description='" + description + '\'' +
                ", enrollmentType='" + enrollmentType + '\'' +
                ", name='" + name + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", bbLocalId='" + bbLocalId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
