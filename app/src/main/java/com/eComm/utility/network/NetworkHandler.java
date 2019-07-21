package com.eComm.utility.network;

import android.content.Context;
import android.view.View;

import com.eComm.utility.DialogUtil;
import com.eComm.utility.Utilities;


public class NetworkHandler {

    public static boolean isConnected;

    public static boolean isConnected() {
        return isConnected;
    }

    public static boolean isConnected(Context context) {
        if (!isConnected) {
            DialogUtil.showNoNetworkToast(context.getApplicationContext());
            NetworkHandler.isConnected = Utilities.getNetworkState(context);
        }
        return isConnected;
    }

    public static boolean isConnected(View anyView) {
        if (!isConnected) {
            if (anyView != null) {
                DialogUtil.showNoNetworkSnackBar(anyView);
                NetworkHandler.isConnected = Utilities.getNetworkState(anyView.getContext());
            }
        }
        return isConnected;
    }

    public static boolean isConnected(View anyView, View.OnClickListener retryListener) {
        if (!isConnected) DialogUtil.showNoNetworkSnackBar(anyView);
        return isConnected;
    }


}
