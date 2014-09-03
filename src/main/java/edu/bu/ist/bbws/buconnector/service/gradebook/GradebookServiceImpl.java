package edu.bu.ist.bbws.buconnector.service.gradebook;

import edu.bu.ist.bbws._generated.course.CourseWSStub;
import edu.bu.ist.bbws._generated.gradebook.GradebookWSStub;
import edu.bu.ist.bbws._generated.user.UserWSStub;
import edu.bu.ist.bbws.buconnector.model.Score;
import edu.bu.ist.bbws.buconnector.service.context.ContextService;
import edu.bu.ist.bbws.buconnector.service.context.ContextServiceImpl;
import edu.bu.ist.bbws.buconnector.service.course.CourseService;
import edu.bu.ist.bbws.buconnector.service.course.CourseServiceImpl;
import edu.bu.ist.bbws.buconnector.service.user.UserService;
import edu.bu.ist.bbws.buconnector.service.user.UserServiceImpl;
import edu.bu.ist.bbws.buconnector.utils.ConnectorUtil;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by mkousheh on 8/24/14.
 */
public class GradebookServiceImpl implements GradebookService {
    private static final Logger logger = Logger.getLogger(GradebookServiceImpl.class.getName());

    public static final int GET_SCORE_BY_COURSE_ID = 1;
    public static final int GET_SCORE_BY_COLUMN_ID_AND_USER_ID = 2;
    public static final int GET_SCORE_BY_COLUMN_ID = 3;
    // final grade is also know is external grade
    public static final int GET_SCORE_BY_COURSE_ID_AND_FINAL_GRADE = 4;
    public static final int GET_SCORE_BY_MEMBER_ID_AND_COLUMN_ID = 5;
    public static final int GET_SCORE_BY_MEMBER_ID = 6;
    public static final int GET_SCORE_BY_ID = 7;
    public static final int GET_SCORE_BY_USER_ID = 8;
    public static final int GET_SCORE_BY_USER_ID_AND_FINAL_GRADE = 9;
    public static final int GET_SCORE_BY_MEMBER_IDS_AND_COLUMN_ID = 10;

    /**
     * Supported filter type values which must be specified for loading attempts
     */
    public static final int GET_ATTEMPT_BY_GRADE_ID = 1;
    public static final int GET_ATTEMPT_BY_GRADE_ID_AND_LAST_ATTEMPT = 2;
    public static final int GET_ATTEMPT_BY_IDS = 3;

    /**
     * Supported filter type values which must be specified for loading columns
     */

    /** For GET_BY_COURSE_ID, framework will pass in the course id.  So, no course id need to be set in the filter. */
    public static final int GET_COLUMN_BY_COURSE_ID = 1;
    /** For GET_BY_COURSE_ID_AND_COLUMN_NAME, courseId and name must be populated */
    public static final int GET_COLUMN_BY_COURSE_ID_AND_COLUMN_NAME = 2;
    /** For GET_BY_ID, id(s) must be populated */
    public static final int GET_COLUMN_BY_IDS = 3;
    /** For GET_BY_EXTERNAL_GRADE_FLAG, courseId must be populated */
    public static final int GET_COLUMN_BY_EXTERNAL_GRADE_FLAG = 4;
    @Autowired
    private ConnectorUtil connectorUtil;
    @Autowired
    private ContextServiceImpl contextService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private UserServiceImpl userService;

    /**
      * gets gradebook columns for a given course
      *
      * @param courseId - bb course identifier
      * @return list of column for given course
      * @throws RemoteException
      */
    public GradebookWSStub.ColumnVO[] getCourseColumns(String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ColumnVO[] bbCrsColums = null;

        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null) {
            courseInternalId = course.getId();
            try {
                GradebookWSStub.ColumnFilter columnFilter = new GradebookWSStub.ColumnFilter();
                columnFilter.setFilterType(GET_COLUMN_BY_COURSE_ID);

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
        }
        return bbCrsColums;
    }

