package net.telesurtv.www.telesur.views.news;

import android.content.Context;
import android.view.View;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.NewsViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public class NewsPresenter implements Presenter<NewsMVPView> ,TelesurNewsCallback {

    private NewsMVPView newsMVPView;
    private NewsInteractor newsInteractor;
    private TelesurApiService telesurApiService;

    Boolean mflag = false;

    public NewsPresenter(Context context){
        telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        newsInteractor = new NewsInteractor(telesurApiService,context);
    }

    @Override
    public void attachedView(NewsMVPView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        newsMVPView = view;
    }

    @Override
    public void setSection(String section) {
        newsMVPView.showProgress();
        newsInteractor.getNewsFromServer(section,this);
    }

    @Override
    public void isRefreshListener(Boolean flag, String section) {
        if(flag){
            newsMVPView.showProgressRefresh();
            newsInteractor.getNewsFromServer(section, this);
        }
        mflag = flag;
    }

    @Override
    public void onItemSelected(int position, NewsViewModel newsViewModel, View imageView) {
        newsMVPView.launchDetail(position,newsViewModel,imageView);
    }


    @Override
    public void onLoaderNews(ArrayList<NewsViewModel> viewModelArrayList) {

        if(mflag)
           newsMVPView.hideProgressRefresh();
        else
            newsMVPView.hideProgress();

        newsMVPView.showReviewList(viewModelArrayList);
    }

    @Override
    public void onFaliedToGetData(String error) {
        if(error.equals(NetworkErrorException.SERVER_INTERNET_ERROR))
            newsMVPView.showConnectionErrorMessage();
        else
            newsMVPView.showUnknownErrorMessage();
    }

    @Override
    public void detachView() {
        newsMVPView = null;
    }

}
