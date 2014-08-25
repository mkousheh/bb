
/**
 * UserWSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package edu.bu.ist.bbws._generated.user;

    /**
     *  UserWSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class UserWSCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public UserWSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public UserWSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for initializeUserWS method
            * override this method for handling normal response from initializeUserWS operation
            */
           public void receiveResultinitializeUserWS(
                    edu.bu.ist.bbws._generated.user.UserWSStub.InitializeUserWSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from initializeUserWS operation
           */
            public void receiveErrorinitializeUserWS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeUserDataSourceId method
            * override this method for handling normal response from changeUserDataSourceId operation
            */
           public void receiveResultchangeUserDataSourceId(
                    edu.bu.ist.bbws._generated.user.UserWSStub.ChangeUserDataSourceIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeUserDataSourceId operation
           */
            public void receiveErrorchangeUserDataSourceId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for saveUser method
            * override this method for handling normal response from saveUser operation
            */
           public void receiveResultsaveUser(
                    edu.bu.ist.bbws._generated.user.UserWSStub.SaveUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveUser operation
           */
            public void receiveErrorsaveUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSystemRoles method
            * override this method for handling normal response from getSystemRoles operation
            */
           public void receiveResultgetSystemRoles(
                    edu.bu.ist.bbws._generated.user.UserWSStub.GetSystemRolesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSystemRoles operation
           */
            public void receiveErrorgetSystemRoles(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteAddressBookEntry method
            * override this method for handling normal response from deleteAddressBookEntry operation
            */
           public void receiveResultdeleteAddressBookEntry(
                    edu.bu.ist.bbws._generated.user.UserWSStub.DeleteAddressBookEntryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteAddressBookEntry operation
           */
            public void receiveErrordeleteAddressBookEntry(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeUserBatchUid method
            * override this method for handling normal response from changeUserBatchUid operation
            */
           public void receiveResultchangeUserBatchUid(
                    edu.bu.ist.bbws._generated.user.UserWSStub.ChangeUserBatchUidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeUserBatchUid operation
           */
            public void receiveErrorchangeUserBatchUid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserInstitutionRoles method
            * override this method for handling normal response from getUserInstitutionRoles operation
            */
           public void receiveResultgetUserInstitutionRoles(
                    edu.bu.ist.bbws._generated.user.UserWSStub.GetUserInstitutionRolesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserInstitutionRoles operation
           */
            public void receiveErrorgetUserInstitutionRoles(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAddressBookEntry method
            * override this method for handling normal response from getAddressBookEntry operation
            */
           public void receiveResultgetAddressBookEntry(
                    edu.bu.ist.bbws._generated.user.UserWSStub.GetAddressBookEntryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAddressBookEntry operation
           */
            public void receiveErrorgetAddressBookEntry(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getServerVersion method
            * override this method for handling normal response from getServerVersion operation
            */
           public void receiveResultgetServerVersion(
                    edu.bu.ist.bbws._generated.user.UserWSStub.GetServerVersionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServerVersion operation
           */
            public void receiveErrorgetServerVersion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteUser method
            * override this method for handling normal response from deleteUser operation
            */
           public void receiveResultdeleteUser(
                    edu.bu.ist.bbws._generated.user.UserWSStub.DeleteUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteUser operation
           */
            public void receiveErrordeleteUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getObservee method
            * override this method for handling normal response from getObservee operation
            */
           public void receiveResultgetObservee(
                    edu.bu.ist.bbws._generated.user.UserWSStub.GetObserveeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getObservee operation
           */
            public void receiveErrorgetObservee(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteUserByInstitutionRole method
            * override this method for handling normal response from deleteUserByInstitutionRole operation
            */
           public void receiveResultdeleteUserByInstitutionRole(
                    edu.bu.ist.bbws._generated.user.UserWSStub.DeleteUserByInstitutionRoleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteUserByInstitutionRole operation
           */
            public void receiveErrordeleteUserByInstitutionRole(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for saveAddressBookEntry method
            * override this method for handling normal response from saveAddressBookEntry operation
            */
           public void receiveResultsaveAddressBookEntry(
                    edu.bu.ist.bbws._generated.user.UserWSStub.SaveAddressBookEntryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveAddressBookEntry operation
           */
            public void receiveErrorsaveAddressBookEntry(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteObserverAssociation method
            * override this method for handling normal response from deleteObserverAssociation operation
            */
           public void receiveResultdeleteObserverAssociation(
                    edu.bu.ist.bbws._generated.user.UserWSStub.DeleteObserverAssociationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteObserverAssociation operation
           */
            public void receiveErrordeleteObserverAssociation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for saveObserverAssociation method
            * override this method for handling normal response from saveObserverAssociation operation
            */
           public void receiveResultsaveObserverAssociation(
                    edu.bu.ist.bbws._generated.user.UserWSStub.SaveObserverAssociationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveObserverAssociation operation
           */
            public void receiveErrorsaveObserverAssociation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getInstitutionRoles method
            * override this method for handling normal response from getInstitutionRoles operation
            */
           public void receiveResultgetInstitutionRoles(
                    edu.bu.ist.bbws._generated.user.UserWSStub.GetInstitutionRolesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getInstitutionRoles operation
           */
            public void receiveErrorgetInstitutionRoles(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUser method
            * override this method for handling normal response from getUser operation
            */
           public void receiveResultgetUser(
                    edu.bu.ist.bbws._generated.user.UserWSStub.GetUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUser operation
           */
            public void receiveErrorgetUser(java.lang.Exception e) {
            }
                


    }
    