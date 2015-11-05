package net.telesurtv.www.telesur.views.view.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by Jhordan on 27/07/15.
 */
public class TelesurGestureControllerView extends View {
    private GestureDetector gestureDetector;
    private TelesurEventsListener listener;

    public TelesurGestureControllerView(Context context) {
        super(context);
    }

    public TelesurGestureControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TelesurGestureControllerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setClickable(true);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mayNotifyGestureDetector(event);
        return true;
    }

    private void mayNotifyGestureDetector(MotionEvent event){
        gestureDetector.onTouchEvent(event);
    }

    public void setFensterEventsListener(TelesurEventsListener listener){
        gestureDetector = new GestureDetector(getContext(), new TelesurGestureListener(listener, ViewConfiguration.get(getContext())));
    }


}
