package net.telesurtv.www.telesur.views.videos;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;


public class VideoListDetailActivity extends NavigatorActivity {


    AppBarLayout appBarLayout;
    TextView txtSection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_videos);

        setupSubActivityWithTitle();
        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        txtSection = (TextView)findViewById(R.id.txt_title_section);
        intializeFromIntent();





    }


    private void intializeFromIntent() {
        if (getIntent() != null) {

            if (getSupportActionBar() != null)
                getToolbar().setBackgroundColor(getIntent().getIntExtra("video_primary", 0));
                appBarLayout.setBackgroundColor(getIntent().getIntExtra("video_primary", 0));
            if (Build.VERSION.SDK_INT >= 21)
                getWindow().setStatusBarColor(getIntent().getIntExtra("video_primary_dark", 0));


            initializeListFragment(getIntent().getIntExtra("video_position", 0));

        }
    }

    private void initializeListFragment(int position) {

        Fragment fragment = null;
        String title = null;
        switch (position) {

            case 0:
                fragment = VideoNewsFragment.newInstance();
                title = getString(R.string.news_videos);
                break;
            case 1:
                fragment = VideoInterviewFragment.newInstance();
                title = getString(R.string.interview_videos);
                break;
            case 2:
                fragment = VideoDocumentalFragment.newInstance();
                title = getString(R.string.documental_videos);
                break;
            case 3:
                fragment = VideoInfografiaFragment.newInstance();
                title = getString(R.string.infogra_videos);
                break;
            case 4:
                fragment = VideoSpecialsFragment.newInstance();
                title = getString(R.string.specials_videos);
                break;
            case 5:
                fragment = VideoReportFragment.newInstance();
                title = getString(R.string.report_videos);
                break;

        }
        fragmentTransactionReplace(fragment);
        txtSection.setText(title);


    }

    private void fragmentTransactionReplace(Fragment fragmentInstance) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_list_videos, fragmentInstance)
                .commit();
    }


}
