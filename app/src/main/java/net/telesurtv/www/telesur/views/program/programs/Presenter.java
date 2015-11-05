package net.telesurtv.www.telesur.views.program.programs;

import net.telesurtv.www.telesur.model.ProgramViewModel;

/**
 * Created by Jhordan on 03/11/15.
 */
public interface Presenter<V> {

    void attachedView(V view);

    void detachView();

    void onStart();

    void onStop();

    void onItemSelected(int position, ProgramViewModel programViewModel);

    void setVideoSection(String section,int initquery ,int lastQuery);

    void getAllSections(int initQuery, int lastQuery);

}
