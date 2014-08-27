package edu.bu.ist.bbws.buconnector.bean;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws.buconnector.service.course.CourseServiceImpl;

import java.rmi.RemoteException;
import java.sql.Date;

/**
 * Created by mkousheh on 8/25/14.
 */
public class Column {

    String bbId;
    String courseId;
    String columnName;
    String columnDisplayName;
    String description;
    String descriptionType;
    int multipleAttempts;
    Date dateCreated;
    Date dateModified;
    String calculationType;
    String aggregationModel;
    Date dueDate;
    Double possible;
    float weight;

    public Column() {
    }

    public Column(GradebookWSStub.ColumnVO columnVO) {
        this.bbId = columnVO.getId();
        this.courseId = columnVO.getCourseId();
        this.columnName = columnVO.getColumnName();
        this.columnDisplayName = columnVO.getColumnDisplayName();
        this.description = columnVO.getDescription();
        this.descriptionType = columnVO.getDescriptionType();
        this.multipleAttempts = columnVO.getMultipleAttempts();
        this.dateCreated = new Date (columnVO.getDateCreated()*1000);
        this.dateModified = new Date(columnVO.getDateModified()*1000);
        this.calculationType = columnVO.getCalculationType();
        this.dueDate = new Date(columnVO.getDueDate()*1000);
        this.possible = columnVO.getPossible();
        this.weight = columnVO.getWeight();
    }

    public Column(String courseId) {
        this.courseId = courseId;
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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDisplayName() {
        return columnDisplayName;
    }

    public void setColumnDisplayName(String columnDisplayName) {
        this.columnDisplayName = columnDisplayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }

    public int getMultipleAttempts() {
        return multipleAttempts;
    }

    public void setMultipleAttempts(int multipleAttempts) {
        this.multipleAttempts = multipleAttempts;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public String getAggregationModel() {
        return aggregationModel;
    }

    public void setAggregationModel(String aggregationModel) {
        this.aggregationModel = aggregationModel;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getPossible() {
        return possible;
    }

    public void setPossible(Double possible) {
        this.possible = possible;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }



    @Override
    public String toString() {
        return "Column{" +
                "bbId='" + bbId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnDisplayName='" + columnDisplayName + '\'' +
                ", description='" + description + '\'' +
                ", descriptionType='" + descriptionType + '\'' +
                ", multipleAttempts=" + multipleAttempts +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", calculationType='" + calculationType + '\'' +
                ", aggregationModel='" + aggregationModel + '\'' +
                ", dueDate=" + dueDate +
                ", possible=" + possible +
                ", weight=" + weight +
                '}';
    }
}
