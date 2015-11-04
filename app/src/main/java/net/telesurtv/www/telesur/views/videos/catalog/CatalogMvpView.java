package net.telesurtv.www.telesur.views.videos.catalog;

import android.view.View;

import net.telesurtv.www.telesur.model.VideoMenu;

import java.util.ArrayList;

/**
 * Created by Jhordan on 28/10/15.
 */
public interface CatalogMvpView {

    void showCatalogList(ArrayList<VideoMenu> videoMenuArrayList);

    void launchDetail(int position, VideoMenu videoMenu, View toolbar);
}
