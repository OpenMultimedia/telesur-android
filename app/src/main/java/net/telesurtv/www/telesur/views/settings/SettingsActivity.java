package net.telesurtv.www.telesur.views.settings;

import android.os.Build;
import android.os.Bundle;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;

/**
 * Created by Jhordan on 17/09/15.
 */
public class SettingsActivity extends NavigatorActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDark));

        setupSubActivityWithTitle();
    }


}
