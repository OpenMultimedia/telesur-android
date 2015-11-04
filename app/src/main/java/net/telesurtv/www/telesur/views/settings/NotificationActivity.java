package net.telesurtv.www.telesur.views.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;


public class NotificationActivity extends NavigatorActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        setupSubActivityWithTitle();
        setCustomBars();


    }


    private void fragmentTransactionReplace(Fragment fragmentInstance) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_notification, fragmentInstance)
                .commit();
    }

    private void setCustomBars() {

        if (getIntent() != null) {
            fragmentTransactionReplace(WebFragment.newInstance(getIntent().getStringExtra("link")));
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Notificación");
        }

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDark));

    }

}
