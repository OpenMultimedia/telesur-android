package net.telesurtv.www.telesur.views.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.storage.Preferences;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.util.GlideImageGetter;
import net.telesurtv.www.telesur.util.Theme;


public class NewsDetailActivity extends NavigatorActivity {

    TextView txtContent, txtDescription, txtTitle, txtAuthor;
    ImageView imageViewNews;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Theme theme;
    Bitmap b;
    String linkNews, titleNews, titleSection;
    Drawable d = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Preferences.setNotification(NewsDetailActivity.this, "not_execute");

        if (getIntent() != null) {
            theme = Theme.valueOf(getIntent().getStringExtra("news_themes"));
            setTheme(theme.getStyle());
            if (Build.VERSION.SDK_INT >= 21)
                getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        setupSubActivityWithTitle();
        initializeViews();
        intializeFromIntent();

        if (Build.VERSION.SDK_INT >= 21)
            ViewCompat.setTransitionName(imageViewNews, getString(R.string.transition_image_view));


    }


    private void intializeFromIntent() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("news");

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("News");
            }

            linkNews = bundle.getString(Config.NEWS_LINK);
            titleNews = bundle.getString("news_title");

            Glide.with(this).load(bundle.getString("news_image"))
                    .diskCacheStrategy(DiskCacheStrategy.ALL).override(200, 200).into(imageViewNews);

            txtTitle.setText(Html.fromHtml(titleNews));

            if (bundle.getString("news_date") != null)
                txtAuthor.setText(Html.fromHtml(bundle.getString("news_date")));

            if (bundle.getString("news_description") != null)
                txtDescription.setText(Html.fromHtml(bundle.getString("news_description")));
            else
                txtDescription.setText("teleSUR");


             GlideImageGetter glideImageGetter = null;
            try {

                if (bundle.getString("news_content") != null) {
                    glideImageGetter = new GlideImageGetter(txtContent,getResources(), this);
                    txtContent.setText(Html.fromHtml(bundle.getString("news_content"), glideImageGetter, null));
                    //txtContent.setText(Html.fromHtml(bundle.getString("news_content")));
                    txtContent.setMovementMethod(LinkMovementMethod.getInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            titleSection = getIntent().getStringExtra("news_section");
            collapsingToolbarLayout.setTitle(getIntent().getStringExtra("news_section"));
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));
            collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

            // collapsingToolbarLayout.setCollapsedTitleTextAppearance();


        }
    }


    private Html.ImageGetter getImagesHtml(final Context context) {

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        final Point maxSize = new Point(size.x - 2 * Math.round(getResources().getDimension(R.dimen.image_resize)), 2 * size.y);

        return new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(final String source) {
                Drawable d = null;
                try {

                    Picasso.with(context).load("http://www.telesurtv.net" + source).into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            b = bitmap;
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
                    // Get original size
                    int w = b.getWidth();
                    int h = b.getHeight();
                    // Shrink if big
                    if (w > maxSize.x || h > maxSize.y) {
                        Point newSize = scaleImage(w, h, maxSize);
                        w = newSize.x;
                        h = newSize.y;
                    }
                    // Need to return a drawable
                    d = new BitmapDrawable(context.getResources(), b);
                    d.setBounds(0, 0, w, h);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return d;
            }
        };


    }

    Point scaleImage(int w, int h, Point maxSize) {
        // Which is out of scale the most?
        float ratio = ((float) w) / ((float) maxSize.x);
        // Calculate new size. Maintains aspect ratio.
        int newWidth = (int) ((float) w / ratio);
        int newHeight = (int) ((float) h / ratio);

        return new Point(newWidth, newHeight);
    }


    private void initializeViews() {

        txtTitle = (TextView) findViewById(R.id.txt_title_news_detail);
        txtAuthor = (TextView) findViewById(R.id.txt_details_news_detail);
        txtDescription = (TextView) findViewById(R.id.txt_description_news_detail);
        txtContent = (TextView) findViewById(R.id.txt_news_content);


        imageViewNews = (ImageView) findViewById(R.id.image_view_news_detail);
        // headerView = Views.findById(this, R.id.story_header_view);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ;


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
        shareIntent.putExtra(Intent.EXTRA_TEXT, titleSection + ": " + titleNews + "\n" + linkNews + "\n" + " Enviado desde teleSUR android app.");
        return shareIntent;
    }


}
