package net.telesurtv.www.telesur.views.streaming;

import net.telesurtv.www.telesur.model.Streaming;

import java.util.List;

/**
 * Created by Jhordan on 04/11/15.
 */
public interface TelesurStreamingCallBack {

    void onLoaderVideos(List<Streaming> program, List<SimpleSectionedRecyclerViewAdapter.Section> sectionList);

    void onFaliedToGetData(String error);
}
