package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoNewsFragment extends BaseVideoFragment {

    public static VideoNewsFragment newInstance() {
        return new VideoNewsFragment();
    }

    @Override protected String getSection() {
        return TelesurApiConstants.SECTION_VIDEO_NOTICIA;
    }

}


