package net.telesurtv.www.telesur.views.videos.video;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.views.videos.reproductor.VideoReproductorActivity;
import net.telesurtv.www.telesur.views.view.HeaderView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VideoDetailActivity extends NavigatorActivity {

    @Bind(R.id.hv_video_detail)HeaderView hv_video_detail;
    @Bind(R.id.iv_video_detail)ImageView iv_video_detail;
    @Bind(R.id.iv_play_video_detail)ImageView iv_play_video_detail;
    @Bind(R.id.txt_video_detail)TextView txt_video_detail;

    String linkVideo, titleVideo, categoryVideo, type , imageVideo ,urlVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_videos);
        initializeActivity();
        ButterKnife.bind(this);
        initializeActivity();
        intializeFromIntent();
        playVideo();
    }

    private void playVideo(){
        iv_play_video_detail.setOnClickListener((View v) -> {
            Intent intent = new Intent(VideoDetailActivity.this, VideoReproductorActivity.class);
            intent.putExtra("video",urlVideo);
            startActivity(intent);
        });
    }

    private void initializeActivity() {
        setupSubActivityWithTitle();
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDark));
    }

    private void intializeFromIntent() {
        if (getIntent() != null) {

            categoryVideo = getIntent().getStringExtra("video_category");

            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(categoryVideo);


            type = getIntent().getStringExtra("video_share");
            titleVideo = getIntent().getStringExtra("video_title");
            linkVideo = getIntent().getStringExtra("video_link");
            imageVideo = getIntent().getStringExtra("video_image");
            urlVideo = getIntent().getStringExtra("video_url");
            hv_video_detail.updateWith(getIntent().getStringExtra("video_title"), getIntent().getStringExtra("video_data"), "");
            Glide.with(this).load(imageVideo).into(iv_video_detail);
            txt_video_detail.setText(getIntent().getStringExtra("video_description"));

        }
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, categoryVideo + type + titleVideo + "\n" + linkVideo + "\n" + " Enviado desde teleSUR android app.");
        return shareIntent;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.share:
                startActivity(createShareIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
