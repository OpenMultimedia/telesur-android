package net.telesurtv.www.telesur.views.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.telesurtv.www.telesur.BaseFragmentNews;

/**
 * Created by Jhordan on 15/07/15.
 */
public class CultureFragment extends BaseFragmentNews {

    public CultureFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static CultureFragment newInstance() {
        return new CultureFragment();
    }


    @Override
    protected String getSection() {
        return "/rss/RssCultura.xml";
    }

    @Override
    protected String getTitleSection() {
        return "Cultura";
    }
}
