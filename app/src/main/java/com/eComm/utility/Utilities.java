package com.eComm.utility;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Utilities {

    /**
     * Method to hide keyboard
     *
     * @param mContext Context of the calling class
     */
    public static void hideKeyboard(Context mContext) {
        try {
            InputMethodManager inputManager = (InputMethodManager) mContext
                    .getSystemService(INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    /**
     * Gets network state.
     *
     * @param context the context
     * @return the network state
     */
    public static boolean getNetworkState(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }


    public static boolean isMyServiceRunning(Class<?> serviceClass, Activity context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static Map<String, Object> getBeanToMap(Object bean) {
        Map<String, Object> properties = new HashMap<>();
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers())
                    && method.getParameterTypes().length == 0
                    && method.getReturnType() != void.class
                    && method.getName().matches("^(get|is).+")
            ) {
                String name = method.getName().replaceAll("^(get|is)", "");
                name = Character.toLowerCase(name.charAt(0)) + (name.length() > 1 ? name.substring(1) : "");
                Object value = null;
                try {
                    value = method.invoke(bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (value != null)
                    properties.put(name, value);
            }
        }
        return properties;
    }

    public static String formatDoubleValueUpToTwoDecimal(double val) {
        DecimalFormat df = new DecimalFormat("####0.00");
        String value = df.format(val);
        if (value.contains(".00"))
            value = value.replace(".00", "");
        return value;
    }

    public static void openSoftKeyboard(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(editText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        editText.requestFocus();
    }


/*
    public static void clearUserDataOnLogout(Activity activity) {
        SocialLoginHelper socialLoginHelper = new SocialLoginHelper();
        socialLoginHelper.googleLogout(activity);
        SessionManager.get().setLoggedIn(false);
        SessionManager.get().setCurrencySymbol("");
        SessionManager.get().setUnitType("");
        SessionManager.get().setReportType("");
        SessionManager.get().setRateKm("");
        SessionManager.get().clear();
//        SessionManager.get().setSocialUser(false);
    }
*/



    /**
     * This method is used to hide keyboard on clicking anywhere on the screen.
     *
     * @param view    parent view
     * @param context Context of the current activity.
     */
    public static void hideKeyboardOnClickingScreen(View view, final Context context) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(context);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                hideKeyboardOnClickingScreen(innerView, context);
            }
        }
    }



    public static <T> T fromJson(String json, Class<T> clazz) {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create().fromJson(json, clazz);
    }

    public static void makeCall(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    public static String getFormattedCurrency(String price) {
        if (price != null && !price.contains(",")) {
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            String value = format.format(Double.valueOf(price));
            if (value.contains("$"))
                value = value.replace("$", "");
            if (value.contains(".00"))
                value = value.replace(".00", "");
            return value;
        } else if (price.contains(","))
            return price;
        else
            return "";
    }

    public static double getFormattedCurrency(double price) {
        String formattedPrice = getFormattedCurrency(formatDoubleValueUpToTwoDecimal(price));
        return Double.parseDouble(formattedPrice);
    }

    public static String getUnFormattedCurrency(String price) {
        return price.replace(",", "");
    }


    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("FB KEYHASH", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("FB KEYHASH", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("FB KEYHASH", "printHashKey()", e);
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            }
            return null;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static String getFormattedStringForFloat(float value) {
        return String.format(Locale.getDefault(), "%.02f", value);
    }

    public static float round(float value) {
        return (float) Math.round((value * 1000) / 10) / 100;
    }

    public static String roundAndFormatWithTowDecimalPlace(float value) {
        return getFormattedStringForFloat(round(value));
    }
}
