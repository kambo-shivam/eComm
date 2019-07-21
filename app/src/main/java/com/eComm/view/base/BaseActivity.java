package com.eComm.view.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eComm.R;
import com.eComm.utility.DialogUtil;
import com.eComm.utility.Utilities;

public class BaseActivity extends AppCompatActivity implements BaseListener, View.OnClickListener {

    protected BaseActivity mThis;
    private ProgressDialog progressDialog;
    private ProgressBar mProgressBar;
    private int backpress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mThis = this;
    }

    public void setBackEnabled(boolean isBackEnabled) {
        ImageView backArrow = findViewById(R.id.toolbar_back_image);
        if (backArrow != null) {
            if (isBackEnabled) {
                backArrow.setVisibility(View.VISIBLE);
                backArrow.setOnClickListener(this);
            } else {
                backArrow.setVisibility(View.GONE);
            }
        }
    }

    public void setBackEnabled_Title(boolean isBackEnabled,String Title) {
        ImageView backArrow = findViewById(R.id.toolbar_back_image);
        TextView title = findViewById(R.id.Title);
        if (backArrow != null) {
            if (isBackEnabled) {
                backArrow.setVisibility(View.VISIBLE);
                backArrow.setOnClickListener(this);
            } else {
                backArrow.setVisibility(View.GONE);
            }
            title.setText(Title);
        }
    }

    public void setBackCross() {
        ImageView backArrow = findViewById(R.id.toolbar_back_image);
        if (backArrow != null) {
            backArrow.setImageResource(R.mipmap.ic_back);
        }
    }


    @Override
    public void showHideProgressDialog(boolean isShow) {
        if (progressDialog != null) {
            if (isShow)
                progressDialog.show();
            else {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        } else {
            progressDialog = DialogUtil.getProgressDialog(this);
            showHideProgressDialog(isShow);

        }
    }


    @Override
    public synchronized void showHideProgressBar(boolean iShow) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
            if (iShow)
                mProgressBar.setVisibility(View.VISIBLE);
            else {
                mProgressBar.setVisibility(View.GONE);
                mProgressBar = null;
            }
        } else {
            mProgressBar = DialogUtil.getProgressBarInstance(this, R.id.circular_progress_bar);
            if (mProgressBar == null) return;
            showHideProgressBar(iShow);
        }
    }

    @Override
    public void onBackPressed() {
      //  if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        //    return;
     //   }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * This method invokes when user press back button of header or device as well.
     */
    public void onHeaderBackPress() {
        Utilities.hideKeyboard(this);
        super.onBackPressed();
    }

    protected void setFragment(Fragment fragment, int container, boolean isAddToBackStack) {
        String fragmentName = fragment.getClass().getSimpleName();
        if (isAddToBackStack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, fragmentName)
                    .commitAllowingStateLoss();
        }
    }

    /**
     * This method set icon on header right most icon
     *
     * @param drawable icon that needs to set
     */
    public void setHeaderRightMostIcon(int drawable) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    /**
     * need to override this method in child class to get the click of header right most icon
     */
    public void onHeaderRightMostIconClicked() {
    }

    public void setHeaderRightMostIconVisibility(boolean isVisible) {
    }

    public int getProgressbarVisibility() {
        if (mProgressBar != null)
            return mProgressBar.getVisibility();
        return View.GONE;
    }





    public BaseActivity getBaseActivity() {
        return mThis;
    }

}