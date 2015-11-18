package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.VideoViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public class VideoPresenter implements Presenter<VideosMvpView>,TelesurVideosCallback {
    private VideosMvpView videosMvpView;
    private VideoInteractor videoInteractor;
    private TelesurApiService telesurApiService;

    Boolean mflag = false;
    public VideoPresenter(){
        telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        videoInteractor = new VideoInteractor(telesurApiService);
    }

    @Override
    public void attachedView(VideosMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        videosMvpView = view;
    }

    @Override
    public void setVideoSection(String section,int initquery ,int lastQuery) {
        videosMvpView.showProgress();
        videoInteractor.getVideosFromServer(section,initquery,lastQuery,this);
    }

    @Override
    public void setVideoSection(String section, String generic, String tag, int initquery, int lastQuery) {
        // do nothing
    }


    @Override
    public void onItemSelected(int position, VideoViewModel videoViewModel) {
        videosMvpView.launchReproductor(position,videoViewModel);
    }

    @Override
    public void isRefreshListener(Boolean flag, String section,int initquery ,int lastQuery) {
        if(flag){
            videosMvpView.showProgressRefresh();
            videoInteractor.getVideosFromServer(section,initquery,lastQuery,this);
        }
        mflag = flag;
    }

    @Override
    public void detachView() {
        videosMvpView = null;
    }

    @Override
    public void onLoaderVideos(ArrayList<VideoViewModel> viewModelArrayList) {

        if(mflag)
           videosMvpView.hideProgressRefresh();
        else
           videosMvpView.hideProgress();

        videosMvpView.showVideoList(viewModelArrayList);
    }



    @Override
    public void onFaliedToGetData(String error) {

        if(error.equals(NetworkErrorException.SERVER_INTERNET_ERROR))
            videosMvpView.showConnectionErrorMessage();
        else
            videosMvpView.showUnknownErrorMessage();

    }
}
