package com.eComm.view.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eComm.R;
import com.eComm.databinding.FragmentOtpScreenBinding;
import com.eComm.databinding.ToolbarSignupBinding;
import com.eComm.view.base.BaseFragment;

public class OtpScreenFragment extends BaseFragment implements View.OnClickListener {
    FragmentOtpScreenBinding mBinding;
    TextView headerCloseButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_screen, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headerCloseButton = view.findViewById(R.id.header_close_bttn);
        headerCloseButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.header_close_bttn: {
                getActivity().onBackPressed();
                break;
            }
        }
    }
}
