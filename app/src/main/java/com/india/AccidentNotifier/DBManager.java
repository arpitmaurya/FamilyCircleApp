package com.india.AccidentNotifier;

public class DBManager {

  static String circleName ;
static  String circlePassword ;
static  String emailId ;
static  Boolean isHost;

    public static Boolean getIsHost() {
        return isHost;
    }

    public static void setIsHost(Boolean isHost) {
        DBManager.isHost = isHost;
    }

    DBManager(){

   }

    public static String getEmailId() {
        return emailId;
    }

    public static void setEmailId(String emailId) {
        DBManager.emailId = emailId;
    }

    public static String getCircleName() {
        return circleName;
    }

    public static void setCircleName(String circleName) {
        DBManager.circleName = circleName;
    }


    public static String getCirclePassword() {
        return circlePassword;
    }

    public static void setCirclePassword(String circlePassword) {

        DBManager.circlePassword = circlePassword;
    }


}
