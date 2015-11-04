package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.model.VideoViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public interface VideosMvpView {

    void showVideoList(ArrayList<VideoViewModel> videoViewModelArrayList);

    void showProgress();

    void hideProgress();

    void showConnectionErrorMessage();

    void showUnknownErrorMessage();

    void launchReproductor(int position, VideoViewModel videoViewModel);

}
