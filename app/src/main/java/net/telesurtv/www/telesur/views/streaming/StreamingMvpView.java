package net.telesurtv.www.telesur.views.streaming;

import net.telesurtv.www.telesur.model.Streaming;

import java.util.List;

/**
 * Created by Jhordan on 04/11/15.
 */
public interface StreamingMvpView {

    void showProgramScheduleList(List<Streaming> programm, List<SimpleSectionedRecyclerViewAdapter.Section> sectionList);

    void showProgress();

    void hideProgress();

    void showConnectionErrorMessage();

    void showUnknownErrorMessage();

    void launchCanal(int position, Streaming canal);
}
