package net.telesurtv.www.telesur.views.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import net.telesurtv.www.telesur.BaseFragmentReview;

/**
 * Created by Jhordan on 15/07/15.
 */
public class InterviewFragment extends BaseFragmentReview {

    public InterviewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static InterviewFragment newInstance() {
        return new InterviewFragment();
    }


    @Override
    protected String getSection() {
        return "/rss/RssInterviews.xml";
    }

    @Override
    protected String getTitleSection() {
        return "Entrevista";
    }
}
