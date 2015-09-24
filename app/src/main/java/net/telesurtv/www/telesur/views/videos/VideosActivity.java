package net.telesurtv.www.telesur.views.videos;

import android.os.Bundle;
import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;


public class VideosActivity extends BaseNavigationDrawerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        setHighLevelActivity();

    }


}
