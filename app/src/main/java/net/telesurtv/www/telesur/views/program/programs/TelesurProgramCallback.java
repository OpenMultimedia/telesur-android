package net.telesurtv.www.telesur.views.program.programs;

import net.telesurtv.www.telesur.model.ProgramViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 03/11/15.
 */
public interface TelesurProgramCallback {

    void onLoaderPrograms(ArrayList<ProgramViewModel> programViewModels);

    void onFaliedToGetData(String error);
}
