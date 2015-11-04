package net.telesurtv.www.telesur.views.review;

import net.telesurtv.www.telesur.model.ReviewViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 27/10/15.
 */
public interface TelesurReviewCallback {

    void onLoaderNews(ArrayList<ReviewViewModel> viewModelArrayList);

    void onFaliedToGetData(String error);
}
