package net.telesurtv.www.telesur.views.news;

import android.view.View;

import net.telesurtv.www.telesur.model.NewsViewModel;

/**
 * Created by Jhordan on 29/10/15.
 */
public interface Presenter<V> {

    void attachedView(V view);

    void detachView();

    void onItemSelected(int position, NewsViewModel newsViewModel, View imageView);

    void setSection(String section);

    void isRefreshListener(Boolean flag,String section);
}
