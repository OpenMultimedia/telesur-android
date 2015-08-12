package net.telesurtv.www.telesur.views.review;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.novoda.notils.caster.Views;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.views.view.HeaderView;


public class ReviewDetailActivity extends NavigatorActivity {


    ImageView imageViewReview;
    private HeaderView headerView;
    private TextView txtContent, txtDescription;

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


    }


    private void initializeView() {
        txtContent = (TextView) findViewById(R.id.txt_review_content);
        txtDescription = (TextView) findViewById(R.id.txt_description_review_detail);
        imageViewReview = (ImageView) findViewById(R.id.image_view_review_detail);
    }

    private void intializeFromIntent() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("review_news");

            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(getIntent().getStringExtra("review_title"));

            headerView.updateWith(bundle.getString("review_title"), bundle.getString("review_date"), bundle.getString("review_author"));
            txtContent.setText(Html.fromHtml(bundle.getString("review_content")));
            txtContent.setMovementMethod(LinkMovementMethod.getInstance());
            txtDescription.setText(Html.fromHtml(bundle.getString("review_description")));
            Glide.with(this).load(bundle.getString("review_image")).into(imageViewReview);
        }
    }


}
