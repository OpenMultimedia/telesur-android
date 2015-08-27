package net.telesurtv.www.telesur.views.news;

import net.telesurtv.www.telesur.BaseFragmentNews;
import net.telesurtv.www.telesur.data.EndPoint;

/**
 * Created by Jhordan on 15/07/15.
 */
public class CultureFragment extends BaseFragmentNews {

    public CultureFragment() {
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

    @Override
    protected String themeSection() {
        return BaseFragmentNews.THEME_CULTURE;
    }
}
