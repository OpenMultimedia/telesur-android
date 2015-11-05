package net.telesurtv.www.telesur.drawer;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

import com.novoda.notils.caster.Views;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;

/**
 * Created by Jhordan on 28/07/15.
 */
public abstract class BaseNavigationDrawerActivity extends NavigatorActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerListener drawerListener;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        drawerLayout = Views.findById(this,R.id.drawer_layout);
        drawerListener = new ActionBarDrawerListener(this, drawerLayout);
        drawerLayout.setDrawerListener(drawerListener);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setNavigationItemSelectedListener(drawerListener);
    }


    protected DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void closeDrawer() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {

        if (shouldBackPressedBeInterceptedByNavDrawer())
            closeDrawer();
        else
            super.onBackPressed();
    }

    protected boolean shouldBackPressedBeInterceptedByNavDrawer() {
        return drawerLayout.isDrawerOpen(Gravity.LEFT);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
