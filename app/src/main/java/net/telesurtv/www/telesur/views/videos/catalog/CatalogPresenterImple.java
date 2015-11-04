package net.telesurtv.www.telesur.views.videos.catalog;

import android.view.View;

import net.telesurtv.www.telesur.model.VideoMenu;

import java.util.ArrayList;

/**
 * Created by Jhordan on 29/10/15.
 */
public class CatalogPresenterImple implements CatalogPresenter<CatalogMvpView>,LoaderListenerSection {

    private CatalogMvpView catalogMvpView;
    private SectionInteractorImple sectionInteractorImple;

    public  CatalogPresenterImple(){
        sectionInteractorImple = new SectionInteractorImple();
    }

    @Override public void attachedView(CatalogMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        catalogMvpView = view;
    }

    @Override public void onResume() {
        sectionInteractorImple.loadItems(this);
    }

    @Override public void detachView() {
        catalogMvpView = null;
    }

    @Override public void onLoadData(ArrayList<VideoMenu> videoMenuArrayList) {
        catalogMvpView.showCatalogList(videoMenuArrayList);
    }

    @Override public void onItemSelected(int position, VideoMenu videoMenu, View toolbar) {
        catalogMvpView.launchDetail(position,videoMenu,toolbar);
    }

}