    public GradebookWSStub.ColumnVO[] getCourseColumnsByColumnName(String courseId, String columnName) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ColumnVO[] columnVOs = null;

        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null && columnName != null) {
            courseInternalId = course.getId();
            try {
                GradebookWSStub.ColumnFilter columnFilter = new GradebookWSStub.ColumnFilter();
                columnFilter.setFilterType(GET_COLUMN_BY_COURSE_ID_AND_COLUMN_NAME);
                columnFilter.setNames(new String[]{columnName});

                GradebookWSStub.GetGradebookColumns getColumns = new GradebookWSStub.GetGradebookColumns();
                getColumns.setFilter(columnFilter);
                getColumns.setCourseId(courseInternalId);

                GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
                getContextService().client_engage(gradebookWSStub._getServiceClient());
                GradebookWSStub.GetGradebookColumnsResponse columnsResponse = gradebookWSStub.getGradebookColumns(getColumns);
                columnVOs = columnsResponse.get_return();
            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return columnVOs;
    }
    /**
     * gets course column object by providing column name
     *
     * @param courseId   - bb course identifier
     * @param columnName - bb gradebook column display name
     * @return column object by column name
     * @throws RemoteException
     */
 //   public GradebookWSStub.ColumnVO getCourseColumnByColumnName(String courseId, String columnName) throws RemoteException {
 //       GradebookWSStub.ColumnVO[] courseColumns = getCourseColumns(courseId);
 //       if (courseColumns != null){
 //           for (GradebookWSStub.ColumnVO courseColumn : courseColumns) {
 //               if (courseColumn.getColumnDisplayName() != null && courseColumn.getColumnDisplayName().equalsIgnoreCase(columnName)) {
 //                     return courseColumn;
 //               }
 //           }
 //       }
 //       return null;
 //   }

    /**
     * gets all scores for a given course
     *
     * @param courseId - bb course identifier
     * @return list of scores for a given course
     * @throws RemoteException
     */
    public GradebookWSStub.ScoreVO[] getCourseTotalScore(String courseId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ScoreVO[] scoreVOs = null;
        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null) {
            courseInternalId = course.getId();
            try {
                GradebookWSStub.ScoreFilter scoreFilter = new GradebookWSStub.ScoreFilter();
                scoreFilter.setFilterType(GET_SCORE_BY_COURSE_ID_AND_FINAL_GRADE);
                //scoreFilter.setColumnId("_27514_1");
                //scoreFilter.setMemberIds(new String[]{"_810183_1"});
                GradebookWSStub.GetGrades grades = new GradebookWSStub.GetGrades();
                grades.setFilter(scoreFilter);

                grades.setCourseId(courseInternalId);

                GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
                getContextService().client_engage(gradebookWSStub._getServiceClient());
                GradebookWSStub.GetGradesResponse gradesResponse = gradebookWSStub.getGrades(grades);

                scoreVOs = gradesResponse.get_return();
            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return scoreVOs;
    }


    /**
     * gets all scores by column for a course
     *
     * @param courseId   - bb course identifier
     * @param columnName - bb gradebook column display name
     * @return list of scores for a given course per given column
     * @throws RemoteException
     */
    public GradebookWSStub.ScoreVO[] getCourseScoreByColumn(String courseId, String columnName) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ScoreVO[] scoreVOs = null;

        String courseInternalId = null;
        CourseWSStub.CourseVO courseVO = getCourseService().getCourseById(courseId);
        if (courseVO != null) {
            courseInternalId = courseVO.getId();
        }

        String columnInternalId = null;
        GradebookWSStub.ColumnVO[] columnVOs = getCourseColumnsByColumnName(courseId, columnName);
        if (columnVOs != null) {
            columnInternalId = columnVOs[0].getId();
        }
        if (courseInternalId != null && columnInternalId != null) {
            try {
                GradebookWSStub.ScoreFilter scoreFilter = new GradebookWSStub.ScoreFilter();
                scoreFilter.setFilterType(GET_SCORE_BY_COLUMN_ID);  // GET_SCORE_BY_COURSE_ID=1   use 4 for final grade
                scoreFilter.setColumnId(columnInternalId);
//            scoreFilter.setMemberIds(new String[]{"_810183_1"});
                GradebookWSStub.GetGrades grades = new GradebookWSStub.GetGrades();
                grades.setFilter(scoreFilter);

                grades.setCourseId(courseInternalId);

                GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
                getContextService().client_engage(gradebookWSStub._getServiceClient());
                GradebookWSStub.GetGradesResponse gradesResponse = gradebookWSStub.getGrades(grades);

                scoreVOs = gradesResponse.get_return();
            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return scoreVOs;
    }


    /**
     * gets all scores by column for a course
     *
     * @param courseId - bb course identifier
     * @param username - bb username name
     * @return list of scores for a given course per given column
     * @throws RemoteException
     */
    public GradebookWSStub.ScoreVO[] getCourseScoreByUsername(String courseId, String username) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ScoreVO[] scoreVOs = null;

        String courseInternalId = null;
        CourseWSStub.CourseVO courseVO = getCourseService().getCourseById(courseId);
        if (courseVO != null) {
            courseInternalId = courseVO.getId();
        }

        String userInternalId = null;
        UserWSStub.UserVO user = getUserService().getUserByUsername(username);
        if (user != null) {
            userInternalId = user.getId();
        }


        if (courseInternalId != null) {
            try {
                GradebookWSStub.ScoreFilter scoreFilter = new GradebookWSStub.ScoreFilter();
                scoreFilter.setFilterType(GET_SCORE_BY_USER_ID);
                scoreFilter.setUserIds(new String[]{userInternalId});
//            scoreFilter.setMemberIds(new String[]{"_810183_1"});
                GradebookWSStub.GetGrades grades = new GradebookWSStub.GetGrades();
                grades.setFilter(scoreFilter);

                grades.setCourseId(courseInternalId);

                GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
                getContextService().client_engage(gradebookWSStub._getServiceClient());
                GradebookWSStub.GetGradesResponse gradesResponse = gradebookWSStub.getGrades(grades);

                scoreVOs = gradesResponse.get_return();
            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return scoreVOs;
    }
    /**
     * @param courseId       - bb course identifier
     * @param columnName     - bb gradebook column display name
     * @param submissionDate
     * @return list of scores for given course per column after a given date
     * @throws RemoteException
     */
    public List<GradebookWSStub.ScoreVO> getCourseScoreByColumnAfterSubmissionDate(String courseId, String columnName, Date submissionDate) throws RemoteException {

        List<GradebookWSStub.ScoreVO> scores= new ArrayList<GradebookWSStub.ScoreVO>();
        long submissionDateLong = submissionDate.getTime();

        GradebookWSStub.ScoreVO[] scoreVOs = getCourseScoreByColumn(courseId, columnName);
        for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
            if (scoreVO.getLastAttemptId() != null) {
                GradebookWSStub.AttemptVO lastAttempt = getAttempt(courseId, scoreVO.getLastAttemptId());
                if (lastAttempt != null) {
                    if (lastAttempt.getStatus().equalsIgnoreCase("Completed") && lastAttempt.getCreationDate()*1000 >= submissionDateLong) {
                        Date d = new Date(lastAttempt.getAttemptDate()*1000);
                        logger.info("date: "+d);
                        scores.add(scoreVO);
                    }
                }
            }
        }
        return scores;
    }

    /**
     * @param courseId       - bb course identifier
     * @param columnName     - bb gradebook column display name
     * @param submissionDate
     * @return list of scores for given course per column after a given date
     * @throws RemoteException
     */
    public List<GradebookWSStub.ScoreVO> getCourseScoreByUserAndColumnAfterDate(String courseId, String username, String columnName, Date submissionDate) throws RemoteException {

        List<GradebookWSStub.ScoreVO> scores= new ArrayList<GradebookWSStub.ScoreVO>();
        long submissionDateLong = submissionDate.getTime();

        GradebookWSStub.ScoreVO[] scoreVOs = getCourseScoreByUserAndColumn(courseId, username, columnName);
        for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
            if (scoreVO.getLastAttemptId() != null) {
                GradebookWSStub.AttemptVO lastAttempt = getAttempt(courseId, scoreVO.getLastAttemptId());
                if (lastAttempt != null) {
                    if (lastAttempt.getStatus().equalsIgnoreCase("Completed") ){//&& lastAttempt.getCreationDate()*1000 >= submissionDateLong) {
                        Date d = new Date(lastAttempt.getAttemptDate()*1000);
                        logger.info("date: "+d);
                        scores.add(scoreVO);
                    }
                }
            }
        }
        return scores;
    }

    /**
     * gets all scores by column for a course
     *
     * @param courseId   - bb course identifier
     * @param columnName - bb gradebook column display name
     * @return list of scores for a given course per given column
     * @throws RemoteException
     */
    public GradebookWSStub.ScoreVO[] getCourseScoreByUserAndColumn(String courseId, String username, String columnName) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.ScoreVO[] scoreVOs = null;

        String courseInternalId = null;
        CourseWSStub.CourseVO courseVO = getCourseService().getCourseById(courseId);
        if (courseVO != null) {
            courseInternalId = courseVO.getId();
        }

        String userInternalId = null;
        UserWSStub.UserVO user = getUserService().getUserByUsername(username);
        if (user != null) {
            userInternalId = user.getId();
        }

        String columnInternalId = null;
        GradebookWSStub.ColumnVO[] columnVOs = getCourseColumnsByColumnName(courseId, columnName);
        if (columnVOs != null) {
            columnInternalId = columnVOs[0].getId();
        }
        if (courseInternalId != null && columnInternalId != null) {
            try {
                GradebookWSStub.ScoreFilter scoreFilter = new GradebookWSStub.ScoreFilter();
                scoreFilter.setFilterType(GET_SCORE_BY_COLUMN_ID_AND_USER_ID);
                scoreFilter.setColumnId(columnInternalId);
                scoreFilter.setUserIds(new String[]{userInternalId});
//            scoreFilter.setMemberIds(new String[]{"_810183_1"});
                GradebookWSStub.GetGrades grades = new GradebookWSStub.GetGrades();
                grades.setFilter(scoreFilter);

                grades.setCourseId(courseInternalId);

                GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
                getContextService().client_engage(gradebookWSStub._getServiceClient());
                GradebookWSStub.GetGradesResponse gradesResponse = gradebookWSStub.getGrades(grades);

                scoreVOs = gradesResponse.get_return();
            } catch (RemoteException e) {
                logger.error("There was a problem executing the getCourseMembership method : " + e.getMessage());
                e.printStackTrace();
                throw e;
            } finally {
                ctx.terminate();
            }
        }
        return scoreVOs;
    }

    /**
     * @param courseId - bb course identifier
     * @param username - bb username name
     * @return list of scores for given course per column after a given date
     * @throws RemoteException
     */
    public List<GradebookWSStub.ScoreVO> getCourseScoreByUserAfterDate(String courseId, String username, Date submissionDate) throws RemoteException {

        List<GradebookWSStub.ScoreVO> scores= new ArrayList<GradebookWSStub.ScoreVO>();
        long submissionDateLong = submissionDate.getTime();

        GradebookWSStub.ScoreVO[] scoreVOs = getCourseScoreByUsername(courseId, username);
        for (GradebookWSStub.ScoreVO scoreVO : scoreVOs) {
            if (scoreVO.getLastAttemptId() != null) {
                GradebookWSStub.AttemptVO lastAttempt = getAttempt(courseId, scoreVO.getLastAttemptId());
                if (lastAttempt != null) {
                    if (lastAttempt.getStatus().equalsIgnoreCase("Completed") ){//&& lastAttempt.getCreationDate()*1000 >= submissionDateLong) {
                        Date d = new Date(lastAttempt.getAttemptDate()*1000);
                        logger.info("date: "+d);
                        scores.add(scoreVO);
                    }
                }
            }
        }
        return scores;
    }

    private  GradebookWSStub.AttemptVO getAttempt(String courseId, String lastAttemptId) throws RemoteException {
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(getConnectorUtil().getModulePath());
        GradebookWSStub.AttemptVO[] attemptVOs = null;
        GradebookWSStub.AttemptVO attemptVO = null;

        String courseInternalId = null;
        CourseWSStub.CourseVO course = getCourseService().getCourseById(courseId);
        if (course != null) {
            courseInternalId = course.getId();

            try {
                GradebookWSStub.AttemptFilter attemptFilter = new GradebookWSStub.AttemptFilter();
                attemptFilter.setFilterType(3);  // 1 GET_ATTEMPT_BY_GRADE_ID
                attemptFilter.setIds(new String[]{lastAttemptId});
                GradebookWSStub.GetAttempts attempts = new GradebookWSStub.GetAttempts();
                attempts.setFilter(attemptFilter);
                attempts.setCourseId(courseInternalId);

                GradebookWSStub gradebookWSStub = new GradebookWSStub(ctx,
                        "http://" + getConnectorUtil().getBlackboardServerURL() + "/webapps/ws/services/Gradebook.WS");
                getContextService().client_engage(gradebookWSStub._getServiceClient());
                GradebookWSStub.GetAttemptsResponse gradesResponse = gradebookWSStub.getAttempts(attempts);

                attemptVOs = gradesResponse.get_return();
                if (attemptVOs != null){
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


    ContextServiceImpl getContextService() {
        return contextService;
    }

    public void setContextService(ContextServiceImpl contextService) {
        this.contextService = contextService;
    }

    CourseServiceImpl getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    ConnectorUtil getConnectorUtil() {
        return connectorUtil;
    }

    public void setConnectorUtil(ConnectorUtil connectorUtil) {
        this.connectorUtil = connectorUtil;
    }
}
