package com.eComm.utility;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.eComm.R;
import com.eComm.msupport.MSupportConstants;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtility {

    public static final int PERMISSIONS_REQUEST = 159;
    public static final int REQUEST_PERMISSION_SETTING = 160;

    public static final String CAMERA = Manifest.permission.CAMERA;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String RECEIVE_SMS= Manifest.permission.RECEIVE_SMS;
    public static final String READ_SMS= Manifest.permission.READ_SMS;
    public static final String ACCESS_WIFI_STATE = Manifest.permission.ACCESS_WIFI_STATE;
    public static final String ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE;
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;

    private static PermissionCallback mPermissionCallback;

    public static boolean isMSupportDevice(Context ctx) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static void checkPermission(final Activity mActivity, final Fragment fragment, String[] permissionSet,
                                       PermissionCallback permissionCallback) {

        if (PermissionUtility.isMSupportDevice(mActivity)) {
            mPermissionCallback = permissionCallback;
            final List<String> permissions = new ArrayList<>();
            for (final String aPermissionSet : permissionSet) {
                int hasPermission = mActivity.checkSelfPermission(aPermissionSet);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    permissions.add(aPermissionSet);
                    ActivityCompat.requestPermissions(mActivity, new String[]{aPermissionSet}, PERMISSIONS_REQUEST
                    );
                    if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, aPermissionSet)) {

                    } else {
                        mPermissionCallback = null;
                        DialogUtil.showAlertDialog(mActivity, mActivity.getString(R.string.msg_permission_setting), mActivity.getString(R.string.SETTINGS), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
                                intent.setData(uri);
                                mActivity.startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                            }
                        }, true);
                        break;
                    }
                }
            }

            if (permissions.size() > 0) {
                if (fragment != null)
                    fragment.requestPermissions(permissions.toArray(new String[permissions.size()]), PERMISSIONS_REQUEST);
                else
                    mActivity.requestPermissions(permissions.toArray(new String[permissions.size()]), PERMISSIONS_REQUEST);
            } else {
                if (mPermissionCallback != null)
                    mPermissionCallback.onPermissionResult(true);
            }

        } else if (mPermissionCallback != null)
            mPermissionCallback.onPermissionResult(true);
    }


    /**
     * Call this method from {@link Activity#onRequestPermissionsResult} or {@link Fragment#onRequestPermissionsResult} to handle the result return in it, <br/>
     * For now we support checking the permission status for one permission and the first permission
     */
    public static void handleOnRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode != PERMISSIONS_REQUEST)
            return;

        boolean granted = true;
        for (int grantResult : grantResults) {
            granted = granted & grantResult == PackageManager.PERMISSION_GRANTED;
        }

        // this to fix a bug
        PermissionCallback callback = mPermissionCallback;
        if (callback != null) {
            mPermissionCallback = null;
            callback.onPermissionResult(granted);
        }
    }

    public static void checkMultiplePermission(final Activity mActivity, String[] permissionsList2, PermissionCallback permissionCallback) {
        if (PermissionUtility.isMSupportDevice(mActivity)) {
            mPermissionCallback = permissionCallback;
            List<String> permissionsNeeded = new ArrayList<>();
            final List<String> permissionsList = new ArrayList<>();

            for (String permission : permissionsList2) {
                if (addPermission(mActivity, permission, permissionsList)) {
                    permissionsNeeded.add(MSupportConstants.getPermissionRationaleMessage(permission));
                }
            }

            if (permissionsList.size() > 0) {
                if (permissionsNeeded.size() > 0) {
                    // Need Rationale case
                    requestPermissions(permissionsList, null, mActivity,
                            PERMISSIONS_REQUEST);
//                    String message = "You need to grant access to " + permissionsNeeded.get(0);
//                    for (int i = 1; i < permissionsNeeded.size(); i++)
//                        message = message + ", " + permissionsNeeded.get(i);
//                    DialogUtil.showAlertDialog(mActivity, message, mActivity.getString(R.string.ok), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            requestPermissions(permissionsList, null, mActivity,
//                                    PERMISSIONS_REQUEST);
//                        }
//                    }, true);
                } else
                    requestPermissions(permissionsList, null, mActivity,
                            PERMISSIONS_REQUEST);
            } else {
                if (mPermissionCallback != null)
                    mPermissionCallback.onPermissionResult(true);
            }
        } else {
            if (mPermissionCallback != null)
                mPermissionCallback.onPermissionResult(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static boolean addPermission(final Activity mActivity, String permission, List<String> permissionsList) {
        if (mActivity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (mActivity.shouldShowRequestPermissionRationale(permission))
                return true;
        }
        return false;
    }

    @TargetApi(23)
    private static void requestPermissions(List<String> permissions, Fragment fragment, Activity mActivity, int requestCode) {
        if (!permissions.isEmpty()) {
            if (fragment != null)
                fragment.requestPermissions(permissions.toArray(new String[permissions.size()]), requestCode);
            else
                mActivity.requestPermissions(permissions.toArray(new String[permissions.size()]), requestCode);
        }
    }




    public static boolean isMessageReceivePermissionGranted(Activity activity, Fragment fragment, int PERMISSION_REQUEST_CODE) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS)
                            == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                if (fragment == null) {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.RECEIVE_SMS,
                                    Manifest.permission.READ_SMS}, PERMISSION_REQUEST_CODE);
                } else {
                    fragment.requestPermissions(
                            new String[]{Manifest.permission.RECEIVE_SMS,
                                    Manifest.permission.READ_SMS}, PERMISSION_REQUEST_CODE);
                }
                return false;
            }
        } else {
            return true;
        }
    }

    public interface PermissionCallback {

        /**
         * @param granted true if granted, false otherwise
         */
        void onPermissionResult(boolean granted);
    }
}
