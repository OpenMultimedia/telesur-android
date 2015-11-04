package net.telesurtv.www.telesur.views.videos.video;

import android.os.Bundle;

import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;


public class VideosActivity extends BaseNavigationDrawerActivity {


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        setHighLevelActivity();
    }


}
