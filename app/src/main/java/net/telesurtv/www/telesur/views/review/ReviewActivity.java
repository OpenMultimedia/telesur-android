package net.telesurtv.www.telesur.views.review;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ReviewActivity extends BaseNavigationDrawerActivity {


    @Bind(R.id.view_pager) ViewPager view_pager_review;
    @Bind(R.id.tabs) TabLayout tabs_review;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setHighLevelActivity();
        ButterKnife.bind(this);

        if (view_pager_review != null)
            setUpViewPager(view_pager_review);

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
        tabs_review.setupWithViewPager(viewPager);
    }






}
