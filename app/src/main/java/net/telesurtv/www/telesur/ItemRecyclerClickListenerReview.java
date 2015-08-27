package net.telesurtv.www.telesur;

import android.view.View;

import net.telesurtv.www.telesur.model.ReviewViewModel;
import net.telesurtv.www.telesur.model.VideoViewModel;

/**
 * Created by Jhordan on 19/06/15.
 */
public interface ItemRecyclerClickListenerReview {

    void itemRecycleOnClickReview(int position, ReviewViewModel reviewViewModel,View imageView);
}
