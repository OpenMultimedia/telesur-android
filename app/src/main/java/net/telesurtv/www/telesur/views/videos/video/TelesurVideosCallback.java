package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.model.VideoViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public interface TelesurVideosCallback {

    void onLoaderVideos(ArrayList<VideoViewModel> viewModelArrayList);

    void onFaliedToGetData(String error);
}
