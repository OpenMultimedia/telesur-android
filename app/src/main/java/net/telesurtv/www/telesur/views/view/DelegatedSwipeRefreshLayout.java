package net.telesurtv.www.telesur.views.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by Jhordan on 28/07/15.
 */
public class DelegatedSwipeRefreshLayout extends SwipeRefreshLayout {

    private ViewDelegate viewDelegate;

    public DelegatedSwipeRefreshLayout(Context context) {
        super(context);
    }

    public DelegatedSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setViewDelegate(ViewDelegate viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    @Override
    public boolean canChildScrollUp() {
        if (viewDelegate != null)
            return viewDelegate.isReadyForPull();

        return super.canChildScrollUp();
    }
}
