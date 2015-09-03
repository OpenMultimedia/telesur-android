

package net.telesurtv.www.telesur.views.videos;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import net.telesurtv.www.telesur.ItemRecyclerClickListener;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.views.adapter.RecyclerVideoDetailAdapter;
import net.telesurtv.www.telesur.views.view.video.FensterPlayerControllerVisibilityListener;
import net.telesurtv.www.telesur.views.view.video.FensterVideoView;
import net.telesurtv.www.telesur.views.view.video.MediaFensterPlayerController;
import net.telesurtv.www.telesur.views.view.video.SimpleMediaFensterPlayerController;

import java.util.ArrayList;
import java.util.List;


public class VideoReproductorActivity extends AppCompatActivity implements ItemRecyclerClickListener, FensterPlayerControllerVisibilityListener {

    RecyclerView recyclerViewVideo;
    VideoView videoView;
    RecyclerVideoDetailAdapter recyclerVideoAdapter;

    private FensterVideoView textureView;
    private SimpleMediaFensterPlayerController fullScreenMediaPlayerController;
    private MediaFensterPlayerController mediaFensterPlayerController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video_pruebas);
        recyclerVideoAdapter = new RecyclerVideoDetailAdapter();
        initializeViews();
        initVideo();
        initializeVideoCustom(getIntent().getStringExtra("video"));


    }


    /**
     * initialize recyclerView
     */
    protected void setupRecyclerView() {
        recyclerViewVideo.setLayoutManager(new LinearLayoutManager(recyclerViewVideo.getContext()));
        recyclerViewVideo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVideo.setAdapter(recyclerVideoAdapter);
        recyclerVideoAdapter.setItemRecyclerClickListener(this);
        updateUI();
    }

    private void updateUI() {
        recyclerVideoAdapter.clear();
        recyclerVideoAdapter.setListItems(videos());

    }


    private void initVideo() {
//        fullScreenMediaPlayerController.setVisibilityListener(this);
        mediaFensterPlayerController.setVisibilityListener(this);
        textureView.setMediaController(mediaFensterPlayerController);
       // textureView.setOnPlayStateListener(mediaFensterPlayerController);
    }

    private List<VideoViewModel> videos() {


        List<VideoViewModel> videoList = new ArrayList<>();
        videoList.clear();
        for (int i = 0; i < 10; i++) {
            VideoViewModel video = new VideoViewModel();
            video.setTitle("Viola coalición saudí tercera tregua humanitaria en Yemen");
            video.setCategory("Política");
            video.setData("2015-07-29 08:31:29");
            video.setDuration("00:52");
            video.setVisitCounter("152");
            videoList.add(video);
        }


        return videoList;
    }


    private void initializeViews() {

        recyclerViewVideo = (RecyclerView) findViewById(R.id.pruebas_video_recycler);
        textureView = (FensterVideoView) findViewById(R.id.play_video_texture);
        //fullScreenMediaPlayerController = (SimpleMediaFensterPlayerController) findViewById(R.id.play_video_controller);
        mediaFensterPlayerController = (MediaFensterPlayerController)findViewById(R.id.play_video_controller);
    }




    private void initializeVideo(String URL) {
        //String URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        Uri uri = Uri.parse(URL); //Declare your url here.
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }


    private void initializeVideoCustom(String video) {
        //"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
     //   textureView.setVideo(video, fullScreenMediaPlayerController.DEFAULT_VIDEO_START);
        textureView.setVideo(video, mediaFensterPlayerController.DEFAULT_VIDEO_START);

        textureView.start();
    }




    @Override
    public void itemRecycleOnClick(int position, VideoViewModel videoViewModel) {

    }

    @Override
    public void onControlsVisibilityChange(boolean value) {
        setSystemUiVisibility(value);
    }


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
                    mediaFensterPlayerController.show();
                }
            }
        });
    }
}
