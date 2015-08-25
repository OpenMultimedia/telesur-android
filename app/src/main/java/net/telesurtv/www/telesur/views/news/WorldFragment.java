package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;
import net.telesurtv.www.telesur.data.EndPoint;

/**
 * Created by Jhordan on 15/07/15.
 */
public class WorldFragment extends BaseFragmentNews {

    public WorldFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static WorldFragment newInstance() {
        return new WorldFragment();
    }


    @Override
    protected String getSection() {
        return EndPoint.RSS_WORLD;
    }

    @Override
    protected String getTitleSection() {
        return EndPoint.SECTION_WORLD ;
    }

    @Override
    protected String themeSection() {
        return BaseFragmentNews.THEME_WORLD;
    }
}
