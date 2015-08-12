package net.telesurtv.www.telesur.views.videos;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;


public class VideosActivity extends BaseNavigationDrawerActivity implements ActionBarDrawerListener.Listener {

    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        setHighLevelActivityOther();



        if (Build.VERSION.SDK_INT >= 21)
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
      //  setupViews();
/*
        if (viewPager != null)
            setUpViewPager(viewPager);*/


    }


    @Override
    protected void onResume() {
        super.onResume();
       // setViewPagerListener();
    }

    /**
     * getViews from xml
     */
    private void setupViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    /*
     * add adapter to viewPager and set ViewPager to tabLayout
     */

    protected void setUpViewPager(ViewPager viewPager) {
        FragmentAdapter fmAdapter = new FragmentAdapter(getSupportFragmentManager());
        fmAdapter.addFragment(VideoNewsFragment.newInstance(), getString(R.string.news_videos));
        fmAdapter.addFragment(VideoInterviewFragment.newInstance(), getString(R.string.interview_videos));
        fmAdapter.addFragment(VideoDocumentalFragment.newInstance(), getString(R.string.documental_videos));
        fmAdapter.addFragment(VideoInfografiaFragment.newInstance(), getString(R.string.infogra_videos));
        fmAdapter.addFragment(VideoSpecialsFragment.newInstance(), getString(R.string.specials_videos));
        fmAdapter.addFragment(VideoReportFragment.newInstance(), getString(R.string.report_videos));
        viewPager.setAdapter(fmAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    /*
     *
     */


  /*  protected void setUpViewPager(ViewPager viewPager) {
        FragmentAdapterVideo fmAdapter = new FragmentAdapterVideo(VideosActivity.this, getSupportFragmentManager());
        fmAdapter.addFragment(VideoNewsFragment.newInstance(), getString(R.string.news_videos), R.drawable.ic_news_videos_selector);
        fmAdapter.addFragment(VideoInterviewFragment.newInstance(), getString(R.string.interview_videos), R.drawable.ic_interview_videos_selector);
        fmAdapter.addFragment(VideoDocumentalFragment.newInstance(), getString(R.string.documental_videos), R.drawable.ic_documental_videos_selector);
        fmAdapter.addFragment(VideoInfografiaFragment.newInstance(), getString(R.string.infogra_videos), R.drawable.ic_infografia_videos_selector);
        fmAdapter.addFragment(VideoSpecialsFragment.newInstance(), getString(R.string.specials_videos), R.drawable.ic_special_videos_selector);
        fmAdapter.addFragment(VideoReportFragment.newInstance(), getString(R.string.report_videos), R.drawable.ic_report_videos_selector);
        viewPager.setAdapter(fmAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabLayout(tabLayout, fmAdapter);

    }


    public void setupTabLayout(TabLayout tabLayout, FragmentAdapterVideo fragmentAdapterVideo) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);


            if (tab != null)
                tab.setCustomView(fragmentAdapterVideo.getIconCustomTab(i));

        }
        tabLayout.requestFocus();
    }*/


    /*
     * add listener to viewPager
     */
    private void setViewPagerListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setThemeBackground(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onNotImplementedFeatureSelected() {

    }

    /**
     * Set color to FrameLayout
     *
     * @param position position of viewPager to set Color Background
     */
    private void setThemeBackground(int position) {

        int backgroundColorTheme = getResources().getColor(R.color.black);
        int statusBarColorTheme = getResources().getColor(R.color.black);

        switch (position) {
            case 0:
                backgroundColorTheme = getResources().getColor(R.color.video_oustanding_theme);
                statusBarColorTheme = getResources().getColor(R.color.oustanding_status_bar);
                break;
            case 1:
                backgroundColorTheme = getResources().getColor(R.color.video_latam_theme);
                statusBarColorTheme = getResources().getColor(R.color.latam_status_bar);
                break;
            case 2:
                backgroundColorTheme = getResources().getColor(R.color.video_world_theme);
                statusBarColorTheme = getResources().getColor(R.color.world_status_bar);
                break;
            case 3:
                backgroundColorTheme = getResources().getColor(R.color.video_sports_theme);
                statusBarColorTheme = getResources().getColor(R.color.sports_status_bar);
                break;
            case 4:
                backgroundColorTheme = getResources().getColor(R.color.video_culture_theme);
                statusBarColorTheme = getResources().getColor(R.color.culture_status_bar);
                break;

            case 5:
                backgroundColorTheme = getResources().getColor(R.color.video_oustanding_theme);
                statusBarColorTheme = getResources().getColor(R.color.oustanding_status_bar);
                break;

        }

        getToolbar().setBackgroundColor(backgroundColorTheme);
        tabLayout.setBackgroundColor(backgroundColorTheme);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(statusBarColorTheme);


    }

}
