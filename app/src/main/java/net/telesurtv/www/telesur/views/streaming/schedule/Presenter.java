package net.telesurtv.www.telesur.views.streaming.schedule;

import net.telesurtv.www.telesur.model.Streaming;

/**
 * Created by Jhordan on 04/11/15.
 */
public interface Presenter <V> {

    void attachedView(V view);

    void detachView();

    void onItemSelected(int position, Streaming streaming);

}
