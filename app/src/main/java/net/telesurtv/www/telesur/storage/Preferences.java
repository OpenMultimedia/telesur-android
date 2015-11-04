package net.telesurtv.www.telesur.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jhordan on 29/09/15.
 */
public class Preferences {

    private final static String CHECKBOX = "checkbox_preferences";
    private final static Boolean CHECKBOX_DEFAULT = false;

    private final static String TAG = "tag_preferences";
    private final static String TAG_PUSH = "notification_tags";
    private final static String TAG_DEFOULT = "empity_tags";

    private final static String NOTIFY = "notification_preferences";
    private final static String NOTIFY_EXECUTE = "notification";
    private final static String NOTIFY_DEFAULT = "notification_empity_tags";

    public static void setPushWooshTag(Context context, String tag) {
        SharedPreferences sharedPref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TAG_PUSH, tag);
        editor.apply();
    }

    public static String getPushWooshTag(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return prefs.getString(TAG_PUSH, TAG_DEFOULT);
    }

    public static void setCheckBoxPreference(Context context, String title, Boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(CHECKBOX, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(title, value);
        editor.apply();
    }

    public static Boolean getCheckBoxPreference(Context context, String title) {
        SharedPreferences prefs = context.getSharedPreferences(CHECKBOX, Context.MODE_PRIVATE);
        return prefs.getBoolean(title, CHECKBOX_DEFAULT);
    }


    public static void setNotification(Context context, String tag) {
        SharedPreferences sharedPref = context.getSharedPreferences(NOTIFY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(NOTIFY_EXECUTE, tag);
        editor.apply();
    }

    public static String getNotification(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(NOTIFY, Context.MODE_PRIVATE);
        return prefs.getString(NOTIFY_EXECUTE, NOTIFY_DEFAULT);
    }
}
