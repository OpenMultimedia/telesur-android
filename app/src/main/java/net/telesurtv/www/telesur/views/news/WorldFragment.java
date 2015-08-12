package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;

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
        return "/rss/RssMundo.xml";
    }

    @Override
    protected String getTitleSection() {
        return "Mundo";
    }
}
