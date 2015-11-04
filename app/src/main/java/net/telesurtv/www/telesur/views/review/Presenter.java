package net.telesurtv.www.telesur.views.review;

import android.view.View;

import net.telesurtv.www.telesur.model.ReviewViewModel;

/**
 * Created by Jhordan on 27/10/15.
 */
public interface Presenter <V> {

    void attachedView(V view);

    void detachView();

    void onItemSelected(int position, ReviewViewModel reviewViewModel, View imageView);

    void setSection(String section);

    void isRefreshListener(Boolean flag,String section);

}
