package net.telesurtv.www.telesur.views.news;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.TelesurApiConstants;

/**
 * Created by Jhordan on 15/07/15.
 */
public class OutstandingFragment extends BaseNewsFragment {


    public static OutstandingFragment newInstance() {
        return new OutstandingFragment();
    }

    @Override
    protected String getSection() {
        return TelesurApiConstants.RSS_OUSTANDING;
    }

    @Override
    protected String getTitleSection() {
        return TelesurApiConstants.SECTION_OUSTANDING;
    }

    @Override
    protected String themeSection() {
        return TelesurApiConstants.THEME_OUSTANDING;
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

                if (spans == 3)
                    if (position == 5)
                        return 3;

                return (position % 5 == 0 ? two_span : one_span);
            }
        });
        return manager;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new RecyclerNewsOutstandingAdapter();
    }


}
