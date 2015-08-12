package net.telesurtv.www.telesur;

import net.telesurtv.www.telesur.model.ReviewViewModel;
import net.telesurtv.www.telesur.model.VideoViewModel;

/**
 * Created by Jhordan on 19/06/15.
 */
public interface ItemRecyclerClickListener {

    void itemRecycleOnClick(int position, VideoViewModel videoViewModel);

}
