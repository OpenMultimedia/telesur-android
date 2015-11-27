package net.telesurtv.www.telesur.views.videos.reproductor;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.views.view.video.TelesurPlayerControllerVisibilityListener;
import net.telesurtv.www.telesur.views.view.video.TelesurVideoView;
import net.telesurtv.www.telesur.views.view.video.MediaVideoPlayerController;

/**
 * Created by Jhordan on 15/07/15.
 */
public class ReproductorFragment extends Fragment implements TelesurPlayerControllerVisibilityListener {

    public ReproductorFragment() {
    }

    public static ReproductorFragment newInstance() {
        return new ReproductorFragment();
    }

    private TelesurVideoView textureView;
    private MediaVideoPlayerController mediaVideoPlayerController;
    ProgressBar progressBarVideo;
    LinearLayout linearLayoutItemBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reproductor, container, false);

        textureView = (TelesurVideoView) rootView.findViewById(R.id.play_video_texture);
        mediaVideoPlayerController = (MediaVideoPlayerController) rootView.findViewById(R.id.play_video_controller);
        progressBarVideo = (ProgressBar)rootView.findViewById(R.id.progress_bar_buffer_video);
        linearLayoutItemBar = (LinearLayout)rootView.findViewById(R.id.progress_bar_item);

        mediaVideoPlayerController.setVisibilityListener(this);
        textureView.setMediaController(mediaVideoPlayerController);


        if (Build.VERSION.SDK_INT >= 21)
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        textureView.suspend();
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            initializeVideo(getActivity().getIntent().getStringExtra("video"));
        }catch (Exception e){
            e.printStackTrace();
            //iv_error.setVisibility(View.VISIBLE);
            //txt_error.setVisibility(View.VISIBLE);
          //  include_video.setVisibility(View.GONE);
        }

    }

    private void initializeVideo(String video) {
        textureView.setVideo(video, mediaVideoPlayerController.DEFAULT_VIDEO_START);
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
                mediaVideoPlayerController.show();
            }
        });
    }


}
