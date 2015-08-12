package net.telesurtv.www.telesur.views.videos;

import net.telesurtv.www.telesur.BaseFragmentVideo;
import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoSpecialsFragment extends BaseFragmentVideo {


    public VideoSpecialsFragment() {

    }

    public static VideoSpecialsFragment newInstance() {
        return new VideoSpecialsFragment();
    }


    @Override
    protected String getSection() {
        return "especial-web";
    }
}
