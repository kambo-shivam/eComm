package com.eComm.service.api;

import com.bumptech.glide.load.engine.Resource;
import com.eComm.service.api.vo.common.GeneralResponse;
import com.eComm.service.api.vo.common.SimpleListResponse;
import com.eComm.view.ui.common.vo.GetCategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * All API services, with their Url, Response type, Request type and Request method(eg. GET, POST)
 */
public interface ApiService {

    //method to get the school type list
    @GET(ApiConstant.GET_CATEGORIES)
    Call<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> getCategories();


}

