package net.telesurtv.www.telesur.views.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.storage.Preferences;
import net.telesurtv.www.telesur.views.news.NewsActivity;
import net.telesurtv.www.telesur.views.review.ReviewActivity;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Jhordan on 25/09/15.
 */
public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    public static final String KEY_1 = "1";
    public static final String KEY_2 = "2";
    public static final String KEY_3 = "3";
    public static final String KEY_4 = "4";
    public static final String KEY_5 = "5";
    public static final String TAGS = "PLMDC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        setContentView(R.layout.activity_splash);
        setStatusBarColor();
        initalizeSettings();
        launchHome();
    }


    // launch to Home Screen
    private void launchHome() {

        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, NewsActivity.class));
                    finish();
                }
            }, SPLASH_TIME_OUT);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //change statusBar color
    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.splash_color));
    }

    // Initialize key for notification push
    private void initalizeSettings() {

        if (Preferences.getPushWooshTag(SplashScreen.this).equals("empity_tags")) {
            Preferences.setCheckBoxPreference(SplashScreen.this, KEY_1, true);
            Preferences.setCheckBoxPreference(SplashScreen.this, KEY_2, true);
            Preferences.setCheckBoxPreference(SplashScreen.this, KEY_3, true);
            Preferences.setCheckBoxPreference(SplashScreen.this, KEY_4, true);
            Preferences.setCheckBoxPreference(SplashScreen.this, KEY_5, true);
            Preferences.setPushWooshTag(SplashScreen.this, TAGS);
        }

    }


}
