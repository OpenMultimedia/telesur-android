package net.telesurtv.www.telesur.views.videos.tags;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.views.videos.video.Presenter;
import net.telesurtv.www.telesur.views.videos.video.TelesurVideosCallback;
import net.telesurtv.www.telesur.views.videos.video.VideosMvpView;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public class TagPresenter implements Presenter<VideosMvpView>,TelesurVideosCallback {
    private VideosMvpView videosMvpView;
    private TagInteractor videoInteractor;
    private TelesurApiService telesurApiService;

    public TagPresenter(){
        telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        videoInteractor = new TagInteractor(telesurApiService);
    }

    @Override
    public void attachedView(VideosMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        videosMvpView = view;
    }

    @Override
    public void setVideoSection(String section,int initquery ,int lastQuery) {

        //videoInteractor.getVideosFromServer(section,initquery,lastQuery,this);
    }

    @Override
    public void setVideoSection(String section, String generic, String tag, int initquery, int lastQuery) {

       switch (tag){
            case "Corresponsales":
                videosMvpView.showProgress();
                videoInteractor.getTagCorresponsal(section,generic,initquery,lastQuery,this);

                break;

            case "Temas":
                videosMvpView.showProgress();
                videoInteractor.getTagTopic(section,generic,initquery,lastQuery,this);
                break;

            case "Países":
                videosMvpView.showProgress();
                videoInteractor.getTagCountry(section, generic, initquery, lastQuery, this);
                System.out.println("se ejecuto paises");
                break;

            case "Categorias":
                videosMvpView.showProgress();
                videoInteractor.getTagCategory(section,generic,initquery,lastQuery,this);
                break;
        }

    }



    @Override
    public void onItemSelected(int position, VideoViewModel videoViewModel) {
        videosMvpView.launchReproductor(position,videoViewModel);
    }

    @Override
    public void isRefreshListener(Boolean flag, String section, int initquery, int lastQuery) {

    }

    @Override
    public void detachView() {
        videosMvpView = null;
    }

    @Override
    public void onLoaderVideos(ArrayList<VideoViewModel> viewModelArrayList) {
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
