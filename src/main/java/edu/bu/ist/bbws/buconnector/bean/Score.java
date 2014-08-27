package edu.bu.ist.bbws.buconnector.bean;

import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;

/**
 * Created by mkousheh on 8/25/14.
 */
public class Score {

    private String bbId;
    private Column column;
    private Course course;
    private String grade;
    private String firstAttemptId;
    private String lastAttemptId;
    private String lowestAttemptId;
    private String highestAttemptId;
    private int status;
 //   CourseMembership membership;
    //Member memberId = {java.lang.String@3219}"_784185_1"
    //Schema SchemaGradeValue = {java.lang.String@3220}"460.00"
    private String shortInstructorComments;
    private String shortStudentComments;
    private String instructorComments;
    private String studentComments;
    private double averageScore;

    public Score() {
    }

    public Score(GradebookWSStub.ScoreVO scoreVO) {
        this.bbId = scoreVO.getId();
  //      this.column = scoreVO.getColumnId();
  //      this.course = scoreVO.getCourseId();
        this.grade = scoreVO.getGrade();
        this.firstAttemptId = scoreVO.getFirstAttemptId();
        this.lastAttemptId = scoreVO.getLastAttemptId();
        this.lowestAttemptId = scoreVO.getLowestAttemptId();
        this.highestAttemptId = scoreVO.getHighestAttemptId();
        this.status = scoreVO.getStatus();
        //   CourseMembership membership;

        //Member memberId = {java.lang.String@3219}"_784185_1"
        //Schema SchemaGradeValue = {java.lang.String@3220}"460.00"
        this.shortInstructorComments = scoreVO.getShortInstructorComments();
        this.shortStudentComments = scoreVO.getShortStudentComments();
        this.instructorComments = scoreVO.getInstructorComments();
        this.studentComments = scoreVO.getStudentComments();
        this.averageScore = scoreVO.getAverageScore();

    }
    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getFirstAttemptId() {
        return firstAttemptId;
    }

    public void setFirstAttemptId(String firstAttemptId) {
        this.firstAttemptId = firstAttemptId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLowestAttemptId() {
        return lowestAttemptId;
    }

    public void setLowestAttemptId(String lowestAttemptId) {
        this.lowestAttemptId = lowestAttemptId;
    }

    public String getHighestAttemptId() {
        return highestAttemptId;
    }

    public void setHighestAttemptId(String highestAttemptId) {
        this.highestAttemptId = highestAttemptId;
    }

    public String getLastAttemptId() {
        return lastAttemptId;
    }

    public void setLastAttemptId(String lastAttemptId) {
        this.lastAttemptId = lastAttemptId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShortInstructorComments() {
        return shortInstructorComments;
    }

    public void setShortInstructorComments(String shortInstructorComments) {
        this.shortInstructorComments = shortInstructorComments;
    }

    public String getShortStudentComments() {
        return shortStudentComments;
    }

    public void setShortStudentComments(String shortStudentComments) {
        this.shortStudentComments = shortStudentComments;
    }

    public String getInstructorComments() {
        return instructorComments;
    }

    public void setInstructorComments(String instructorComments) {
        this.instructorComments = instructorComments;
    }

    public String getStudentComments() {
        return studentComments;
    }

    public void setStudentComments(String studentComments) {
        this.studentComments = studentComments;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "bbId='" + bbId + '\'' +
                ", column=" + column +
                ", course=" + course +
                ", grade='" + grade + '\'' +
                ", firstAttemptId='" + firstAttemptId + '\'' +
                ", lastAttemptId='" + lastAttemptId + '\'' +
                ", lowestAttemptId='" + lowestAttemptId + '\'' +
                ", highestAttemptId='" + highestAttemptId + '\'' +
                ", status=" + status +
                ", shortInstructorComments='" + shortInstructorComments + '\'' +
                ", shortStudentComments='" + shortStudentComments + '\'' +
                ", instructorComments='" + instructorComments + '\'' +
                ", studentComments='" + studentComments + '\'' +
                ", averageScore='" + averageScore + '\'' +
                '}';
    }
}
