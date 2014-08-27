package edu.bu.ist.bbws.buconnector.bean;

import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;


/**
 * Created by mkousheh on 8/25/14.
 */
public  class CourseMembership {
    String bbId;
    User user;
    Course course;
    Long enrollmentDate;
    CourseMembershipRole courseMembershipRole;
    boolean available;

    public CourseMembership() {
    }

    public CourseMembership(CourseMembershipWSStub.CourseMembershipVO courseMembershipVO) {
        this.bbId = courseMembershipVO.getId();
        this.user = new User(courseMembershipVO.getUserId());
        this.course = new Course(courseMembershipVO.getCourseId());
        this.enrollmentDate = courseMembershipVO.getEnrollmentDate();
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

    public Long getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Long enrollmentDate) {
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
