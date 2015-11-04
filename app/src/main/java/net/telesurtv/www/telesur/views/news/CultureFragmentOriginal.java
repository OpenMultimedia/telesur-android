package net.telesurtv.www.telesur.views.news;

import android.support.v7.widget.RecyclerView;

import com.squareup.otto.Subscribe;

import net.telesurtv.www.telesur.data.TelesurApiConstants;
import net.telesurtv.www.telesur.util.OttoBus;

/**
 * Created by Jhordan on 15/07/15.
 */
public class CultureFragmentOriginal extends BaseNewsFragment {

    public CultureFragmentOriginal() {
    }

    public static CultureFragmentOriginal newInstance() {
        return new CultureFragmentOriginal();
    }
    String linkNotify = "null";
    int i = 0;

    @Override
    public void onStart() {
        super.onStart();


        try {
            OttoBus.getInstance().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        try {

            OttoBus.getInstance().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected String getSection() {
        return TelesurApiConstants.RSS_CULTURE;
    }

    @Override
    protected String getTitleSection() {
        return TelesurApiConstants.SECTION_CULTURE;
    }

    @Override
    protected String themeSection() {
        return TelesurApiConstants.THEME_CULTURE;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return null;
    }

    /*@Override
    protected String linkNotification() {
        return linkNotify;
    }*/


    @Subscribe
    public void subscribeLink(String link) {
        if(!link.equals("null")){
            linkNotify = link;
        }
    }
}
