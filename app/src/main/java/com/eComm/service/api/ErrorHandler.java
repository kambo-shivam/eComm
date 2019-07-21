package com.eComm.service.api;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.eComm.R;
import com.eComm.service.api.vo.common.ErrorResponse;
import com.eComm.service.api.vo.common.SimpleListResponse;
import com.eComm.service.api.vo.common.SimpleResponse;
import com.eComm.utility.DialogUtil;
import com.google.gson.Gson;

public class ErrorHandler {

    public static Snackbar showErrorMsgWithAction(View view, String msg, String actionText, View.OnClickListener listener) {
        return DialogUtil.showActionSnackBar(view, msg, actionText, listener);
    }

    public static void showErrorMsg(View view, String msg) {
        ErrorResponse errorResponse = null;
        if (msg != null && msg.contains("{")) {
            try {
                errorResponse = new Gson().fromJson(msg, ErrorResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (errorResponse != null)
            msg = errorResponse.getMessage();
        else if (msg == null || msg.isEmpty())
            msg = view.getContext().getString(R.string.something_went_wrong);
        DialogUtil.showSnackBar(view, msg);
    }

    public static void showResponseErrorMsg(View view, SimpleResponse responseBean) {
        String msg = "";
        if (responseBean.getErrorCode() == ApiConstant.VALIDATION_ERROR_CODE) {
            if (responseBean.getErrors().size() > 0) {
                msg = responseBean.getErrors().get(0).getMessage();
            } else {
                manageSimpleErrorMsg(view.getContext(), responseBean);
            }

        } else {
            manageSimpleErrorMsg(view.getContext(), responseBean);
        }
        DialogUtil.showSnackBar(view, msg);
    }

    public static void showResponseErrorMsg(View view, SimpleListResponse responseBean) {
        String msg = "";
        if (responseBean.getErrorCode() == ApiConstant.VALIDATION_ERROR_CODE) {
            if (responseBean.getErrors().size() > 0) {
                msg = responseBean.getErrors().get(0).getMessage();
            } else {
                manageSimpleErrorMsg(view.getContext(), responseBean);
            }

        } else {
            manageSimpleErrorMsg(view.getContext(), responseBean);
        }
        DialogUtil.showSnackBar(view, msg);
    }

    private static String manageSimpleErrorMsg(Context context, SimpleListResponse responseBean) {
        if (!responseBean.getMessage().isEmpty()) {
            return responseBean.getMessage();
        } else
            return context.getString(R.string.something_went_wrong);
    }

    private static String manageSimpleErrorMsg(Context context, SimpleResponse responseBean) {
        if (!responseBean.getMessage().isEmpty()) {
            return responseBean.getMessage();
        } else
            return context.getString(R.string.something_went_wrong);
    }
}
