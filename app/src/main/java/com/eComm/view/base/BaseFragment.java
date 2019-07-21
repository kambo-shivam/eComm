package com.eComm.view.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.eComm.R;
import com.eComm.utility.DialogUtil;
import com.eComm.utility.Utilities;

public class BaseFragment extends Fragment implements BaseListener, View.OnClickListener {

    public BaseActivity context;
    private ProgressDialog progressDialog;
    private ProgressBar mProgressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (BaseActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        context = (BaseActivity) getActivity();
    }

    @Override
    public void showHideProgressDialog(boolean isShow) {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                if (!isShow) {
                    progressDialog.dismiss();
                    progressDialog = null;
                } else
                    return;
            } else {
                if (isShow)
                    progressDialog.show();
            }
        } else {
            progressDialog = DialogUtil.getProgressDialog(getActivity());
            showHideProgressDialog(isShow);

        }
    }

    @Override
    public void showHideProgressBar(boolean iShow) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
            if (iShow)
                mProgressBar.setVisibility(View.VISIBLE);
            else {
                mProgressBar.setVisibility(View.GONE);
                mProgressBar = null;
            }
        } else {
            mProgressBar = DialogUtil.getProgressBarInstance(getView(), R.id.circular_progress_bar);
            if (mProgressBar == null) return;
            showHideProgressBar(iShow);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_image:
                onHeaderBackPressed();
                break;
        }
    }


    /**
     * This method is used to handle fragment back press
     */
    public void onHeaderBackPressed() {
        Utilities.hideKeyboard(getActivity());
        getActivity().onBackPressed();
    }


    protected void addFragment(Fragment fragment, int container, boolean isAddToBackStack) {
        String fragmentName = fragment.getClass().getSimpleName();
        if (isAddToBackStack) {
            getFragmentManager()
                    .beginTransaction()
                    .add(container, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commitAllowingStateLoss();
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, fragmentName)
                    .commitAllowingStateLoss();
        }
    }

    public void showHeaderBack() {
        ImageView backIv = getView().findViewById(R.id.toolbar_back_image);
        if (backIv != null) {
            backIv.setVisibility(View.VISIBLE);
            backIv.setOnClickListener(this);
        }
    }

    public int getProgressbarVisibility() {
        if (mProgressBar != null)
            return mProgressBar.getVisibility();
        return View.GONE;
    }

    public boolean isProgressDialogVisible() {
        if (progressDialog != null)
            return progressDialog.isShowing();
        return false;
    }


}
