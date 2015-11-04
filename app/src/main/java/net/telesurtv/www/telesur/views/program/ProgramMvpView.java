package net.telesurtv.www.telesur.views.program;

import net.telesurtv.www.telesur.model.ProgramItem;

import java.util.ArrayList;

/**
 * Created by Jhordan on 03/11/15.
 */
public interface ProgramMvpView {

    void showProgramList(ArrayList<ProgramItem> programItems);

    void OttoPost(String programSlug);




}
