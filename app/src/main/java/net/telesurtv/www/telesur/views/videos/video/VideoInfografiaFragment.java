package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoInfografiaFragment extends BaseVideoFragment {

    public static VideoInfografiaFragment newInstance() {
        return new VideoInfografiaFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.SECTION_VIDEO_INFOGRAFIA;
    }
}
