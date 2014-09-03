package edu.bu.ist.bbws.model;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws.service.course.CourseService;
import edu.bu.ist.bbws.service.course.CourseServiceImpl;
import edu.bu.ist.bbws.service.gradebook.GradebookService;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkousheh on 8/25/14.
 */
public class CourseBasic {

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

    public CourseBasic() {
    }

    public CourseBasic(CourseWSStub.CourseVO courseV0, CourseService courseService, GradebookService gradebookService) {

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
        try {
            columns = new ArrayList<Column>();
            GradebookWSStub.ColumnVO[] columnVOs = gradebookService.getCourseColumns(courseV0.getCourseId());
            for (GradebookWSStub.ColumnVO columnVO:columnVOs){
                this.columns.add (new Column(columnVO, courseService));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public CourseBasic(String courseBbId, CourseService courseService, GradebookService gradebookService) {
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
                try {
                    columns = new ArrayList<Column>();
                    GradebookWSStub.ColumnVO[] columnVOs = gradebookService.getCourseColumns(courseV0.getCourseId());
                    for (GradebookWSStub.ColumnVO columnVO:columnVOs){
                        this.columns.add (new Column(columnVO, courseService));
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getBatchUid() {
        return batchUid;
    }

    public void setBatchUid(String batchUid) {
        this.batchUid = batchUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getCourseServiceLevel() {
        return courseServiceLevel;
    }

    public void setCourseServiceLevel(String courseServiceLevel) {
        this.courseServiceLevel = courseServiceLevel;
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

                '}';
    }
}
