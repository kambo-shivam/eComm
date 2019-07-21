package com.eComm.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.bumptech.glide.load.engine.Resource;
import com.eComm.service.api.ApiService;
import com.eComm.service.api.vo.common.GeneralResponse;
import com.eComm.service.api.vo.common.SimpleListResponse;
import com.eComm.utility.DependencyUtil;
import com.eComm.view.ui.common.vo.GetCategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private final ApiService apiService;

    public HomeRepository() {
        this.apiService = DependencyUtil.getAppService();
    }

    public LiveData<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> getCategory() {
        final MutableLiveData<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> data = new MutableLiveData<>();
        apiService.getCategories().enqueue(new Callback<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>>() {
            @Override
            public void onResponse(Call<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> call, Response<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> response) {
                simulateDelay();
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
