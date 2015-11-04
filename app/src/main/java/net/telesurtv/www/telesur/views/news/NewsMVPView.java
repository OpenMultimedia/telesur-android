package net.telesurtv.www.telesur.views.news;

import android.view.View;

import net.telesurtv.www.telesur.model.NewsViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public interface NewsMVPView {

    void showReviewList(ArrayList<NewsViewModel> newsViewModels);

    void showProgress();

    void hideProgress();

    void showProgressRefresh();

    void hideProgressRefresh();

    void showConnectionErrorMessage();

    void showUnknownErrorMessage();

    void launchDetail(int position, NewsViewModel newsViewModel, View imageView);
}
