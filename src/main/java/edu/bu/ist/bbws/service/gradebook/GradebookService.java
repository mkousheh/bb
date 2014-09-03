package edu.bu.ist.bbws.service.gradebook;

import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created by mkousheh on 8/24/14.
 */
public interface GradebookService {

    public GradebookWSStub.ColumnVO[] getCourseColumns( String courseId) throws RemoteException;

//    public GradebookWSStub.ColumnVO getCourseColumnByColumnName(String courseId, String columnName)  throws RemoteException;

    public GradebookWSStub.ColumnVO[] getCourseColumnsByColumnName(String courseId, String columnName)  throws RemoteException;

    public GradebookWSStub.ScoreVO[] getCourseTotalScore(String username) throws RemoteException;

    public GradebookWSStub.ScoreVO[] getCourseScoreByColumn(String courseId, String columnName) throws RemoteException;

    public GradebookWSStub.ScoreVO[] getCourseScoreByUserAndColumn(String courseId, String username, String columnName) throws RemoteException;

    public List<GradebookWSStub.ScoreVO> getCourseScoreByUserAndColumnAfterDate(String courseId, String username, String columnName, Date submissionDate) throws RemoteException;

    public List<GradebookWSStub.ScoreVO> getCourseScoreByUserAfterDate(String courseId, String username, Date submissionDate) throws RemoteException;

    public List<GradebookWSStub.ScoreVO> getCourseScoreByColumnAfterSubmissionDate(String courseId, String columnName, Date submissionDate) throws RemoteException;

}
