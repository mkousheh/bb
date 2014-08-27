package edu.bu.ist.bbws.buconnector.model;

import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;

import java.sql.Date;


/**
 * Created by mkousheh on 8/25/14.
 */
public  class CourseMembership {
    private String bbId;
    private User user;
    private Course course;
    private Date enrollmentDate;
    private CourseMembershipRole courseMembershipRole;
    private boolean available;

    public CourseMembership() {
    }

    public CourseMembership(CourseMembershipWSStub.CourseMembershipVO courseMembershipVO) {
        this.bbId = courseMembershipVO.getId();
        this.user = new User(courseMembershipVO.getUserId());
        this.course = new Course(courseMembershipVO.getCourseId());
        this.enrollmentDate = new Date(courseMembershipVO.getEnrollmentDate()*1000);
        this.courseMembershipRole = new CourseMembershipRole(courseMembershipVO.getRoleId());
        this.available = courseMembershipVO.getAvailable();
    }

    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public CourseMembershipRole getCourseMembershipRole() {
        return courseMembershipRole;
    }

    public void setCourseMembershipRole(CourseMembershipRole courseMembershipRole) {
        this.courseMembershipRole = courseMembershipRole;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "CourseMembership{" +
                "bbId='" + bbId + '\'' +
                ", user=" + user.toString() +
                ", course=" + course.toString() +
                ", enrollmentDate=" + enrollmentDate +
                ", courseMembershipRole=" + courseMembershipRole.toString() +
                ", available=" + available +
                '}';
    }
}
