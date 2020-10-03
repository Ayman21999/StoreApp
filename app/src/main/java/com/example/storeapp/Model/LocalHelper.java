package com.example.storeapp.Model;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;
import java.util.prefs.Preferences;

public class LocalHelper {

    private static final String SELECTED_LANG = "Local.Helper.Selected.Language";

    public static Context onAttach(Context context) {
        String lang = getPersistesData(context, Locale.getDefault().getLanguage());
        return setLocal(context, lang);

    }

    public static Context onAttach(Context context, String Defaultlang) {
        String lang = getPersistesData(context, Defaultlang);
        return setLocal(context, lang);

    }

    private static Context setLocal(Context context, String lang) {
        persist(context, lang);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return updateResoures(context, lang);

            return updateResourseLagacy(context,lang);

    }
    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResoures(Context context, String lang) {
    Locale locale = new Locale(lang);
    Locale.setDefault(locale);

        Configuration config  = context.getResources().getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);

        return context.createConfigurationContext(config);

    }

    @SuppressWarnings("depreation")
    private static Context updateResourseLagacy(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN);
        config.setLayoutDirection(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
        return context ;

    }

    private static void  persist(Context context ,String lang){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANG , lang);
        editor.apply();
        
    }


    private static String getPersistesData(Context context, String language) {

        SharedPreferences   preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANG,language);
    }

}
