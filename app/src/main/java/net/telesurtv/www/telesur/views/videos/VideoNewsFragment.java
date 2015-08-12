package net.telesurtv.www.telesur.views.videos;

import net.telesurtv.www.telesur.BaseFragmentVideo;
import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 28/07/15.
 */
public class VideoNewsFragment extends BaseFragmentVideo {


    public VideoNewsFragment() {

    }


    public static VideoNewsFragment newInstance() {
        return new VideoNewsFragment();
    }


    @Override
    protected String getSection() {
        return "noticia";
    }



           
}


