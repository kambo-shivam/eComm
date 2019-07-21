package com.eComm.view.ui.home;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.load.engine.Resource;
import com.eComm.R;
import com.eComm.databinding.ActivityMainBinding;
import com.eComm.service.api.vo.Status;
import com.eComm.service.api.vo.common.GeneralResponse;
import com.eComm.service.api.vo.common.SimpleListResponse;
import com.eComm.view.base.BaseActivity;
import com.eComm.view.ui.common.vo.GetCategoryResponse;
import com.eComm.view.ui.home.vo.GetCategoryRequestBean;
import com.eComm.viewmodel.HomeViewModel;

public class MainActivity extends BaseActivity {

    private HomeViewModel homeViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();

    }

    private void init() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        activityMainBinding.apiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGetCateogryApi();
            }
        });
    }

    //method to call the api to get the categories list
    private void callGetCateogryApi() {
        homeViewModel.setContactUsRequest(new GetCategoryRequestBean());
        if (!homeViewModel.getCategoryReponse().hasActiveObservers()) {
            homeViewModel.getCategoryReponse().observe(this, new Observer<Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>>>() {
                @Override
                public void onChanged(@Nullable Resource<GeneralResponse<SimpleListResponse<GetCategoryResponse>>> generalResponseResource) {
                    showHideProgressBar(false);
                }
            });
        }
    }
}
