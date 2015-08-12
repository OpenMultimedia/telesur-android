package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;

/**
 * Created by Jhordan on 15/07/15.
 */
public class SportFragment extends BaseFragmentNews {

    public SportFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static SportFragment newInstance() {
        return new SportFragment();
    }


    @Override
    protected String getSection() {
        return "/rss/RssDeporte.xml";
    }

    @Override
    protected String getTitleSection() {
        return "Deportes";
    }
}
