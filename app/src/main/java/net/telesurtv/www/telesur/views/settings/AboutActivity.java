package net.telesurtv.www.telesur.views.settings;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;


public class AboutActivity extends NavigatorActivity {

    String title;
    private final String NOTIFICATION_ID = "Configurar notificaciones";
    private final String ABOUT_ID = "Acerca de";
    private final String PRIVACITY_ID = "Privacidad";
    private final String READ_ID = "Leér sobre teleSUR";
    private final String URL_PRIVACITY = "http://www.telesurtv.net/pages/terminosdeuso.html";
    private final String URL_READ = "http://www.telesurtv.net/pages/sobrenosotros.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setupSubActivityWithTitle();
        setCustomBars();
        fragmentSelected(title);


    }

    private void fragmentSelected(String title) {

        switch (title) {
            case NOTIFICATION_ID:
                fragmentTransactionReplace(NotificationFragment.newInstance());
                break;
            case ABOUT_ID:
                fragmentTransactionReplace(AboutFragment.newInstance());
                break;
            case PRIVACITY_ID:
                fragmentTransactionReplace(WebFragment.newInstance(URL_PRIVACITY));
                break;
            case READ_ID:
                fragmentTransactionReplace(WebFragment.newInstance(URL_READ));
                break;
        }

    }


    private void fragmentTransactionReplace(Fragment fragmentInstance) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_preferences, fragmentInstance)
                .commit();
    }

    private void setCustomBars() {

        if (getIntent() != null) {
            title = getIntent().getStringExtra("title_preference");
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(title);
        }


        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDark));
    }

}
