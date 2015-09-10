package net.telesurtv.www.telesur.views.streaming;

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
import net.telesurtv.www.telesur.views.view.video.FensterPlayerControllerVisibilityListener;
import net.telesurtv.www.telesur.views.view.video.FensterVideoView;
import net.telesurtv.www.telesur.views.view.video.MediaFensterPlayerController;

/**
 * Created by Jhordan on 15/07/15.
 */
public class FragmentSchedule extends Fragment implements FensterPlayerControllerVisibilityListener {

    public FragmentSchedule() {
    }

    public static FragmentSchedule newInstance() {
        return new FragmentSchedule();
    }

    private FensterVideoView textureView;
    private MediaFensterPlayerController mediaFensterPlayerController;
    ProgressBar progressBarVideo;
    LinearLayout linearLayoutItemBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        textureView = (FensterVideoView) rootView.findViewById(R.id.play_video_texture);
        mediaFensterPlayerController = (MediaFensterPlayerController) rootView.findViewById(R.id.play_video_controller);
        progressBarVideo = (ProgressBar)rootView.findViewById(R.id.progress_bar_buffer_video);
        linearLayoutItemBar = (LinearLayout)rootView.findViewById(R.id.progress_bar_item);

        mediaFensterPlayerController.setVisibilityListener(this);
        textureView.setMediaController(mediaFensterPlayerController);

        textureView.setVideo(Uri.parse("http://streaming.openmultimedia.biz:1935/blive/ngrp:balta.stream_all/playlist.m3u8"), mediaFensterPlayerController.DEFAULT_VIDEO_START);
        textureView.start();



        textureView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

               mediaPlayer.setOnInfoListener(onInfoToPlayStateListener);

            }
        });

        if (Build.VERSION.SDK_INT >= 21)
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        textureView.suspend();
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
