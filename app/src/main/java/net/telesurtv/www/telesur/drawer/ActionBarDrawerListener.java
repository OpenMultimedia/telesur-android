package net.telesurtv.www.telesur.drawer;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.novoda.notils.caster.Classes;

import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class ActionBarDrawerListener implements DrawerLayout.DrawerListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationTarget pendingNavigation;
    private BaseNavigationDrawerActivity activity;


    public ActionBarDrawerListener(BaseNavigationDrawerActivity activity, DrawerLayout drawerLayout) {
        this.activity = activity;
        this.drawerLayout = drawerLayout;

    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }


    @Override
    public void onDrawerStateChanged(int newState) {

    }


    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

        if (pendingNavigation != null) {
            pendingNavigation.navigateUsing(activity.navigate());
            pendingNavigation = null;
        }

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.drawer_item_news:
                pendingNavigation = new NewsNavigationTarget();

                break;
            case R.id.drawer_item_videos:
                pendingNavigation = new VideosNavigationTarget();

                break;
            case R.id.drawer_item_programas:
                pendingNavigation = new ProgramsNavigationTarget();

                break;
            case R.id.drawer_item_reviews:
                pendingNavigation = new ReviewNavigationTarget();

                break;
            case R.id.drawer_item_streaming:
                pendingNavigation = new StreamingsNavigationTarget();

                break;
            case R.id.drawer_item_settings:

                pendingNavigation = new SettingsNavigationTarget();
                break;


        }


        drawerLayout.closeDrawers();

        return false;

    }




    interface NavigationTarget {
        void navigateUsing(Navigator navigator);
    }


    /**
     * static class to navigate
     */

    static class NewsNavigationTarget implements NavigationTarget {
        @Override
        public void navigateUsing(Navigator navigator) {
            navigator.toNews();
        }
    }

    static class VideosNavigationTarget implements NavigationTarget {

        @Override
        public void navigateUsing(Navigator navigator) {
            navigator.toVideos();
        }
    }

    static class ProgramsNavigationTarget implements NavigationTarget {

        @Override
        public void navigateUsing(Navigator navigator) {
            navigator.toPrograms();
        }
    }

    static class ReviewNavigationTarget implements NavigationTarget {

        @Override
        public void navigateUsing(Navigator navigator) {
            navigator.toReview();
        }
    }

    static class StreamingsNavigationTarget implements NavigationTarget {

        @Override
        public void navigateUsing(Navigator navigator) {
            navigator.toStreaming();
        }
    }

    static class SettingsNavigationTarget implements NavigationTarget {

        @Override
        public void navigateUsing(Navigator navigator) {
            navigator.toSettings();
        }
    }

    static class AboutNavigationTarget implements NavigationTarget {

        @Override
        public void navigateUsing(Navigator navigator) {
            navigator.toAbout();
        }
    }

}
