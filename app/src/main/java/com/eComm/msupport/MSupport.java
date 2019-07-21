/*
 *  Copyright (C) Prodege LLC, US. All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Written by Rupesh Mishra <rupesh@techaheadcorp.com>, 2015
 *
 */

package com.eComm.msupport;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;


import com.eComm.R;

import java.util.ArrayList;
import java.util.List;

public class MSupport {

    public static boolean isMSupportDevice(Context ctx) {
        /**
         * @return true in case of M Device,
         * false in case of below M devices
         */
        if (Build.VERSION.SDK_INT >= MSupportConstants.SDK_VERSION)
            return true;
        else
            return false;
    }

    /**
     * for the activity
     *
     * @param mActivity      Calling activity Context
     * @param permissionName for which permission is needed for performing the perticular funtion
     * @param requestcode    request code to identify the request
     * @return true in case of permission is granted
     * false in case of permission is not granted
     * in case of false we have to request that permission
     */
    public static boolean checkOrRequestPermission(Activity mActivity, String permissionName, int requestcode) {
        if (ContextCompat.checkSelfPermission(mActivity, permissionName)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(mActivity, new String[]{permissionName}, requestcode);
            return false;
        }
    }
   /* public static boolean checkOrRequestPermission(Activity mActivity, String permissionName, int requestcode) {

        if (MSupport.isMSupportDevice(mActivity)) {
            if (mActivity.checkSelfPermission(permissionName)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                mActivity.requestPermissions(new String[]{permissionName}, requestcode);
                return false;
            }
        } else
            return true;
    }*/

    /**
     * for the fragment
     *
     * @param f              Calling activity Context
     * @param permissionName for which permission is needed for performing the perticular funtion
     * @param requestcode    request code to identify the request
     * @return true in case of permission is granted
     * false in case of permission is not granted
     * in case of false we have to request that permission
     */

   /* public static boolean checkOrRequestPermission(Fragment f, String permissionName, int requestcode) {

        if (MSupport.isMSupportDevice(f.getActivity())) {
            if (f.getActivity().checkSelfPermission(permissionName)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                f.requestPermissions(new String[]{permissionName}, requestcode);
                return false;
            }
        } else
            return true;
    }
*/

    /**
     * MEthod to check permissions set and in this method we can
     * send the bunch of permission we needed to perform
     * the perticular function
     *
     * @param mActivity     Calling activity context
     * @param fragment      Calling fragment instance
     * @param permissionSet Permission set to check
     * @param requestCode   request code
     * @return true in case of permission is granted or pre marshmallow false in case of permission is not granted
     * in case of false we have to request that permission
     */
    @TargetApi(23)
    public static boolean checkPermission(Activity mActivity, Fragment fragment, String[] permissionSet, int requestCode) {

        if (MSupport.isMSupportDevice(mActivity)) {

            List<String> permissions = new ArrayList<>();
            for (String aPermissionSet : permissionSet) {
                int hasPermission = mActivity.checkSelfPermission(aPermissionSet);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    permissions.add(aPermissionSet);
                }
            }
            if (!permissions.isEmpty()) {
                if (fragment != null)
                    fragment.requestPermissions(permissions.toArray(new String[permissions.size()]), requestCode);
                else
                    mActivity.requestPermissions(permissions.toArray(new String[permissions.size()]), requestCode);
                return false;
            } else
                return true;

        } else
            return true;
    }

    private static void showMessageOKCancel(Activity activity, String message
            , DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.Theme_AppCompat_Dialog_Alert))
                .setMessage(message)
                .setPositiveButton(activity.getString(R.string.btn_ok), okListener)
                .create()
                .show();
    }


    public static boolean checkMultiplePermission(final Activity mActivity, String[] permissionsList2, final int requestCode) {
        if (MSupport.isMSupportDevice(mActivity)) {

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
                    String message = mActivity.getString(R.string.grant_access) + permissionsNeeded.get(0);
                    for (int i = 1; i < permissionsNeeded.size(); i++)
                        message = message + ", " + permissionsNeeded.get(i);
                    showMessageOKCancel(mActivity, message,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(permissionsList, null, mActivity,
                                            requestCode);
                                }
                            });
                    return false;
                }
                requestPermissions(permissionsList, null, mActivity,
                        requestCode);
                return false;
            } else
                return true;
        } else
            return true;
    }

    /**
     * @param mActivity
     * @param permission
     * @param permissionsList
     */
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

    /**
     * @param permissions
     * @param fragment
     * @param mActivity
     * @param requestCode
     */
    @TargetApi(23)
    private static boolean requestPermissions(List<String> permissions, Fragment fragment, Activity mActivity, int requestCode) {
        if (!permissions.isEmpty()) {
            if (fragment != null)
                fragment.requestPermissions(permissions.toArray(new String[permissions.size()]), requestCode);
            else
                mActivity.requestPermissions(permissions.toArray(new String[permissions.size()]), requestCode);
            return false;
        } else
            return true;
    }
}

