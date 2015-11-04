package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoInterviewFragment extends BaseVideoFragment {

    public static VideoInterviewFragment newInstance() {
        return new VideoInterviewFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.SECTION_VIDEO_INTERVIEW;
    }
}
