package edu.bu.ist.bbws.buconnector.model;

import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.buconnector.service.user.UserService;

import java.rmi.RemoteException;
import java.sql.Date;


/**
 * Created by mkousheh on 8/25/14.
 */
public  class CourseMembership {
    private String bbId;
    private String username;
    private String courseId;
    private Date enrollmentDate;
    private CourseMembershipRole courseMembershipRole;
    private boolean available;

    public CourseMembership() {
    }

    public CourseMembership(CourseMembershipWSStub.CourseMembershipVO courseMembershipVO, CourseService courseService, UserService userService, CoursemembershipService coursemembershipService) {
        this.bbId = courseMembershipVO.getId();
        try {
            this.username = userService.getUserByUserBbId(courseMembershipVO.getUserId()).getName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            this.courseId = courseService.getCourseByBbId(courseMembershipVO.getCourseId()).getCourseId();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.enrollmentDate = new Date(courseMembershipVO.getEnrollmentDate()*1000);
        this.courseMembershipRole = new CourseMembershipRole(courseMembershipVO.getRoleId(), coursemembershipService);
        this.available = courseMembershipVO.getAvailable();
    }

    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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
                ", username='" + username + '\'' +
                ", courseId='" + courseId + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", courseMembershipRole=" + courseMembershipRole +
                ", available=" + available +
                '}';
    }
}
