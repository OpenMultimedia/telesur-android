package net.telesurtv.www.telesur.views.view.video;

import android.view.MotionEvent;

/**
 * Created by Jhordan on 27/07/15.
 */
public interface FensterEventsListener {

    void onTap();

    void onHorizontalScroll(MotionEvent event, float delta);

    void onVerticalScroll(MotionEvent event, float delta);

    void onSwipeRight();

    void onSwipeLeft();

    void onSwipeBottom();

    void onSwipeTop();
}
