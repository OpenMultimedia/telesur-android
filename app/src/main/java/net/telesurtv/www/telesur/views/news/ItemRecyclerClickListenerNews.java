package net.telesurtv.www.telesur.views.news;

import android.view.View;

import net.telesurtv.www.telesur.model.NewsViewModel;

/**
 * Created by Jhordan on 19/06/15.
 */
public interface ItemRecyclerClickListenerNews {

    void itemRecycleOnClickNews(int position, NewsViewModel newsViewModel,View image);
}
