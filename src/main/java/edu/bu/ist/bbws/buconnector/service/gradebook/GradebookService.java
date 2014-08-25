package edu.bu.ist.bbws.buconnector.service.gradebook;

import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface GradebookService {

    public GradebookWSStub.ColumnVO[] getCourseColumns( String courseId) throws RemoteException;

    public GradebookWSStub.ColumnVO getCourseColumnByColumnName(String courseId, String columnName)  throws RemoteException;;

    public GradebookWSStub.ScoreVO[] getCourseTotalScore(String username) throws RemoteException;

    public GradebookWSStub.ScoreVO[] getCourseScoreByColumn(String courseId, String columnName) throws RemoteException;

    public GradebookWSStub.ScoreVO[] getCourseScoreByColumnAfterSubmissionDate(String courseId, String columnName, String submissionDate) throws RemoteException;


}
