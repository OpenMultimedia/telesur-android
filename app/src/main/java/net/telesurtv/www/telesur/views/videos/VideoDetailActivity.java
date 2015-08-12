

package net.telesurtv.www.telesur.views.videos;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.views.view.video.FensterPlayerControllerVisibilityListener;
import net.telesurtv.www.telesur.views.view.video.FensterVideoView;
import net.telesurtv.www.telesur.views.view.video.SimpleMediaFensterPlayerController;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VideoDetailActivity extends AppCompatActivity implements FensterPlayerControllerVisibilityListener {


    private FensterVideoView textureView;
    private SimpleMediaFensterPlayerController fullScreenMediaPlayerController;
    FrameLayout frameLayout;
    ImageView imageView;
    DraggableView draggablePanel;
    int i = 0;
    @Bind(R.id.pruebas_video_view)
    VideoView videoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        ButterKnife.bind(this);

        initializeVideoView();
     /*   if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar !=  null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        VideoView videoView = (VideoView) findViewById(R.id.video_view);

        String URL = "http://media-telesur.openmultimedia.biz/clips/telesur-video-2015-07-27-125522768408.mp4";
        Uri uri = Uri.parse(URL); //Declare your url here.
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();



        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("video");





        RecyclerView rv = (RecyclerView)findViewById(R.id.scrollableview);
        setupRecyclerView(rv);*/
     //   bindViews();
      //  imageView = (ImageView) findViewById(R.id.media_full_screen);
      //  frameLayout = (FrameLayout) findViewById(R.id.frame_layout_video);
        draggablePanel = (DraggableView)findViewById(R.id.pruebas_draggable_view);


        //initVideo();

        hookDraggableViewListener();


    }



    @Override
    protected void onResume() {
        super.onResume();

        /*
        textureView.setVideo("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
                fullScreenMediaPlayerController.DEFAULT_VIDEO_START);
        textureView.start();




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;

                if (i == 1) {

                    frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

                } else {

                    frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 600));


                    i = 0;
                }


            }
        });*/


    }

    private void bindViews() {
        textureView = (FensterVideoView) findViewById(R.id.play_video_texture);
        fullScreenMediaPlayerController = (SimpleMediaFensterPlayerController) findViewById(R.id.play_video_controller);
    }

    private void initVideo() {
        fullScreenMediaPlayerController.setVisibilityListener(this);
        textureView.setMediaController(fullScreenMediaPlayerController);
        textureView.setOnPlayStateListener(fullScreenMediaPlayerController);
    }



    private void initializeVideoView() {
        //  Uri path = Uri.parse(APPLICATION_RAW_PATH + R.raw.video);

        String URL = "http://media-telesur.openmultimedia.biz/clips/telesur-video-2015-07-27-125522768408.mp4";
        Uri uri = Uri.parse(URL); //Declare your url here.
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

/*
    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new RecyclerVideoAdapter());
    }

    // Generamos videos y repetimos para tener una lista grande
    private List<VideoViewModel> videos() {


        List<VideoViewModel> videoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            VideoViewModel video = new VideoViewModel();
            video.setTitle("Viola coalición saudí tercera tregua humanitaria en Yemen");
            video.setCategory("Política");
            video.setData("27 de julio 2015");
            video.setDuration("00:52");
            videoList.add(video);
        }

        return videoList;
    }
*/

    private void setSystemUiVisibility(final boolean visible) {
        int newVis = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

        if (!visible) {
            newVis |= View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(newVis);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(final int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == 0) {
                    fullScreenMediaPlayerController.show();
                }
            }
        });
    }

    @Override
    public void onControlsVisibilityChange(boolean value) {
        setSystemUiVisibility(value);
    }



    private void hookDraggableViewListener() {
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override public void onMaximized() {
                startVideo();
            }

            //Empty
            @Override public void onMinimized() {
                //Empty
            }

            @Override public void onClosedToLeft() {
                pauseVideo();
            }

            @Override public void onClosedToRight() {
                pauseVideo();
            }
        });
    }

    /**
     * Pause the VideoView content.
     */
    private void pauseVideo() {
        if (textureView.isPlaying()) {
            textureView.pause();
        }
    }

    /**
     * Resume the VideoView content.
     */
    private void startVideo() {
        if (!textureView.isPlaying()) {
            textureView.start();
        }
    }
}
