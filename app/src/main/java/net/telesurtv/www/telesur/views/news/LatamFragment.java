package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;
import net.telesurtv.www.telesur.data.EndPoint;

/**
 * Created by Jhordan on 15/07/15.
 */
public class LatamFragment extends BaseFragmentNews {

    public LatamFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static LatamFragment newInstance() {
        return new LatamFragment();
    }

    @Override
    protected String getSection() {
        return EndPoint.RSS_LATAM;
    }

    @Override
    protected String getTitleSection() {
        return EndPoint.SECTION_LATAM;
    }
}
