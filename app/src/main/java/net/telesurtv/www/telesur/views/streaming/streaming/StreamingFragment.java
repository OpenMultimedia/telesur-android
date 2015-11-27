package net.telesurtv.www.telesur.views.streaming.streaming;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.views.view.video.TelesurPlayerControllerVisibilityListener;
import net.telesurtv.www.telesur.views.view.video.TelesurVideoView;
import net.telesurtv.www.telesur.views.view.video.MediaStreamingPlayerController;

/**
 * Created by Jhordan on 15/07/15.
 */
public class StreamingFragment extends Fragment implements TelesurPlayerControllerVisibilityListener {

    public StreamingFragment() {
    }

    public static StreamingFragment newInstance() {
        return new StreamingFragment();
    }

    private TelesurVideoView textureView;
    private MediaStreamingPlayerController mediaFensterPlayerController;
    ProgressBar progressBarVideo;
    LinearLayout linearLayoutItemBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        textureView = (TelesurVideoView) rootView.findViewById(R.id.play_video_texture);
        mediaFensterPlayerController = (MediaStreamingPlayerController) rootView.findViewById(R.id.play_video_controller);
        progressBarVideo = (ProgressBar)rootView.findViewById(R.id.progress_bar_buffer_video);
        linearLayoutItemBar = (LinearLayout)rootView.findViewById(R.id.progress_bar_item);

        mediaFensterPlayerController.setVisibilityListener(this);
        textureView.setMediaController(mediaFensterPlayerController);

        if (Build.VERSION.SDK_INT >= 21)
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        try{

            //"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
            initializeVideo("http://streaming.openmultimedia.biz:1935/blive/ngrp:balta.stream_all/playlist.m3u8");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        textureView.suspend();
    }



    private void initializeVideo(String video) {
        textureView.setVideo(video, mediaFensterPlayerController.DEFAULT_VIDEO_START);
        textureView.start();
        textureView.setOnPreparedListener((MediaPlayer mediaPlayer) -> mediaPlayer.setOnInfoListener(onInfoToPlayStateListener));
    }


    private final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {

                linearLayoutItemBar.setVisibility(View.GONE);

            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {

                linearLayoutItemBar.setVisibility(View.VISIBLE);
            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
                linearLayoutItemBar.setVisibility(View.GONE);

            }
            return false;
        }
    };



    @Override
    public void onControlsVisibilityChange(boolean value) {
        setSystemUiVisibility(value);

        if (Build.VERSION.SDK_INT >= 21)
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.black));

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

        final View decorView = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(newVis);
        decorView.setOnSystemUiVisibilityChangeListener((int visibility) -> {
            if ((visibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == 0) {
                mediaFensterPlayerController.show();
            }
        });
    }


}
