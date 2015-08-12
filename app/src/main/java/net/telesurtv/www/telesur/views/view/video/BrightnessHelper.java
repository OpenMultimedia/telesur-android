package net.telesurtv.www.telesur.views.view.video;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

/**
 * Created by Jhordan on 27/07/15.
 */
public class BrightnessHelper {
    public static void setBrightness(Context context, int brightness){
        ContentResolver cResolver = context.getContentResolver();
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    public static int getBrightness(Context context) {
        ContentResolver cResolver = context.getContentResolver();
        try {
            return Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            return 0;
        }
    }
}
