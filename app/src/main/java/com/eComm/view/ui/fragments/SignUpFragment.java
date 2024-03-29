package com.eComm.view.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eComm.R;
import com.eComm.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment implements View.OnClickListener {
    FragmentSignUpBinding mBinding;
    TextView headerCloseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headerCloseButton = view.findViewById(R.id.header_close_bttn);
        headerCloseButton.setOnClickListener(this);
        mBinding.alreadyExist.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.already_exist:
                getActivity().onBackPressed();
                break;
            case R.id.header_close_bttn:
                getActivity().onBackPressed();
                break;
        }
    }
}
