package net.telesurtv.www.telesur.views.news;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.novoda.notils.caster.Views;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.NavigatorActivity;
import net.telesurtv.www.telesur.views.view.HeaderView;
import net.telesurtv.www.telesur.views.view.SubHeaderView;


public class NewsDetailActivity extends NavigatorActivity {

    // private SubHeaderView headerView;
    TextView txtContent, txtDescription, txtTitle, txtAuthor;
    ImageView imageViewNews;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);


        setupSubActivityWithTitle();
        initializeViews();
        intializeFromIntent();


    }


    private void intializeFromIntent() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("news");

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("News");
            }

            txtTitle.setText(Html.fromHtml(bundle.getString("news_title")));
            System.out.println(bundle.getString("news_title"));

            txtContent.setText(Html.fromHtml(bundle.getString("news_content")));
            txtContent.setMovementMethod(LinkMovementMethod.getInstance());


            txtDescription.setText(Html.fromHtml(bundle.getString("news_description")));
//


                txtAuthor.setText(Html.fromHtml(bundle.getString("news_date")));


            Glide.with(this).load(bundle.getString("news_image")).into(imageViewNews);
            // headerView.updateWith(bundle.getString("news_title"), bundle.getString("news_date"), bundle.getString("news_author"));

            collapsingToolbarLayout.setTitle(getIntent().getStringExtra("news_section"));
           collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));
           collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

            // collapsingToolbarLayout.setCollapsedTitleTextAppearance();


        }
    }

    private void initializeViews() {

        txtTitle = (TextView) findViewById(R.id.txt_title_news_detail);
        txtAuthor = (TextView) findViewById(R.id.txt_details_news_detail);
        txtDescription = (TextView) findViewById(R.id.txt_description_news_detail);
        txtContent = (TextView) findViewById(R.id.txt_news_content);


        imageViewNews = (ImageView) findViewById(R.id.image_view_news_detail);
        // headerView = Views.findById(this, R.id.story_header_view);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
    }


}
