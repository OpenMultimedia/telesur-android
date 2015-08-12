package net.telesurtv.www.telesur.views.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.views.dialog.ReadTelesur;
import net.telesurtv.www.telesur.views.dialog.TermsTelesur;


public class AboutActivity extends NavigatorActivity implements ActionBarDrawerListener.Listener, View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDark));

        setupSubActivityWithTitle();
        initializeViews();


    }


    @Override
    public void onNotImplementedFeatureSelected() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.txt_about_tandc:
                //showAboutDialogTerms();
                break;

            case R.id.txt_about_read:
                // showAboutDialog();
                break;
        }


    }


    private void initializeViews() {
        ((TextView) findViewById(R.id.txt_about_tandc)).setOnClickListener(this);
        ((TextView) findViewById(R.id.txt_about_read)).setOnClickListener(this);
    }

    private void showAboutDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ReadTelesur.newInstance().show(fm, "telesur_about");
    }

    private void showAboutDialogTerms() {
        FragmentManager fm = getSupportFragmentManager();
        TermsTelesur.newInstance().show(fm, "telesur_about_terms");
    }


}
