package edu.bu.ist.bbws.buconnector.bean;

/**
 * Created by mkousheh on 8/25/14.
 */
public class CourseMembership {
    boolean isAvailable;
    Course course;
    Long enrollmentDate;
    String bbId;
    CourseRole role;
    User user;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Long enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public CourseRole getRole() {
        return role;
    }

    public void setRole(CourseRole role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
