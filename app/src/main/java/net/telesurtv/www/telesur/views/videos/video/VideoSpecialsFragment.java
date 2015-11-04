package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoSpecialsFragment extends BaseVideoFragment {

    public static VideoSpecialsFragment newInstance() {
        return new VideoSpecialsFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.SECTION_VIDEO_SPECIAL;
    }
}
