package net.telesurtv.www.telesur.views.program;

import net.telesurtv.www.telesur.model.ProgramItem;
import net.telesurtv.www.telesur.model.ReviewViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 27/10/15.
 */
public interface TelesurProgramCallback {

    void onLoaderNews(ArrayList<ProgramItem> viewModelArrayList);

    void onFaliedToGetData(String error);
}
