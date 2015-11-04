package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoReportFragment extends BaseVideoFragment {

    public static VideoReportFragment newInstance() {
        return new VideoReportFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.SECTION_VIDEO_REPORT;
    }

}
