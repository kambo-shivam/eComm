package com.eComm.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.os.Build;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.Resource;
import com.eComm.service.api.vo.common.GeneralResponse;
import com.eComm.service.api.vo.common.SimpleListResponse;
import com.eComm.service.repository.HomeRepository;
import com.eComm.utility.AbsentLiveData;
import com.eComm.view.base.BaseViewModel;
import com.eComm.view.ui.common.vo.GetCategoryResponse;
import com.eComm.view.ui.home.vo.GetCategoryRequestBean;
import java.util.Objects;

public class HomeViewModel extends BaseViewModel {


    final MutableLiveData<GetCategoryRequestBean> getCategoryRequest = new MutableLiveData<>();
    private final HomeRepository homeRepository;
    private LiveData<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> categoryResponse;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository = new HomeRepository();

        categoryResponse = Transformations.switchMap(getCategoryRequest, new Function<GetCategoryRequestBean, LiveData<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>>>() {
            @Override
            public LiveData<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> apply(GetCategoryRequestBean usRequest) {
                if (getCategoryRequest == null) {
                    return AbsentLiveData.create();
                } else
                    return homeRepository.getCategory();
            }
        });

    }

    public void setContactUsRequest(GetCategoryRequestBean request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(this.categoryResponse.getValue(), request)) {
                return;
            }
        }
        this.getCategoryRequest.setValue(request);
    }


    public LiveData<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>> getCategoryReponse() {
        return categoryResponse;
    }

}
