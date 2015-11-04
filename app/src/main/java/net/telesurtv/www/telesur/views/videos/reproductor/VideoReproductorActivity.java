package net.telesurtv.www.telesur.views.videos.reproductor;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.views.view.video.FensterPlayerControllerVisibilityListener;
import net.telesurtv.www.telesur.views.view.video.FensterVideoView;
import net.telesurtv.www.telesur.views.view.video.MediaFensterPlayerController;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VideoReproductorActivity extends AppCompatActivity implements FensterPlayerControllerVisibilityListener {

    @Bind(R.id.progress_bar_item)     LinearLayout linearLayoutProgress;
    @Bind(R.id.play_video_texture)    FensterVideoView textureView;
    @Bind(R.id.play_video_controller) MediaFensterPlayerController mediaFensterPlayerController;
    @Bind(R.id.iv_error)              ImageView iv_error;
    @Bind(R.id.txt_error)             TextView txt_error;
    @Bind(R.id.include_video)         View include_video;

    @Override protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_reproductor);
        ButterKnife.bind(this);
        initializeViews();
    }

    private void initializeViews() {
        mediaFensterPlayerController.setVisibilityListener(this);
        textureView.setMediaController(mediaFensterPlayerController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            initializeVideo(getIntent().getStringExtra("video"));
        }catch (Exception e){
            e.printStackTrace();
            iv_error.setVisibility(View.VISIBLE);
            txt_error.setVisibility(View.VISIBLE);
            include_video.setVisibility(View.GONE);
        }

    }

    private void initializeVideo(String video) {
        textureView.setVideo(video, mediaFensterPlayerController.DEFAULT_VIDEO_START);
        textureView.start();
        textureView.setOnPreparedListener((MediaPlayer mediaPlayer) -> mediaPlayer.setOnInfoListener(onInfoToPlayStateListener));
    }

    private final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {
        @Override public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what)
                linearLayoutProgress.setVisibility(View.GONE);
            if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what)
                linearLayoutProgress.setVisibility(View.VISIBLE);
            if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what)
                linearLayoutProgress.setVisibility(View.GONE);
            return false;
        }
    };

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

        decorView.setOnSystemUiVisibilityChangeListener((int visibility) -> {
            if ((visibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == 0) {
                mediaFensterPlayerController.show();
            }
        });

    }

    @Override
    public void onControlsVisibilityChange(boolean value) {
        setSystemUiVisibility(value);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
    }
}
