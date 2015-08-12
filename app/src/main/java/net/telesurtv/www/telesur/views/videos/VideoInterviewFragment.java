package net.telesurtv.www.telesur.views.videos;

import net.telesurtv.www.telesur.BaseFragmentVideo;
import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoInterviewFragment extends BaseFragmentVideo {


    public VideoInterviewFragment() {

    }

    public static VideoInterviewFragment newInstance() {
        return new VideoInterviewFragment();
    }



    @Override
    protected String getSection() {
        return "entrevista";
    }
}
