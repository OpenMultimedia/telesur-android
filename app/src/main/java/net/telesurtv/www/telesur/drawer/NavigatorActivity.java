package net.telesurtv.www.telesur.drawer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class NavigatorActivity extends AppCompatActivity {

    private Navigator navigator;
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = new Navigator(this);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setHighLevelActivity() {
        setupToolbar();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void setHighLevelActivityOther() {
        setupToolbar();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getToolbar().setTitleTextColor(getResources().getColor(R.color.accent));
            getToolbar().setBackgroundColor(getResources().getColor(R.color.primary_videos));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_gray);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }

    }

    protected void setupSubActivity() {
        setupToolbar();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setShowHideAnimationEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    protected void setupSubActivityCustom(int color) {
        setupToolbar();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setShowHideAnimationEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            getToolbar().setBackgroundColor(color);
        }
    }


    protected void setupSubActivityWithTitle() {
        setupSubActivity();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }

    }

    public Navigator navigate() {
        if (navigator == null) {
            navigator = new Navigator(this);
        }
        return navigator;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

}

