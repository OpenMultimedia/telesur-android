package net.telesurtv.www.telesur.views.review;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 15/07/15.
 */
public class BlogFragment extends BaseReviewFragment{

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.RSS_BLOG;
    }

    @Override protected String getTitleSection() {
        return TelesurApiConstants.SECTION_BLOG;
    }
}
