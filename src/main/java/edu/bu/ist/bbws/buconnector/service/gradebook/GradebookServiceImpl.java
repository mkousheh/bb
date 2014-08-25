package edu.bu.ist.bbws.buconnector.service.gradebook;

import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;

/**
 * Created by mkousheh on 8/24/14.
 */
public class GradebookServiceImpl implements GradebookService {
    private static final Logger logger = Logger.getLogger(GradebookServiceImpl.class.getName());

    private ContextService contextService;
    private CourseService courseService;
    private ConnectorUtil connectorUtil;


    /**
     * gets gradebook columns for a given course
     * @param courseId
     * @return
     * @throws RemoteException
     */
    public GradebookWSStub.ColumnVO[] getCourseColumns(String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ColumnVO[] bbCrsColums=null;

        String courseInternalId = getCourseService().getCourseById(courseId).getId();
        try {
            GradebookWSStub.ColumnFilter columnFilter = new GradebookWSStub.ColumnFilter();
            columnFilter.setFilterType(1);  // GET_COLUMN_BY_COURSE_ID=1

            GradebookWSStub.GetGradebookColumns getColumns = new GradebookWSStub.GetGradebookColumns();
            getColumns.setFilter(columnFilter);
            getColumns.setCourseId(courseInternalId);

            GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
            getContextService().client_engage(gradebookWSStub._getServiceClient());
            GradebookWSStub.GetGradebookColumnsResponse columnsResponse = gradebookWSStub.getGradebookColumns(getColumns);
            bbCrsColums = columnsResponse.get_return();
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return bbCrsColums;
    }

    /**
     * gets course column object by providing column anme
      * @param courseId
     * @param columnName
     * @return
     * @throws RemoteException
     */
    public GradebookWSStub.ColumnVO getCourseColumnByColumnName(String courseId, String columnName) throws RemoteException {
        GradebookWSStub.ColumnVO[] courseColumns = getCourseColumns(courseId);
        for (GradebookWSStub.ColumnVO courseColumn : courseColumns){
            if (courseColumn.getColumnDisplayName().equalsIgnoreCase(columnName)){
                return courseColumn;
            }
        }
        return null;
    }

    /**
     * gets all scores for a given course
     * @param courseId
     * @return
     * @throws RemoteException
     */
    public GradebookWSStub.ScoreVO[] getCourseTotalScore(String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ScoreVO[] scoreVOs  = null;
        String courseInternalId = getCourseService().getCourseById(courseId).getId();
        try{
            GradebookWSStub.ScoreFilter scoreFilter = new GradebookWSStub.ScoreFilter();
            scoreFilter.setFilterType(4);  // GET_SCORE_BY_COURSE_ID=1   use 4 for final grade -- 5 with member
            //scoreFilter.setColumnId("_27514_1");
            //scoreFilter.setMemberIds(new String[]{"_810183_1"});
            GradebookWSStub.GetGrades grades = new GradebookWSStub.GetGrades();
            grades.setFilter(scoreFilter);

            grades.setCourseId(courseInternalId);

            GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
            getContextService().client_engage(gradebookWSStub._getServiceClient());
            GradebookWSStub.GetGradesResponse gradesResponse = gradebookWSStub.getGrades(grades);

            scoreVOs  = gradesResponse.get_return() ;
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return scoreVOs;
    }

    /**
     * gets all scores by column for a course
      * @param courseId
     * @param columnName
     * @return
     * @throws RemoteException
     */
    public GradebookWSStub.ScoreVO[] getCourseScoreByColumn(String courseId, String columnName) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ScoreVO[] scoreVOs  = null;
        String courseInternalId = getCourseService().getCourseById(courseId).getId();
        String columnInternalId = getCourseColumnByColumnName(courseId, columnName).getId();
        try{
            GradebookWSStub.ScoreFilter scoreFilter = new GradebookWSStub.ScoreFilter();
            scoreFilter.setFilterType(3);  // GET_SCORE_BY_COURSE_ID=1   use 4 for final grade
            scoreFilter.setColumnId(columnInternalId);
//            scoreFilter.setMemberIds(new String[]{"_810183_1"});
            GradebookWSStub.GetGrades grades = new GradebookWSStub.GetGrades();
            grades.setFilter(scoreFilter);

            grades.setCourseId(courseInternalId);

            GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
            getContextService().client_engage(gradebookWSStub._getServiceClient());
            GradebookWSStub.GetGradesResponse gradesResponse = gradebookWSStub.getGrades(grades);

            scoreVOs  = gradesResponse.get_return() ;
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return scoreVOs;
    }

    /**
     *
     * @param courseId
     * @param columnName
     * @param submissionDate
     * @return
     * @throws RemoteException
     */
    public GradebookWSStub.ScoreVO[] getCourseScoreByColumnAfterSubmissionDate(String courseId, String columnName, String submissionDate) throws RemoteException {
        GradebookWSStub.ScoreVO[] scores = getCourseScoreByColumn(courseId, columnName);
        GradebookWSStub.ScoreVO[] filteredScores = null;

        for (GradebookWSStub.ScoreVO score: scores){
            if (score.getLastAttemptId() != null) {
                GradebookWSStub.AttemptVO lastAttempt = getCourseScoreForColumnByLastAttempt(courseId, score.getLastAttemptId());  //.getLastAttemptId()
                if (lastAttempt != null) {
                    lastAttempt.getAttemptDate();
                    String a = "a";
                }
            }
        }
        return scores;
    }

    /**
     *
     * @param courseId
     * @param lastAttemptId
     * @return
     * @throws RemoteException
     */
    public GradebookWSStub.AttemptVO getCourseScoreForColumnByLastAttempt(String courseId, String lastAttemptId) throws RemoteException {
        GradebookWSStub.AttemptVO[] attemptVOs  = null;
        GradebookWSStub.AttemptVO attemptVO  = null;
        if (lastAttemptId != null){
            ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
            String courseInternalId = getCourseService().getCourseById(courseId).getId();
            try{
                GradebookWSStub.AttemptFilter attemptFilter = new GradebookWSStub.AttemptFilter();
                attemptFilter.setFilterType(3);  // 2: GET_ATTEMPT_BY_GRADE_ID_AND_LAST_ATTEMPT 3: GET_ATTEMPT_BY_IDS
//            attemptFilter.setGradeId(gradeId);
                attemptFilter.setIds(new String[]{lastAttemptId});

                GradebookWSStub.GetAttempts attempts = new GradebookWSStub.GetAttempts();
                attempts.setFilter(attemptFilter);
                attempts.getCourseId();
//            attempts.setCourseId(searchCourse);


                GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
                getContextService().client_engage(gradebookWSStub._getServiceClient());
                GradebookWSStub.GetAttemptsResponse gradesResponse = gradebookWSStub.getAttempts(attempts);

                attemptVOs  = gradesResponse.get_return();  // assuming there is only one attempt as a last attempt
                if (attemptVOs != null) {
                    attemptVO = attemptVOs[0];
                }
            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseScoreByAttempt method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return attemptVO;
    }

    /**
     *
      * @param courseId
     * @param gradeId
     * @return
     * @throws RemoteException
     */
    public GradebookWSStub.AttemptVO[] getCourseGradeAttempts(String courseId, String gradeId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.AttemptVO[] attemptVOs  = null;
        String courseInternalId = getCourseService().getCourseById(courseId).getId();
        try {
            GradebookWSStub.AttemptFilter attemptFilter = new GradebookWSStub.AttemptFilter();
            attemptFilter.setFilterType(1);  // 1 GET_ATTEMPT_BY_GRADE_ID
            attemptFilter.setGradeId(gradeId);
            GradebookWSStub.GetAttempts attempts = new GradebookWSStub.GetAttempts();
            attempts.setFilter(attemptFilter);
            attempts.setCourseId(courseInternalId);

            GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                    "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
            getContextService().client_engage(gradebookWSStub._getServiceClient());
            GradebookWSStub.GetAttemptsResponse gradesResponse = gradebookWSStub.getAttempts(attempts);

            attemptVOs  = gradesResponse.get_return() ;
        } catch (RemoteException e) {
            logger.error("There was a problem executing the getCourseScoreByAttempt method : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            ctx.terminate();
        }
        return attemptVOs;
    }



    public ContextService getContextService() {
        return contextService;
    }

    public void setContextService(ContextService contextService) {
        this.contextService = contextService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public ConnectorUtil getConnectorUtil() {
        return connectorUtil;
    }

    public void setConnectorUtil(ConnectorUtil connectorUtil) {
        this.connectorUtil = connectorUtil;
    }
}
