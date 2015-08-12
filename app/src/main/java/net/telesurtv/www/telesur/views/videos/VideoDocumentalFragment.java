package net.telesurtv.www.telesur.views.videos;

import net.telesurtv.www.telesur.BaseFragmentVideo;
import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoDocumentalFragment extends BaseFragmentVideo {


    public VideoDocumentalFragment() {

    }

    public static VideoDocumentalFragment newInstance() {
        return new VideoDocumentalFragment();
    }



    @Override
    protected String getSection() {
        return "documental";
    }
}
