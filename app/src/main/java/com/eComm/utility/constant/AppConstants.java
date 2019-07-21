package com.eComm.utility.constant;

import android.net.Uri;


public class AppConstants {
    public static final String APP_NAME = "Atigo";
    public static final String RESOURCE_ERROR_TYPE = "type must be a resource";
    public static final String RESOURCE_ERROR_PARAMETER = "resource must be parameterized";
    public static final int ZERO_STATUS_CODE = 0;
    public static final int MIN_PSWD_LENGTH = 6;
    public static final String PHONE_NUMBER = "phone_number";
    public static final String IS_REGISTER = "isRegister";
    public static final String USER_NAME = "userName";
    public static final String USER_EMAIL = "userEmail";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String SIGN_IN_TYPE = "signInType";
    public static final String GOOGLE_SIGNIN = "1";
    public static final String NORMAL_SIGNIN = "2";
    public static final String IS_SOCIAL_USER = "isSocialUser";
    public static final int IsOlaSelected = 0;
    public static final int IsUberSelected = 1;
    public static Uri mCamRequestedUri;
    public static final int MAX_PROFILE_CROP_WIDTH = 1000;
    public static final int MAX_PROFILE_CROP_HEIGHT = 1000;
    public static final int MIN_PROFILE_CROP_WIDTH = 500;
    public static final int MIN_PROFILE_CROP_HEIGHT = 500;
    public static final int SOCIAL_MEDIUM_GOOGLE = 2;
    public static final String PRODUCT_DEEP_LINK = "productDeepLink";
    public static final String SELLER_DEEP_LINK = "sellerDeepLink";
    public static final String FORGET_DEEP_LINK = "deeplinkForget";
    public static final String USER_TYPE = "userType"; /*Either Seller or Buyer*/
    public static final String KEY_PUSH_TYPE = "keyPushType";
    public static final String IS_UPDATE_PHONE = "is_update_phone";
    public static final String IsfirstTime = "IsfirstTime";
    public static final String SOURCE_ADDRESS = "SourceAddress";
    public static final String DESTINATION_ADDRESS = "SourceAddress";
    public static final String SOURCE_LAT = "SourceLat";
    public static final String SOURCE_LONGITUDE = "SourceLongitude";
    public static final String DESTINATION_LATITUDE = "SourceLatitude";
    public static final String DESTINATION_LONGITUDE = "SourceLongitude";
    public static final String RideId = "RideId";
    public static final String CREATERIDE_REQUEST = "CreateRideRequest";
    public static final String EstimatedPriceRide="Pool";

    public static final String SEARCHED_PREDICTION="Prediction";
    public static final String SEARCHED_LAT="Searched_Lattitude";
    public static final String SEARCHED_LNG="Searched_Longitude";
    public static final String OlaTokenForApiHeader = "X-APP-TOKEN";
    public static final String OlaAuthForApiHeader = "Bearer 631404119bfc4097b6aac95ce160933c";


    public static final String DATE_FORMAT_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String SERVER = "yyyy-MM-dd 'HH:mm:ss";
    public static final String APP_DATE_FORMAT = "yyyy-MM-dd | hh:mm aa";  //yyyy-MM-dd HH:mm
    public static final String SERVER_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'";
    public static final double MAX_LIMIT_DISTANCE = 20;
    public static final int MAX_RECENT_SEARCH_COUNT = 5;
    public static final int GEO_FENCING_AREA= 40;
}
