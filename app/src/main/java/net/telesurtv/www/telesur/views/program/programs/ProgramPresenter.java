package net.telesurtv.www.telesur.views.program.programs;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.ProgramViewModel;
import net.telesurtv.www.telesur.util.OttoBus;

import java.util.ArrayList;

/**
 * Created by Jhordan on 03/11/15.
 */
public class ProgramPresenter implements Presenter<ProgramMvpView> ,TelesurProgramCallback{

    private ProgramMvpView programMvpView;
    private ProgramInteractor programInteractor;
    private TelesurApiService telesurApiService;

    Boolean mflag = false;
    public ProgramPresenter() {
        telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        programInteractor = new ProgramInteractor(telesurApiService);
    }

    @Override
    public void attachedView(ProgramMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        programMvpView = view;
    }

    @Override
    public void onStart() {
        try {OttoBus.getInstance().register(this);} catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void onStop() {
        try {OttoBus.getInstance().unregister(this);} catch (Exception e) {e.printStackTrace();}
    }


    @Override
    public void setVideoSection(String section, int initquery, int lastQuery) {
        programMvpView.showProgress();
        programInteractor.getProgram(section,initquery,lastQuery,this);

    }

    @Override
    public void getAllSections(int initQuery, int lastQuery) {
        programMvpView.showProgress();
        programInteractor.getAllPrograms(initQuery, lastQuery,this);
    }

    @Override
    public void isRefreshListener(Boolean flag, String section, int initquery, int lastQuery) {
        if(flag){
            if(section.equals("all")){
                programMvpView.showProgressRefresh();
                programInteractor.getAllPrograms(initquery, lastQuery, this);
            }else{
                programMvpView.showProgressRefresh();
                programInteractor.getProgram(section,initquery, lastQuery, this);
            }

        }
        mflag = flag;
    }

    @Override
    public void onItemSelected(int position, ProgramViewModel videoViewModel) {
       programMvpView.launchReproductor(position,videoViewModel);
    }

    @Override
    public void detachView() {
        programMvpView = null;
    }

    @Override
    public void onLoaderPrograms(ArrayList<ProgramViewModel> programViewModels) {
        if(mflag)
            programMvpView.hideProgressRefresh();
        else
            programMvpView.hideProgress();

        programMvpView.showVideoList(programViewModels);
    }

    @Override
    public void onFaliedToGetData(String error) {
        if(error.equals(NetworkErrorException.SERVER_INTERNET_ERROR))
           programMvpView.showConnectionErrorMessage();
        else
            programMvpView.showUnknownErrorMessage();

    }
}
