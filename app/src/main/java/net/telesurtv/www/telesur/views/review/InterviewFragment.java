package net.telesurtv.www.telesur.views.review;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 15/07/15.
 */
public class InterviewFragment extends BaseReviewFragment {

    public static InterviewFragment newInstance() {
        return new InterviewFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.RSS_INTERVIEW;
    }

    @Override protected String getTitleSection() {
        return TelesurApiConstants.SECTION_INTERVIEW;
    }
}
