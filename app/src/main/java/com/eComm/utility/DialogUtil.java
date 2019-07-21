package com.eComm.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eComm.R;
import com.eComm.view.callback.PopupCallbackListener;

public class DialogUtil {


    /**
     * get a blocking progress dialog.
     *
     * @param context context of current activity/fragment
     * @return create of {@link ProgressDialog}
     */
    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.MyTheme);
//        progressDialog.setMessage(context.getResources().getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    /**
     * Static method to get an create of material styled progress bar
     *
     * @param context Context of the current class
     * @param resId   Resource Id of the progress bar
     * @return An create of MaterialProgressBar
     */
    public static ProgressBar getProgressBarInstance(Context context, int resId) {
        ProgressBar nonBlockingProgressBar = ((Activity) context).getWindow().findViewById(resId);
        return nonBlockingProgressBar;
    }

    public static ProgressBar getProgressBarInstance(View view, int resId) {
        if (view != null) {
            ProgressBar nonBlockingProgressBar = view.findViewById(resId);
            return nonBlockingProgressBar;
        }
        return null;
    }

    public static Snackbar showSnackBar(View anyView, int msg) {
        Resources res = anyView.getContext().getResources();
        return showSnackBar(anyView, res.getString(msg));
    }

    public static void showToast(@NonNull Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@NonNull Context context, int msg) {
        Toast.makeText(context.getApplicationContext(), context.getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

    public static Snackbar showNoNetworkSnackBar(@NonNull View anyView) {
        return showSnackBar(anyView, R.string.no_internet_error_msg);
    }

    public static Snackbar showSnackBar(View anyView, String msg) {
        final Snackbar snackBar = Snackbar.make(anyView, msg, Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(Color.WHITE);
        if (anyView instanceof CoordinatorLayout) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                    snackBar.getView().getLayoutParams();
            params.setMargins(0, 0, 0, 130);
            snackBar.getView().setLayoutParams(params);
        }
        View view = snackBar.getView();
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        tv.setMaxLines(5);
        snackBar.show();
        return snackBar;
    }

    public static void showNoNetworkToast(Context context) {
        showToast(context, context.getString(R.string.no_internet_error_msg));
    }

    public static void showActionNoNetworkSnackBar(View rootView, String actionText, View.OnClickListener actionListener) {
       showActionSnackBar(rootView, rootView.getContext().getString(R.string.no_internet_error_msg), actionText, actionListener);
    }

    public static Snackbar showActionSnackBar(View parentView, String msg, String actionText, final View.OnClickListener actionListener) {
        try {
            final Snackbar snackBar = Snackbar.make(parentView, msg, Snackbar.LENGTH_INDEFINITE);
            snackBar.setActionTextColor(Color.WHITE);
            View view = snackBar.getView();
            TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            tv.setMaxLines(5);
            snackBar.setAction(actionText, v -> {
                snackBar.dismiss();
                actionListener.onClick(v);
            });
            snackBar.show();
            return snackBar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * Static method to show alert dialog with Ok cancel buttons
     *
     * @param context      Context of the calling class
     * @param text         Text to show in toast
     * @param positiveText Text to show on OKAY button
     * @param negativeText Text to show on CANCEL button
     */


    public static void showAlertDialogWithCallbackAndText(Context context, String text,
                                                          final PopupCallbackListener dialogCallBack, String positiveText, String negativeText, boolean isCancelable) {

        if (text == null)
            text = "";
        AlertDialog mAlertDialog = new AlertDialog.Builder(context).setMessage(text).setCancelable(isCancelable)
                .setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (dialogCallBack != null)
                            dialogCallBack.onPopupNegativeButtonClicked();
                    }
                })
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (dialogCallBack != null)
                            dialogCallBack.onPopupPositiveButtonClicked();

                    }
                }).create();
//        mAlertDialog.setTitle(context.getString(R.string.app_name));
        mAlertDialog.show();
        Button nbutton = mAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTransformationMethod(null);
        nbutton.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        Button pbutton = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTransformationMethod(null);
        pbutton.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }


    /**
     * Static method to show alert dialog with single button
     *
     * @param mContext Context of the calling class
     * @param text     Text to show in toast
     */
    public static AlertDialog showAlertDialog(Context mContext, String text, String buttonText, DialogInterface.OnClickListener clickListener, boolean isCancelable) {
        if (text == null)
            text = "";
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext).setMessage(text)
                /*.setTitle(mContext.getString(R.string.app_name))*/.setCancelable(isCancelable)
                .setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        clickListener.onClick(dialog, which);
                    }
                }).create();

        mAlertDialog.show();
        Button button = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        return mAlertDialog;
    }

    public static String SelectedTab = "";


    public static void showLoader(ProgressWheel progressWheel) {
        if (progressWheel != null) {

            if (!progressWheel.isSpinning()) progressWheel.spin();
            if (progressWheel.getVisibility() == View.GONE || progressWheel.getVisibility() == View.INVISIBLE) {
                progressWheel.setVisibility(View.VISIBLE);
            }
        } else {
            Lg.e("PROGRESS BAR", "progressbar is not attached");
        }
    }

    public static void hideLoader(ProgressWheel progressWheel) {
        try {
            if (progressWheel != null) {

                if (progressWheel.isSpinning()) progressWheel.stopSpinning();
                if (progressWheel.getVisibility() == View.VISIBLE) {
                    progressWheel.setVisibility(View.GONE);
                }
            } else {
                Lg.e("PROGRESS BAR", "progressbar is not attached");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
