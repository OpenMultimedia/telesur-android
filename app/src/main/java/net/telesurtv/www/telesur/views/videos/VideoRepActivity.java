package net.telesurtv.www.telesur.views.videos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novoda.notils.caster.Views;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.views.view.HeaderView;
import net.telesurtv.www.telesur.views.view.video.FensterPlayerControllerVisibilityListener;
import net.telesurtv.www.telesur.views.view.video.FensterVideoView;
import net.telesurtv.www.telesur.views.view.video.MediaFensterPlayerController;
import net.telesurtv.www.telesur.views.view.video.SimpleMediaFensterPlayerController;


public class VideoRepActivity extends NavigatorActivity implements FensterPlayerControllerVisibilityListener {


    HeaderView headerView;
    TextView txtDescription;
    String linkVideo, titleVideo, categoryVideo, type;
    private FensterVideoView textureView;
    private MediaFensterPlayerController mediaFensterPlayerController;
    ProgressBar progressBarLoad;
    LinearLayout linearLayoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep_activity_videos);

        setupSubActivityWithTitle();
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDark));


        initializeViews();
        intializeFromIntent();
        initVideo();
      //  initializeVideoCustom(getIntent().getStringExtra("video_url"));

    }

    private void initializeViews() {
        headerView = Views.findById(this, R.id.video_header_view);
        txtDescription = (TextView) findViewById(R.id.txt_description_video_detail);
        progressBarLoad = (ProgressBar) findViewById(R.id.progress_bar_buffer_video);
        linearLayoutProgress = (LinearLayout) findViewById(R.id.progress_bar_item);
        textureView = (FensterVideoView) findViewById(R.id.play_video_texture);
        mediaFensterPlayerController = (MediaFensterPlayerController) findViewById(R.id.play_video_controller);

       // linearLayoutProgress.setVisibility(View.GONE);
        //  textureView.setVisibility(View.GONE);
        //  mediaFensterPlayerController.setVisibility(View.GONE);
    }


    private void intializeFromIntent() {
        if (getIntent() != null) {

            categoryVideo = getIntent().getStringExtra("video_category");

            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(categoryVideo);

            type = getIntent().getStringExtra("video_share");
            titleVideo = getIntent().getStringExtra("video_title");
            linkVideo = getIntent().getStringExtra("video_link");
            headerView.updateWith(getIntent().getStringExtra("video_title"), getIntent().getStringExtra("video_data"), "");
            txtDescription.setText(getIntent().getStringExtra("video_description"));

        }
    }


    private void initVideo() {
//        fullScreenMediaPlayerController.setVisibilityListener(this);
        mediaFensterPlayerController.setVisibilityListener(this);
        textureView.setMediaController(mediaFensterPlayerController);


        // textureView.setOnPlayStateListener(mediaFensterPlayerController);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initializeVideoCustom(getIntent().getStringExtra("video_url"));
    }

    private void initializeVideoCustom(String video) {
        //"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        //   textureView.setVideo(video, fullScreenMediaPlayerController.DEFAULT_VIDEO_START);
        textureView.setVideo(video, mediaFensterPlayerController.DEFAULT_VIDEO_START);
        textureView.start();


        textureView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.setOnInfoListener(onInfoToPlayStateListener);

            }
        });


    }


    private final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {

                linearLayoutProgress.setVisibility(View.GONE);

            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {

                linearLayoutProgress.setVisibility(View.VISIBLE);
            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {

            }
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
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(final int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == 0) {
                    mediaFensterPlayerController.show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.share:
                startActivity(createShareNewsIntent());
                return true;

        }


        //  ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item.getItemId());


        return super.onOptionsItemSelected(item);
    }


    private Intent createShareNewsIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, categoryVideo + type + titleVideo + "\n" + linkVideo + "\n" + " Enviado desde teleSUR android app.");
        return shareIntent;
    }


    @Override
    public void onControlsVisibilityChange(boolean value) {
        setSystemUiVisibility(value);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
    }
}
