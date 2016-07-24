package net.telesurtv.www.telesur.views.review;

import android.view.View;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.ReviewViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 27/10/15.
 */
public class ReviewPresenter implements Presenter<ReviewMvpView> ,TelesurReviewCallback {

    private ReviewMvpView reviewMvpView;
    private ReviewInteractor reviewInteractor;
    private TelesurApiService telesurApiService;

    public  ReviewPresenter(){
        telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        reviewInteractor = new ReviewInteractor(telesurApiService);
    }

    Boolean mflag = false;
    @Override
    public void attachedView(ReviewMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        reviewMvpView = view;

    }

    @Override
    public void detachView() {
        reviewMvpView = null;
    }


    @Override
    public void onItemSelected(int position, ReviewViewModel reviewViewModel, View imageView) {
       reviewMvpView.launchDetail(reviewViewModel,imageView);
    }

    @Override
    public void setSection(String section) {
        reviewMvpView.showProgress();
        reviewInteractor.getNewsFromServer(section,this);
    }

    @Override
    public void isRefreshListener(Boolean flag, String section) {

        if(flag){
            reviewMvpView.showProgressRefresh();
            reviewInteractor.getNewsFromServer(section, this);
        }
        mflag = flag;


    }


    @Override
    public void onLoaderNews(ArrayList<ReviewViewModel> viewModelArrayList) {

        if(mflag)
            reviewMvpView.hideProgressRefresh();
        else
            reviewMvpView.hideProgress();


        reviewMvpView.showReviewList(viewModelArrayList);

    }


    @Override
    public void onFaliedToGetData(String error) {

        if(error.equals(NetworkErrorException.SERVER_INTERNET_ERROR))
            reviewMvpView.showConnectionErrorMessage();
        else
            reviewMvpView.showUnknownErrorMessage();

    }
}
