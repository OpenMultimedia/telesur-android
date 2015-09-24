package net.telesurtv.www.telesur.views.review;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;


public class ReviewActivity extends BaseNavigationDrawerActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        setHighLevelActivity();
        setupViews();

        if (viewPager != null)
            setUpViewPager(viewPager);


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
        fmAdapter.addFragment(ArticleFragment.newInstance(), getString(R.string.articles));
        fmAdapter.addFragment(InterviewFragment.newInstance(), getString(R.string.interview));
        fmAdapter.addFragment(BlogFragment.newInstance(), getString(R.string.blogs));
        viewPager.setAdapter(fmAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }






}
