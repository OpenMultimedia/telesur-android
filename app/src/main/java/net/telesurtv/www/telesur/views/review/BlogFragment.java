package net.telesurtv.www.telesur.views.review;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentReview;

/**
 * Created by Jhordan on 15/07/15.
 */
public class BlogFragment extends BaseFragmentReview{

    public BlogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }


    @Override
    protected String getSection() {
        return "/rss/RssBlogs.xml";
    }

    @Override
    protected String getTitleSection() {
        return "Blog";
    }
}
