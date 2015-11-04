package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoDocumentalFragment extends BaseVideoFragment {

    public static VideoDocumentalFragment newInstance() {
        return new VideoDocumentalFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.SECTION_VIDEO_DOCUMENTAL;
    }
}
