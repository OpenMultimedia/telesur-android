package net.telesurtv.www.telesur.views.videos.catalog;

import android.view.View;

import net.telesurtv.www.telesur.model.VideoMenu;

/**
 * Created by Jhordan on 29/10/15.
 */
public interface CatalogPresenter<V> {

    void attachedView(V view);

    void detachView();

    void onItemSelected(int position, VideoMenu videoMenu, View toolbar);

    void onResume();

}
