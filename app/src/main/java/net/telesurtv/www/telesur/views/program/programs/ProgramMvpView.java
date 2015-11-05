package net.telesurtv.www.telesur.views.program.programs;

import net.telesurtv.www.telesur.model.ProgramViewModel;

import java.util.ArrayList;

/**
 * Created by Jhordan on 03/11/15.
 */
public interface ProgramMvpView {

    void showVideoList(ArrayList<ProgramViewModel> programViewModels);

    void showProgress();

    void hideProgress();

    void showConnectionErrorMessage();

    void showUnknownErrorMessage();

    void launchReproductor(int position, ProgramViewModel programViewModel);
}
