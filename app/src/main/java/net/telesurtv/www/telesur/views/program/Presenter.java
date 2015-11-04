package net.telesurtv.www.telesur.views.program;

/**
 * Created by Jhordan on 03/11/15.
 */
public interface Presenter <V> {

    void attachedView(V view);

    void detachView();

    void onResume();

    void onStart();

    void onStop();

    void onItemSelected(String programSlug);
}
