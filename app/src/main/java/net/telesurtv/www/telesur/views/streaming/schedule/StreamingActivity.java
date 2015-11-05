package net.telesurtv.www.telesur.views.streaming.schedule;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import net.telesurtv.www.telesur.drawer.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class StreamingActivity extends BaseNavigationDrawerActivity {

    @Bind(R.id.view_pager) ViewPager viewPager;
    @Bind(R.id.tabs)TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming);
        setHighLevelActivity();
        ButterKnife.bind(this);

        if (viewPager != null)
            setUpViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        FragmentAdapter fmAdapter = new FragmentAdapter(getSupportFragmentManager());
        fmAdapter.addFragment(ScheduleFragment.newInstance(), getString(R.string.signal));
        viewPager.setAdapter(fmAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
