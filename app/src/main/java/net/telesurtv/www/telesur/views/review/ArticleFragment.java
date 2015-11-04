package net.telesurtv.www.telesur.views.review;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 15/07/15.
 */
public class ArticleFragment extends BaseReviewFragment {

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.RSS_ARTICLE;
    }

    @Override protected String getTitleSection() {
        return TelesurApiConstants.SECTION_ARTICLE;
    }
}
