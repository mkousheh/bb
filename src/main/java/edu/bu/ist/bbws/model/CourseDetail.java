package edu.bu.ist.bbws.model;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.coursemembership.CourseMembershipWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.service.course.CourseService;
import edu.bu.ist.bbws.service.course.CourseServiceImpl;
import edu.bu.ist.bbws.service.coursemembership.CoursemembershipService;
import edu.bu.ist.bbws.service.gradebook.GradebookService;
import edu.bu.ist.bbws.service.user.UserService;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkousheh on 8/25/14.
 */
public class CourseDetail {

    private String bbId;
    private String courseId;
    private String batchUid;
    private String name;
    private String description;
    private String enrollmentType;
    private Date startDate;
    private Date endDate;
    private String duration;
    private boolean available;
    private String institutionName;
    private String courseServiceLevel;
    private List<Column> columns;
    private List<User> enrolledStudents;
    private List<User> intructors;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;


    public CourseDetail() {
    }

    public CourseDetail(CourseWSStub.CourseVO courseV0, UserService userService, GradebookService gradebookService, CoursemembershipService coursemembershipService, CourseService courseService) {

        this.bbId = courseV0.getId();
        this.courseId = courseV0.getCourseId();
        this.batchUid = courseV0.getBatchUid();
        this.name = courseV0.getName();
        this.description = courseV0.getDescription();
        this.enrollmentType = courseV0.getEnrollmentType();
        this.startDate = new Date(courseV0.getStartDate() * 1000);
        this.endDate = new Date(courseV0.getEndDate() * 1000);
        this.duration = courseV0.getCourseDuration();
        this.available = courseV0.getAvailable();
        this.institutionName = courseV0.getInstitutionName();
        this.courseServiceLevel = courseV0.getCourseServiceLevel();

//        this.columns = getBuConnectorController().getCourseColumns(courseV0.getCourseId());

        try {
            columns = new ArrayList<Column>();
            GradebookWSStub.ColumnVO[] columnVOs = gradebookService.getCourseColumns(courseV0.getCourseId());
            for (GradebookWSStub.ColumnVO columnVO:columnVOs){
                this.columns.add (new Column(columnVO, courseService));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            enrolledStudents = new ArrayList<User>();
            intructors = new ArrayList<User>();
            UserWSStub.UserVO[] userV0s = userService.getCourseUsersByCourseId(courseV0.getCourseId());
            for (UserWSStub.UserVO userVO:userV0s){
                List<CourseMembership> courseMemberships= new ArrayList<CourseMembership>();
                CourseMembershipWSStub.CourseMembershipVO[] courseMembershipVOs = coursemembershipService.getCourseMembership(userVO.getName(), courseV0.getCourseId());
                for (CourseMembershipWSStub.CourseMembershipVO courseMembershipVO:courseMembershipVOs){
                    if (courseMembershipVO != null) {
                        courseMemberships.add(new CourseMembership(courseMembershipVO, courseService, userService, coursemembershipService));
                        if (coursemembershipService.getCourseMembershipRoleById(courseMembershipVO.getRoleId()).getRoleIdentifier().toString().equalsIgnoreCase("S")) {
                            this.enrolledStudents.add(new User(userVO));
                        } else if (coursemembershipService.getCourseMembershipRoleById(courseMembershipVO.getRoleId()).getRoleIdentifier().toString().contains("Instructor")) {
                            this.intructors.add(new User(userVO));
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public CourseDetail(String courseBbId) {


        CourseWSStub.CourseVO courseV0;
        try {
            courseV0 = new CourseServiceImpl().getCourseByBbId(courseBbId);
            if (courseV0 != null) {
                this.bbId = courseV0.getId();
                this.courseId = courseV0.getCourseId();
                this.batchUid = courseV0.getBatchUid();
                this.name = courseV0.getName();
                this.description = courseV0.getDescription();
                this.enrollmentType = courseV0.getEnrollmentType();
                this.startDate = new Date(courseV0.getStartDate() * 1000);
                this.endDate = new Date(courseV0.getEndDate() * 1000);
                this.duration = courseV0.getCourseDuration();
                this.available = courseV0.getAvailable();
                this.institutionName = courseV0.getInstitutionName();
                this.courseServiceLevel = courseV0.getCourseServiceLevel();
 //               this.columns = getBuConnectorController().getCourseColumns(courseV0.getCourseId());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getBbId() {
        return bbId;
    }

    private void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public String getCourseId() {
        return courseId;
    }

    private void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getBatchUid() {
        return batchUid;
    }

    private void setBatchUid(String batchUid) {
        this.batchUid = batchUid;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getEnrollmentType() {
        return enrollmentType;
    }

    private void setEnrollmentType(String enrollmentType) {
        this.enrollmentType = enrollmentType;
    }

    public Date getStartDate() {
        return startDate;
    }

    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    private void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isAvailable() {
        return available;
    }

    private void setAvailable(boolean available) {
        this.available = available;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    private void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getCourseServiceLevel() {
        return courseServiceLevel;
    }

    private void setCourseServiceLevel(String courseServiceLevel) {
        this.courseServiceLevel = courseServiceLevel;
    }

    public List<Column> getColumns() {
        return columns;
    }

    private void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<User> getEnrolledStudents() {
        return enrolledStudents;
    }

    private void setEnrolledStudents(List<User> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<User> getIntructors() {
        return intructors;
    }

    private void setIntructors(List<User> intructors) {
        this.intructors = intructors;
    }

    @Override
    public String toString() {
        return "Course{" +
                "bbId='" + bbId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", batchUid='" + batchUid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", enrollmentType='" + enrollmentType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration='" + duration + '\'' +
                ", available=" + available +
                ", institutionName='" + institutionName + '\'' +
                ", courseServiceLevel='" + courseServiceLevel + '\'' +
                ", columns=" + columns +
                ", enrolledStudents=" + enrolledStudents +
                ", intructors=" + intructors +
                '}';
    }
}
