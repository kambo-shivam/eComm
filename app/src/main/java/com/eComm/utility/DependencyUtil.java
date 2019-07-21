package com.eComm.utility;

import com.eComm.service.api.ApiService;
import com.eComm.service.api.AppRetrofit;
import com.eComm.utility.AppExecutors;
public class DependencyUtil {

    private static ApiService sApiServiceInstance;


    public static ApiService getAppService() {
        if (sApiServiceInstance == null) {
            sApiServiceInstance = AppRetrofit.getInstance().getApiService();
        }
        return sApiServiceInstance;
    }

    public static AppExecutors getAppExecuter() {
        return new AppExecutors();
    }
}
