
/**
 * CourseWSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package edu.bu.ist.bbws._generated.course;

    /**
     *  CourseWSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CourseWSCallbackHandler {



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CourseWSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CourseWSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for loadTermByCourseId method
            * override this method for handling normal response from loadTermByCourseId operation
            */
           public void receiveResultloadTermByCourseId(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.LoadTermByCourseIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loadTermByCourseId operation
           */
            public void receiveErrorloadTermByCourseId(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteOrgCategory method
            * override this method for handling normal response from deleteOrgCategory operation
            */
           public void receiveResultdeleteOrgCategory(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteOrgCategoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteOrgCategory operation
           */
            public void receiveErrordeleteOrgCategory(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteOrgCategoryMembership method
            * override this method for handling normal response from deleteOrgCategoryMembership operation
            */
           public void receiveResultdeleteOrgCategoryMembership(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteOrgCategoryMembershipResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteOrgCategoryMembership operation
           */
            public void receiveErrordeleteOrgCategoryMembership(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteTerm method
            * override this method for handling normal response from deleteTerm operation
            */
           public void receiveResultdeleteTerm(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteTermResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteTerm operation
           */
            public void receiveErrordeleteTerm(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for addCourseToTerm method
            * override this method for handling normal response from addCourseToTerm operation
            */
           public void receiveResultaddCourseToTerm(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.AddCourseToTermResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCourseToTerm operation
           */
            public void receiveErroraddCourseToTerm(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveCourseCategoryMembership method
            * override this method for handling normal response from saveCourseCategoryMembership operation
            */
           public void receiveResultsaveCourseCategoryMembership(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveCourseCategoryMembershipResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveCourseCategoryMembership operation
           */
            public void receiveErrorsaveCourseCategoryMembership(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for initializeCourseWS method
            * override this method for handling normal response from initializeCourseWS operation
            */
           public void receiveResultinitializeCourseWS(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.InitializeCourseWSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from initializeCourseWS operation
           */
            public void receiveErrorinitializeCourseWS(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveCourseCategory method
            * override this method for handling normal response from saveCourseCategory operation
            */
           public void receiveResultsaveCourseCategory(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveCourseCategoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveCourseCategory operation
           */
            public void receiveErrorsaveCourseCategory(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for changeCourseBatchUid method
            * override this method for handling normal response from changeCourseBatchUid operation
            */
           public void receiveResultchangeCourseBatchUid(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.ChangeCourseBatchUidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeCourseBatchUid operation
           */
            public void receiveErrorchangeCourseBatchUid(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getCourseCategoryMembership method
            * override this method for handling normal response from getCourseCategoryMembership operation
            */
           public void receiveResultgetCourseCategoryMembership(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetCourseCategoryMembershipResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCourseCategoryMembership operation
           */
            public void receiveErrorgetCourseCategoryMembership(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveTerm method
            * override this method for handling normal response from saveTerm operation
            */
           public void receiveResultsaveTerm(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveTermResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveTerm operation
           */
            public void receiveErrorsaveTerm(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveOrgCategory method
            * override this method for handling normal response from saveOrgCategory operation
            */
           public void receiveResultsaveOrgCategory(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveOrgCategoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveOrgCategory operation
           */
            public void receiveErrorsaveOrgCategory(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteOrg method
            * override this method for handling normal response from deleteOrg operation
            */
           public void receiveResultdeleteOrg(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteOrgResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteOrg operation
           */
            public void receiveErrordeleteOrg(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for loadTermsByName method
            * override this method for handling normal response from loadTermsByName operation
            */
           public void receiveResultloadTermsByName(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.LoadTermsByNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loadTermsByName operation
           */
            public void receiveErrorloadTermsByName(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getCartridge method
            * override this method for handling normal response from getCartridge operation
            */
           public void receiveResultgetCartridge(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetCartridgeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCartridge operation
           */
            public void receiveErrorgetCartridge(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveGroup method
            * override this method for handling normal response from saveGroup operation
            */
           public void receiveResultsaveGroup(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveGroup operation
           */
            public void receiveErrorsaveGroup(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for loadTerm method
            * override this method for handling normal response from loadTerm operation
            */
           public void receiveResultloadTerm(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.LoadTermResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loadTerm operation
           */
            public void receiveErrorloadTerm(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getServerVersion method
            * override this method for handling normal response from getServerVersion operation
            */
           public void receiveResultgetServerVersion(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetServerVersionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServerVersion operation
           */
            public void receiveErrorgetServerVersion(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getStaffInfo method
            * override this method for handling normal response from getStaffInfo operation
            */
           public void receiveResultgetStaffInfo(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetStaffInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getStaffInfo operation
           */
            public void receiveErrorgetStaffInfo(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for changeCourseDataSourceId method
            * override this method for handling normal response from changeCourseDataSourceId operation
            */
           public void receiveResultchangeCourseDataSourceId(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.ChangeCourseDataSourceIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeCourseDataSourceId operation
           */
            public void receiveErrorchangeCourseDataSourceId(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for createOrg method
            * override this method for handling normal response from createOrg operation
            */
           public void receiveResultcreateOrg(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.CreateOrgResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createOrg operation
           */
            public void receiveErrorcreateOrg(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for setCourseBannerImage method
            * override this method for handling normal response from setCourseBannerImage operation
            */
           public void receiveResultsetCourseBannerImage(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SetCourseBannerImageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setCourseBannerImage operation
           */
            public void receiveErrorsetCourseBannerImage(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for loadCoursesInTerm method
            * override this method for handling normal response from loadCoursesInTerm operation
            */
           public void receiveResultloadCoursesInTerm(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.LoadCoursesInTermResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loadCoursesInTerm operation
           */
            public void receiveErrorloadCoursesInTerm(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for updateOrg method
            * override this method for handling normal response from updateOrg operation
            */
           public void receiveResultupdateOrg(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.UpdateOrgResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateOrg operation
           */
            public void receiveErrorupdateOrg(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for changeOrgCategoryBatchUid method
            * override this method for handling normal response from changeOrgCategoryBatchUid operation
            */
           public void receiveResultchangeOrgCategoryBatchUid(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.ChangeOrgCategoryBatchUidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeOrgCategoryBatchUid operation
           */
            public void receiveErrorchangeOrgCategoryBatchUid(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for loadTerms method
            * override this method for handling normal response from loadTerms operation
            */
           public void receiveResultloadTerms(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.LoadTermsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loadTerms operation
           */
            public void receiveErrorloadTerms(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveStaffInfo method
            * override this method for handling normal response from saveStaffInfo operation
            */
           public void receiveResultsaveStaffInfo(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveStaffInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveStaffInfo operation
           */
            public void receiveErrorsaveStaffInfo(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteCourse method
            * override this method for handling normal response from deleteCourse operation
            */
           public void receiveResultdeleteCourse(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteCourseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteCourse operation
           */
            public void receiveErrordeleteCourse(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteCourseCategoryMembership method
            * override this method for handling normal response from deleteCourseCategoryMembership operation
            */
           public void receiveResultdeleteCourseCategoryMembership(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteCourseCategoryMembershipResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteCourseCategoryMembership operation
           */
            public void receiveErrordeleteCourseCategoryMembership(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteCartridge method
            * override this method for handling normal response from deleteCartridge operation
            */
           public void receiveResultdeleteCartridge(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteCartridgeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteCartridge operation
           */
            public void receiveErrordeleteCartridge(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for updateCourse method
            * override this method for handling normal response from updateCourse operation
            */
           public void receiveResultupdateCourse(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.UpdateCourseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateCourse operation
           */
            public void receiveErrorupdateCourse(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveOrgCategoryMembership method
            * override this method for handling normal response from saveOrgCategoryMembership operation
            */
           public void receiveResultsaveOrgCategoryMembership(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveOrgCategoryMembershipResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveOrgCategoryMembership operation
           */
            public void receiveErrorsaveOrgCategoryMembership(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getClassifications method
            * override this method for handling normal response from getClassifications operation
            */
           public void receiveResultgetClassifications(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetClassificationsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getClassifications operation
           */
            public void receiveErrorgetClassifications(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveCartridge method
            * override this method for handling normal response from saveCartridge operation
            */
           public void receiveResultsaveCartridge(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveCartridgeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveCartridge operation
           */
            public void receiveErrorsaveCartridge(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteGroup method
            * override this method for handling normal response from deleteGroup operation
            */
           public void receiveResultdeleteGroup(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteGroup operation
           */
            public void receiveErrordeleteGroup(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getOrgCategoryMembership method
            * override this method for handling normal response from getOrgCategoryMembership operation
            */
           public void receiveResultgetOrgCategoryMembership(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetOrgCategoryMembershipResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrgCategoryMembership operation
           */
            public void receiveErrorgetOrgCategoryMembership(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getAvailableGroupTools method
            * override this method for handling normal response from getAvailableGroupTools operation
            */
           public void receiveResultgetAvailableGroupTools(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetAvailableGroupToolsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailableGroupTools operation
           */
            public void receiveErrorgetAvailableGroupTools(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for createCourse method
            * override this method for handling normal response from createCourse operation
            */
           public void receiveResultcreateCourse(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.CreateCourseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createCourse operation
           */
            public void receiveErrorcreateCourse(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for changeCourseCategoryBatchUid method
            * override this method for handling normal response from changeCourseCategoryBatchUid operation
            */
           public void receiveResultchangeCourseCategoryBatchUid(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.ChangeCourseCategoryBatchUidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeCourseCategoryBatchUid operation
           */
            public void receiveErrorchangeCourseCategoryBatchUid(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteStaffInfo method
            * override this method for handling normal response from deleteStaffInfo operation
            */
           public void receiveResultdeleteStaffInfo(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteStaffInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteStaffInfo operation
           */
            public void receiveErrordeleteStaffInfo(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for saveCourse method
            * override this method for handling normal response from saveCourse operation
            */
           public void receiveResultsaveCourse(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.SaveCourseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveCourse operation
           */
            public void receiveErrorsaveCourse(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getCourse method
            * override this method for handling normal response from getCourse operation
            */
           public void receiveResultgetCourse(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetCourseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCourse operation
           */
            public void receiveErrorgetCourse(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getCategories method
            * override this method for handling normal response from getCategories operation
            */
           public void receiveResultgetCategories(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetCategoriesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCategories operation
           */
            public void receiveErrorgetCategories(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for changeOrgDataSourceId method
            * override this method for handling normal response from changeOrgDataSourceId operation
            */
           public void receiveResultchangeOrgDataSourceId(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.ChangeOrgDataSourceIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeOrgDataSourceId operation
           */
            public void receiveErrorchangeOrgDataSourceId(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for removeCourseFromTerm method
            * override this method for handling normal response from removeCourseFromTerm operation
            */
           public void receiveResultremoveCourseFromTerm(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.RemoveCourseFromTermResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCourseFromTerm operation
           */
            public void receiveErrorremoveCourseFromTerm(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for changeOrgBatchUid method
            * override this method for handling normal response from changeOrgBatchUid operation
            */
           public void receiveResultchangeOrgBatchUid(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.ChangeOrgBatchUidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeOrgBatchUid operation
           */
            public void receiveErrorchangeOrgBatchUid(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getGroup method
            * override this method for handling normal response from getGroup operation
            */
           public void receiveResultgetGroup(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getGroup operation
           */
            public void receiveErrorgetGroup(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteCourseCategory method
            * override this method for handling normal response from deleteCourseCategory operation
            */
           public void receiveResultdeleteCourseCategory(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.DeleteCourseCategoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteCourseCategory operation
           */
            public void receiveErrordeleteCourseCategory(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getOrg method
            * override this method for handling normal response from getOrg operation
            */
           public void receiveResultgetOrg(
                    edu.bu.ist.bbws._generated.course.CourseWSStub.GetOrgResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrg operation
           */
            public void receiveErrorgetOrg(Exception e) {
            }
                


    }
    