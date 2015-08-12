package net.telesurtv.www.telesur.views.videos;

import net.telesurtv.www.telesur.BaseFragmentVideo;
import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoReportFragment extends BaseFragmentVideo {


    public VideoReportFragment() {

    }

    public static VideoReportFragment newInstance() {
        return new VideoReportFragment();
    }


    @Override
    protected String getSection() {
        return "reportaje";
    }


}
