package net.telesurtv.www.telesur.views.news;

import net.telesurtv.www.telesur.BaseFragmentNews;
import net.telesurtv.www.telesur.data.EndPoint;

/**
 * Created by Jhordan on 15/07/15.
 */
public class LatamFragment extends BaseFragmentNews {

    public LatamFragment() {
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

    @Override
    protected String themeSection() {
        return BaseFragmentNews.THEME_LATAM;
    }
}
