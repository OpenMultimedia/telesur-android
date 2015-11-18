package net.telesurtv.www.telesur.views.review;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.novoda.notils.caster.Views;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.util.PicassoImageGetter;
import net.telesurtv.www.telesur.views.view.HeaderView;


public class ReviewDetailActivity extends NavigatorActivity {


    ImageView imageViewReview;
    private HeaderView headerView;
    private TextView txtContent, txtDescription;
    String linkNews, titleNews, titleReview;
    Bitmap b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDark));


        headerView = Views.findById(this, R.id.story_header_view);
        setupSubActivityWithTitle();
        initializeView();
        intializeFromIntent();

        if (Build.VERSION.SDK_INT >= 21)
            ViewCompat.setTransitionName(imageViewReview, getString(R.string.transition_image_view));

    }


    private void initializeView() {
        txtContent = (TextView) findViewById(R.id.txt_review_content);
        txtDescription = (TextView) findViewById(R.id.txt_description_review_detail);
        imageViewReview = (ImageView) findViewById(R.id.image_view_review_detail);
    }

    private void intializeFromIntent() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("review_news");

            linkNews = bundle.getString("review_link");
            titleNews = bundle.getString("review_title");
            titleReview = getIntent().getStringExtra("review_title");

            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(getIntent().getStringExtra("review_title"));

            headerView.updateWith(bundle.getString("review_title"), bundle.getString("review_date"), bundle.getString("review_author"));


            PicassoImageGetter picassoImageGetter = null;
            try {
                picassoImageGetter = new PicassoImageGetter(txtContent,getResources(), Picasso.with(this));
                txtContent.setText(Html.fromHtml(bundle.getString("review_content"), picassoImageGetter, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
           // txtContent.setText(Html.fromHtml(bundle.getString(), getImagesHtml(this), null));


            txtContent.setMovementMethod(LinkMovementMethod.getInstance());
            txtDescription.setText(Html.fromHtml(bundle.getString("review_description")));
            Glide.with(this).load(bundle.getString("review_image")).into(imageViewReview);
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
        shareIntent.putExtra(Intent.EXTRA_TEXT, titleReview + ": " + titleNews + "\n" + linkNews + "\n" + " Enviado desde teleSUR android app.");
        return shareIntent;
    }


}
