package net.telesurtv.www.telesur.views.program;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.model.ProgramItem;
import net.telesurtv.www.telesur.util.OttoBus;

import java.util.ArrayList;

/**
 * Created by Jhordan on 03/11/15.
 */
public class ProgramPresenter implements Presenter<ProgramMvpView>, TelesurProgramCallback {

    private ProgramMvpView programMvpView;
    private ProgramInteractor programInteractor;
    private TelesurApiService telesurApiService;

    public ProgramPresenter() {
        telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        programInteractor = new ProgramInteractor(telesurApiService);
    }


    @Override
    public void onResume() {
        programInteractor.getNewsFromServer(this);

    }

    @Override public void onStart() {
        try {OttoBus.getInstance().register(this);} catch (Exception e) {e.printStackTrace();}
    }

    @Override public void onStop() {
        try {OttoBus.getInstance().unregister(this);} catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void onItemSelected(String programSlug) {
        programMvpView.OttoPost(programSlug);
    }

    @Override
    public void attachedView(ProgramMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        programMvpView = view;
    }

    @Override
    public void detachView() {
        programMvpView = null;

    }


    @Override
    public void onLoaderNews(ArrayList<ProgramItem> viewModelArrayList) {

        programMvpView.showProgramList(viewModelArrayList);

        for(int i = 0; i< viewModelArrayList.size();i++)
             System.out.println(viewModelArrayList.get(i).getName());

    }

    @Override
    public void onFaliedToGetData(String error) {

    }
}
