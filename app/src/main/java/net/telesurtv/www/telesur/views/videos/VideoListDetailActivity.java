package net.telesurtv.www.telesur.views.videos;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Produce;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.util.OttoBus;


public class VideoListDetailActivity extends NavigatorActivity implements AppBarLayout.OnOffsetChangedListener{



    TextView txtSection;

    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getIntent() != null)
            setTheme(getIntent().getIntExtra("video_theme",0));


           /* if (Build.VERSION.SDK_INT >= 21)
                getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));*/


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_videos);

        setupSubActivityWithTitle();

        //collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
      //  imageView = (ImageView)findViewById(R.id.image_view_icon_video_detail);


        intializeFromIntent();

        if (Build.VERSION.SDK_INT >= 21){
        //   ViewCompat.setTransitionName(getToolbar(), getString(R.string.transition_toolbar));

        }



    }



    private void intializeFromIntent() {
        if (getIntent() != null) {

            if (getSupportActionBar() != null)


                initializeListFragment(getIntent().getIntExtra("video_position", 0));

        }
    }

    private void initializeListFragment(int position) {

        int[] icons = {
                R.drawable.ic_v_menu_news,
                R.drawable.ic_v_menu_interview,
                R.drawable.ic_v_menu_documental,
                R.drawable.ic_v_menu_info,
                R.drawable.ic_v_menu_specials,
                R.drawable.ic_v_menu_report
        };

       // imageView.setImageResource(icons[position]);
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
        if(getSupportActionBar() != null)
           getSupportActionBar().setTitle(title);
       // txtSection.setText(title);
       // collapsingToolbarLayout.setTitle(title);
    //    getSupportActionBar().setTitle(title);


    }

    private void fragmentTransactionReplace(Fragment fragmentInstance) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_list_videos, fragmentInstance)
                .commit();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        System.out.println(i);

    }
}
