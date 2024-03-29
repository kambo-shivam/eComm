package com.eComm.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.eComm.R;
import com.eComm.app.AppController;

import java.util.Locale;


public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    private static String DEVICE_DEFAULT_LANGUAGE;

    public static Context init(Context context) {
        DEVICE_DEFAULT_LANGUAGE = Locale.getDefault().getLanguage();
        String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        return setLanguage(context, lang);
    }

    public static Context reset(Context context) {
        return setLanguage(context, DEVICE_DEFAULT_LANGUAGE);
    }

    public static Context init(Context context, String defaultLanguage) {
        String lang = getPersistedData(context, defaultLanguage);
        return setLanguage(context, lang);
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }

    public static Context setLanguage(Context context, String language) {
        persist(context, language);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return updateResources(context, language);
        }*/
        return updateResourcesLegacy(context, language);
    }

    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(AppController.getInstance());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
//        setLayoutDirection(configuration, locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        setLayoutDirection(configuration, locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    private static void setLayoutDirection(Configuration configuration, Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            int layoutDirection = 0;
            layoutDirection = 1 + TextUtils.getLayoutDirectionFromLocale(locale);
            configuration.screenLayout = (configuration.screenLayout & ~Configuration.SCREENLAYOUT_LAYOUTDIR_MASK) |
                    (layoutDirection << Configuration.SCREENLAYOUT_LAYOUTDIR_SHIFT);
            configuration.setLayoutDirection(locale);
        }
    }


    public static boolean checkIsDeviceLangSame(String defaultLanguage) {
        return Locale.getDefault().getLanguage().equals(defaultLanguage);
    }

    public static void showDeviceChangePopup(Context context, String defLangName, DialogInterface.OnClickListener clickListener) {
        String alertMsg = "";
        DialogUtil.showAlertDialog(context, alertMsg, context.getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clickListener.onClick(dialog, which);
            }
        }, false);
    }
}
