package net.telesurtv.www.telesur.views.videos;

import net.telesurtv.www.telesur.BaseFragmentVideo;
import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoInfografiaFragment extends BaseFragmentVideo {


    public VideoInfografiaFragment() {

    }

    public static VideoInfografiaFragment newInstance() {
        return new VideoInfografiaFragment();
    }


    @Override
    protected String getSection() {
        return "infografia";
    }
}
