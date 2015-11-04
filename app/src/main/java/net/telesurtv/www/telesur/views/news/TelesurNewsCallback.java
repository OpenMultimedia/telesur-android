package net.telesurtv.www.telesur.views.news;

import net.telesurtv.www.telesur.model.NewsViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public interface TelesurNewsCallback {

    void onLoaderNews(ArrayList<NewsViewModel> viewModelArrayList);

    void onFaliedToGetData(String error);
}
