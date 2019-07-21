package com.eComm.view.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eComm.R;
import com.eComm.databinding.FragmentLogInBinding;
import com.eComm.view.base.BaseFragment;
import com.eComm.view.ui.activity.HomeActivity;

public class LogInFragment extends BaseFragment implements View.OnClickListener {
    FragmentLogInBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.newToEcomSignup.setOnClickListener(this);
        mBinding.signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.new_to_ecom_signup:
                setSignUpFragment();
                break;
            case R.id.sign_in:
                setHomeScreen();
                break;
        }

    }

    private void setHomeScreen() {
        Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
        startActivity(homeIntent);
    }

    private void setSignUpFragment() {
        Fragment fragment = new SignUpFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_signup_frag, fragment).addToBackStack(null).commit();
    }
}
