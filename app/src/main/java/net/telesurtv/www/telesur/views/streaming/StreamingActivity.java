package net.telesurtv.www.telesur.views.streaming;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;
import net.telesurtv.www.telesur.views.fragment.FragmentEmpity;


public class StreamingActivity extends BaseNavigationDrawerActivity implements ActionBarDrawerListener.Listener {

    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming);

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
        fmAdapter.addFragment(FragmentEmpity.newInstance(), getString(R.string.signal));
        fmAdapter.addFragment(FragmentEmpity.newInstance(), getString(R.string.audio));
        viewPager.setAdapter(fmAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }




    @Override
    public void onNotImplementedFeatureSelected() {

    }



}
