package net.telesurtv.www.telesur.views.videos.video;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.util.Theme;
import net.telesurtv.www.telesur.views.videos.reproductor.VideoReproductorActivity;
import net.telesurtv.www.telesur.views.videos.tags.TagListActivity;
import net.telesurtv.www.telesur.views.view.HeaderView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VideoDetailActivity extends NavigatorActivity {

    @Bind(R.id.hv_video_detail)
    HeaderView hv_video_detail;
    @Bind(R.id.iv_video_detail)
    ImageView iv_video_detail;
    @Bind(R.id.iv_play_video_detail)
    ImageView iv_play_video_detail;
    @Bind(R.id.txt_video_detail)
    TextView txt_video_detail;
    @Bind(R.id.txt_video_c_detail)
    TextView txt_video_c_detail;
    @Bind(R.id.tag_corresponsal)
    TextView txt_tag_corresponsal;
    @Bind(R.id.tag_tema)
    TextView txt_tag_tema;
    @Bind(R.id.tag_country)
    TextView txt_tag_country;
    @Bind(R.id.tag_category)
    TextView txt_tag_category;
    @Bind(R.id.tag_corresponsal_title)
    TextView txt_tag_corresponsal_title;

    private String linkVideo, titleVideo, categoryVideo, type, imageVideo, urlVideo,nameCorres,
            slugCorres,slugTopic, categorySlugVideo, slugCorresCountry,section;

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

    private void playVideo() {
        iv_play_video_detail.setOnClickListener((View v) -> {
            Intent intent = new Intent(VideoDetailActivity.this, VideoReproductorActivity.class);
            intent.putExtra("video", urlVideo);
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
            nameCorres = getIntent().getStringExtra("video_corres_name");
            slugCorres = getIntent().getStringExtra("video_corres_slug");
            slugTopic = getIntent().getStringExtra("video_topic_slug");
            categorySlugVideo = getIntent().getStringExtra("video_category_slug");
            slugCorresCountry = getIntent().getStringExtra("video_corres_country");
            section= getIntent().getStringExtra("video_section_origin");
            hv_video_detail.updateWith(getIntent().getStringExtra("video_title"), getIntent().getStringExtra("video_data"), "");
            Glide.with(this).load(imageVideo).into(iv_video_detail);

            //  if (getIntent().getStringExtra("video_description").equals(""))
            txt_video_detail.setText(getIntent().getStringExtra("video_description"));
            //  else
            //    txt_video_detail.setText(getIntent().getStringExtra("video_program_descripcion"));



            if(!nameCorres.equals("")){
                txt_tag_corresponsal_title.setVisibility(View.VISIBLE);
                txt_video_c_detail.setVisibility(View.VISIBLE);
                txt_tag_corresponsal.setVisibility(View.VISIBLE);
                txt_video_c_detail.setText(nameCorres);
                txt_tag_corresponsal.setText(slugCorres);

            }else{
                slugCorres = "no_es_nulo";
            }


            if(!slugCorresCountry.equals("")){
                txt_tag_country.setVisibility(View.VISIBLE);
                txt_tag_country.setText(slugCorresCountry);
            }else{
                slugCorresCountry = "no_es_nulo";

            }

            if(!slugTopic.equals("")){
                txt_tag_tema.setVisibility(View.VISIBLE);
                txt_tag_tema.setText(slugTopic);
            }else{
                slugTopic = "no_es_nulo";

            }

            if(!categorySlugVideo.equals("")){
                txt_tag_category.setVisibility(View.VISIBLE);
                txt_tag_category.setText(categorySlugVideo);}
            else{
                categorySlugVideo= "no_es_nulo";

            }



            txt_tag_corresponsal.setOnClickListener((View view) ->{
                Toast.makeText(VideoDetailActivity.this,slugCorres,Toast.LENGTH_SHORT).show();
                lauchTag(slugCorres,"Corresponsales");
            });

            txt_tag_tema.setOnClickListener((View view) ->{
                Toast.makeText(VideoDetailActivity.this,slugTopic,Toast.LENGTH_SHORT).show();
                lauchTag(slugTopic,"Temas");
            });

            txt_tag_country.setOnClickListener((View view) ->{
                Toast.makeText(VideoDetailActivity.this,slugCorresCountry,Toast.LENGTH_SHORT).show();
                lauchTag(slugCorresCountry,"Países");
            });

            txt_tag_category.setOnClickListener((View view) ->{
                Toast.makeText(VideoDetailActivity.this,categorySlugVideo,Toast.LENGTH_SHORT).show();
                lauchTag(categorySlugVideo,"Categorias");
            });



        }
    }


    private void lauchTag(String generic,String tag){
        Theme theme = Theme.valueOf("news");
        Intent intent = new Intent(VideoDetailActivity.this, TagListActivity.class);
        intent.putExtra("video_theme", theme.getStyle());
        intent.putExtra("video_section",section);
        intent.putExtra("video_tag",tag);
        intent.putExtra("video_generico",generic);
        startActivity(intent);
    }


    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, categoryVideo + type + titleVideo + "\n" + linkVideo + "\n" + " Enviado desde teleSUR android app.");
        return shareIntent;
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
                startActivity(createShareIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
