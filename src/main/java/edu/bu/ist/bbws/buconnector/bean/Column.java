package edu.bu.ist.bbws.buconnector.bean;

import java.sql.Date;

/**
 * Created by mkousheh on 8/25/14.
 */
public class Column {

    String aggregationModel;
    String calculationType;
    String columnDisplayName;
    String ColumnName;
    //ContentId
    Course Course;
    Date dateCreated;
    Date dateModified;
    String description;
    String descriptionType;
    Date dueDate;
    String bbId;
    boolean isMultipleAttempts;
    int possible;
    int weight;

    public String getAggregationModel() {
        return aggregationModel;
    }

    public void setAggregationModel(String aggregationModel) {
        this.aggregationModel = aggregationModel;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public String getColumnDisplayName() {
        return columnDisplayName;
    }

    public void setColumnDisplayName(String columnDisplayName) {
        this.columnDisplayName = columnDisplayName;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public Course getCourse() {
        return Course;
    }

    public void setCourse(Course course) {
        Course = course;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public boolean isMultipleAttempts() {
        return isMultipleAttempts;
    }

    public void setMultipleAttempts(boolean isMultipleAttempts) {
        this.isMultipleAttempts = isMultipleAttempts;
    }

    public int getPossible() {
        return possible;
    }

    public void setPossible(int possible) {
        this.possible = possible;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
