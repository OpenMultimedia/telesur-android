package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;
import net.telesurtv.www.telesur.data.EndPoint;

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
        return EndPoint.RSS_SPORTS;
    }

    @Override
    protected String getTitleSection() {
        return EndPoint.SECTION_SPORT;
    }

    @Override
    protected String themeSection() {
        return BaseFragmentNews.THEME_SPORT;
    }
}
