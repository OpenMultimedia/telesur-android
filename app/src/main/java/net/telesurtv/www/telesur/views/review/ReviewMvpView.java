package net.telesurtv.www.telesur.views.review;

import android.view.View;

import net.telesurtv.www.telesur.model.ReviewViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 27/10/15.
 */
public interface ReviewMvpView {

    void showReviewList(ArrayList<ReviewViewModel> reviewViewModelArrayList);

    void showProgress();

    void hideProgress();

    void showProgressRefresh();

    void hideProgressRefresh();

    void showConnectionErrorMessage();

    void showUnknownErrorMessage();

    void launchDetail(ReviewViewModel reviewViewModel,View imageView);

}
