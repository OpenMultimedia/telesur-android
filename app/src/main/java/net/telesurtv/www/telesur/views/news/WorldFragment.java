package net.telesurtv.www.telesur.views.news;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.otto.Subscribe;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.TelesurApiConstants;
import net.telesurtv.www.telesur.util.OttoBus;

/**
 * Created by Jhordan on 15/07/15.
 */
public class WorldFragment extends BaseNewsFragment {


    public static WorldFragment newInstance() {
        return new WorldFragment();
    }

    String linkNotify = "null";

    @Override public void onStart() {
        super.onStart();

        try {
            OttoBus.getInstance().register(this);} catch (Exception e) {e.printStackTrace();}

    }

    @Override
    public void onStop() {
        super.onStop();
        try {OttoBus.getInstance().unregister(this);} catch (Exception e) {e.printStackTrace();}

    }


    @Override
    protected String getSection() {
        return TelesurApiConstants.RSS_WORLD;
    }

    @Override
    protected String getTitleSection() {
        return TelesurApiConstants.SECTION_WORLD;
    }

    @Override
    protected String themeSection() {
        return TelesurApiConstants.THEME_WORLD;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        final int spans = getResources().getInteger(R.integer.review_columns);
        final int one_span = getResources().getInteger(R.integer.show_span_1);
        final int two_span = getResources().getInteger(R.integer.show_span_2);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), spans);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 5 == 0 ? two_span : one_span);
            }
        });
        return manager;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new RecyclerNewsAdapter();
    }

    @Override
    protected String linkNotification() {
        return linkNotify;
    }

    @Subscribe
    public void subscribeLink(String link) {
        if(!link.equals("null")){
            linkNotify = link;
        }
    }

}
