package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.model.VideoViewModel;

/**
 * Created by Jhordan on 29/10/15.
 */
public interface Presenter<V> {

    void attachedView(V view);

    void setVideoSection(String section,int initquery ,int lastQuery);

    void setVideoSection(String section,String generic,String tag,int initquery ,int lastQuery);

    void onItemSelected(int position, VideoViewModel videoViewModel);

    void isRefreshListener(Boolean flag,String section,int initquery ,int lastQuery);

    void detachView();
}
