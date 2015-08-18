package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;
import net.telesurtv.www.telesur.data.EndPoint;

/**
 * Created by Jhordan on 15/07/15.
 */
public class CultureFragment extends BaseFragmentNews {

    public CultureFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static CultureFragment newInstance() {
        return new CultureFragment();
    }

    @Override
    protected String getSection() {
        return EndPoint.RSS_CULTURE;
    }

    @Override
    protected String getTitleSection() {
        return EndPoint.SECTION_CULTURE;
    }
}
