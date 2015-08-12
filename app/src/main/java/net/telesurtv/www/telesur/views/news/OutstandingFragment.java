package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentOutstandingNews;

/**
 * Created by Jhordan on 15/07/15.
 */
public class OutstandingFragment extends BaseFragmentOutstandingNews {

    public OutstandingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static OutstandingFragment newInstance() {
        return new OutstandingFragment();
    }


    @Override
    protected String getSection() {
        return "/rss/RssPortada.xml";
    }

    @Override
    protected String getTitleSection() {
        return "Destacado";
    }
}
