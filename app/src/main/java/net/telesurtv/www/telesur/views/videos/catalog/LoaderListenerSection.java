package net.telesurtv.www.telesur.views.videos.catalog;

import net.telesurtv.www.telesur.model.VideoMenu;

import java.util.ArrayList;

/**
 * Created by Jhordan on 28/10/15.
 */
public interface LoaderListenerSection {

    void onLoadData(ArrayList<VideoMenu> videoMenuArrayList);
}
