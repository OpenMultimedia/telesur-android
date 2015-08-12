package net.telesurtv.www.telesur.views.review;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;
import net.telesurtv.www.telesur.BaseFragmentReview;

/**
 * Created by Jhordan on 15/07/15.
 */
public class ArticleFragment extends BaseFragmentReview {

    public ArticleFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }


    @Override
    protected String getSection() {
        return "/rss/RssOpinion.xml";
    }

    @Override
    protected String getTitleSection() {
        return "Artículo";
    }
}
