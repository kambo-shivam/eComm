package com.eComm.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eComm.R;
import com.eComm.databinding.ActivitySignUpLogInBinding;
import com.eComm.view.base.BaseActivity;
import com.eComm.view.ui.fragments.LogInFragment;

public class SignUpLogInActivity extends BaseActivity {
    ActivitySignUpLogInBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this
                , R.layout.activity_sign_up_log_in);
        setLogInFragment();
    }

    private void setLogInFragment() {
        Fragment fragment = new LogInFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_signup_frag, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
