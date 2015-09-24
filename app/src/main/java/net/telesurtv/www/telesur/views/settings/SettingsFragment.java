package net.telesurtv.www.telesur.views.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 17/09/15.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {


    private final String NOTIFICATION_KEY = "notifys";
    private final String SUPPORT_KEY = "evaluation";
    private final String ABOUT_KEY = "about";
    private final String PRIVACITY_KEY = "privacity";
    private final String READ_KEY = "read";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.general_preferences);
        initializePreferences();

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        switch (preference.getKey()) {

            case NOTIFICATION_KEY:
                launchDetailPreference(getString(R.string.pref_title_notification));
                break;
            case SUPPORT_KEY:
                launchGooglePlay();
                break;
            case ABOUT_KEY:
                launchDetailPreference(getString(R.string.pref_title_about));
                break;
            case PRIVACITY_KEY:
                launchDetailPreference(getString(R.string.pref_title_privacity));
                break;
            case READ_KEY:
                launchDetailPreference(getString(R.string.pref_title_read));
                break;
        }


        return false;
    }

    private void launchDetailPreference(String title) {
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        intent.putExtra("title_preference", title);
        startActivity(intent);
    }

    private void launchGooglePlay(){
        String url = "https://play.google.com/store/apps/details?id=app.telesur.x&hl=es_419";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void initializePreferences() {
        findPreference(NOTIFICATION_KEY).setOnPreferenceClickListener(this);
        findPreference(SUPPORT_KEY).setOnPreferenceClickListener(this);
        findPreference(ABOUT_KEY).setOnPreferenceClickListener(this);
        findPreference(PRIVACITY_KEY).setOnPreferenceClickListener(this);
        findPreference(READ_KEY).setOnPreferenceClickListener(this);

    }


}
