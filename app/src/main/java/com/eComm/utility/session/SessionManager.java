package com.eComm.utility.session;

import android.app.Application;

import com.eComm.app.AppController;
import com.eComm.utility.constant.AppConstants;
import com.eComm.utility.constant.PrefConstant;

public class SessionManager {

    private static SessionManager sInstance;
    private PreferenceUtil pref;


    private SessionManager(Application application) {
        pref = new PreferenceUtil(application);
    }

    public static void init(Application application) {
        if (sInstance == null) {
            sInstance = new SessionManager(application);
        }
    }

    public static SessionManager get() {
        init(AppController.getInstance());
        return sInstance;
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(PrefConstant.IS_LOGGED_IN);
    }

    public void setLoggedIn(boolean b) {
        pref.setBooleanData(PrefConstant.IS_LOGGED_IN, b);
    }

    public String getAccessToken() {
        return pref.getStringData(PrefConstant.ACCESS_TOKEN);
    }

    public void setAccessToken(String token) {
        pref.setStringData(PrefConstant.ACCESS_TOKEN, token);
    }

    /**
     * This method is to send userid in headre even if user is not marked as logged in
     * to overcome verifyMobile api issue.
     *
     * @return
     */
    public int getUserIdForAPiHeader() {
        return pref.getIntData(PrefConstant.USER_ID);
    }

    public void saveUserInfo(String accessToken) {
        pref.setBooleanData(PrefConstant.IS_LOGGED_IN, true);
        pref.setStringData(PrefConstant.ACCESS_TOKEN, accessToken);
    }

    public void clear() {
        pref.clear();
    }

    public String getFCMToken() {
        return pref.getStringData(PrefConstant.FCM_TOKEN);
    }

    public void setFCMToken(String token) {
        pref.setStringData(PrefConstant.FCM_TOKEN, token);
    }


    public void setSocialUser(boolean isSocialUser) {
        pref.setBooleanData(AppConstants.IS_SOCIAL_USER, isSocialUser);
    }

    public boolean getSocialUser() {
        return pref.getBoolean(AppConstants.IS_SOCIAL_USER);
    }

    public String getEmail() {
        return pref.getStringData(PrefConstant.EMAIL);
    }

    public void setEmail(String token) {
        pref.setStringData(PrefConstant.EMAIL, token);
    }

    public String getUserName() {
        return pref.getStringData(PrefConstant.USERNAME);
    }

    public void setUserName(String token) {
        pref.setStringData(PrefConstant.USERNAME, token);
    }

    public String getPhoneNo() {
        return pref.getStringData(PrefConstant.PHONE_NUMEBR);
    }

    public void setPhoneNo(String token) {
        pref.setStringData(PrefConstant.PHONE_NUMEBR, token);
    }

    public boolean isNotificationOnOff() {
        return pref.getBoolean(PrefConstant.IS_NOTIFICATION);
    }

    public void setNotificationOnOff(boolean b) {
        pref.setBooleanData(PrefConstant.IS_NOTIFICATION, b);
    }

    public boolean isLocationOnOff() {
        return pref.getBoolean(PrefConstant.IS_LOCATION);
    }

    public void setLocationOnOff(boolean b) {
        pref.setBooleanData(PrefConstant.IS_LOCATION, b);
    }

    public String getCurrencyCode() {
        return pref.getStringData(PrefConstant.CURRENCY_CODE);
    }

    public void setCurrencyCode(String token) {
        pref.setStringData(PrefConstant.CURRENCY_CODE, token);
    }

    public String getCurrencySymbol() {
        return pref.getStringData(PrefConstant.CURRENCY_SYMBOL);
    }

    public void setCurrencySymbol(String token) {
        pref.setStringData(PrefConstant.CURRENCY_SYMBOL, token);
    }

    public String getRateKm() {
        return pref.getStringData(PrefConstant.RATEKM);
    }

    public void setRateKm(String token) {
        pref.setStringData(PrefConstant.RATEKM, token);
    }

    public String getUnitType() {
        return pref.getStringData(PrefConstant.UNIT_TYPE);
    }

    public void setUnitType(String token) {
        pref.setStringData(PrefConstant.UNIT_TYPE, token);
    }

    public String getReportType() {
        return pref.getStringData(PrefConstant.REPORT_TYPE);
    }

    public void setReportType(String token) {
        pref.setStringData(PrefConstant.REPORT_TYPE, token);
    }

    public String getUserProfile() {
        return pref.getStringData(PrefConstant.USER_PROFILE);
    }

    public void setUserProfile(String token) {
        pref.setStringData(PrefConstant.USER_PROFILE, token);
    }


    public boolean isGPlusLoggin() {
        return pref.getBoolean(PrefConstant.IS_GPLUS_LOGGIN);
    }

    public void setGplusLoggin(boolean b) {
        pref.setBooleanData(PrefConstant.IS_GPLUS_LOGGIN, b);
    }

    public boolean isFirstTimeRidecCreate() {
        return pref.getBoolean(PrefConstant.IS_FIRSTTIME_RIDECREATED);
    }

    public void setFirstTimeRidecCreate(boolean b) {
        pref.setBooleanData(PrefConstant.IS_FIRSTTIME_RIDECREATED, b);
    }

    public String getVechicleType() {
        return pref.getStringData(PrefConstant.VECHICLE_TYPE);
    }

    public void setVechicleType(String vechicleType) {
        pref.setStringData(PrefConstant.VECHICLE_TYPE, vechicleType);
    }


    public String getVehicleDetails() {
        return pref.getStringData(PrefConstant.VehicleDetails);
    }

    public void setVehicleDetails(String VehicleDetails) {
        pref.setStringData(PrefConstant.VehicleDetails, VehicleDetails);
    }

    public String getDefaultVehicle() {
        return pref.getStringData(PrefConstant.DefaultVehicle);
    }

    public void setDefaultVehicle(String DefaultVehicle) {
        pref.setStringData(PrefConstant.DefaultVehicle, DefaultVehicle);
    }




    public String getRecentSearch() {
        return pref.getStringData(PrefConstant.RECENT_SEARCH);
    }

    public void setRecentSearch(String RecentSearch) {
        pref.setStringData(PrefConstant.RECENT_SEARCH, RecentSearch);
    }

    public String getMessagePattern() {
        return pref.getStringData(PrefConstant.MESSAGE_PATTERN);
    }

    public void setMessagePattern(String MessagePattern) {
        pref.setStringData(PrefConstant.MESSAGE_PATTERN, MessagePattern);
    }

}