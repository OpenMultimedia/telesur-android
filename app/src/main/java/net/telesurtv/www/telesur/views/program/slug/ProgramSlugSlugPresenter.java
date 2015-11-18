package net.telesurtv.www.telesur.views.program.slug;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.ProgramItem;
import net.telesurtv.www.telesur.util.OttoBus;

import java.util.ArrayList;

/**
 * Created by Jhordan on 03/11/15.
 */
public class ProgramSlugSlugPresenter implements Presenter<ProgramSlugMvpView>, TelesurProgramSlugCallback {

    private ProgramSlugMvpView programSlugMvpView;
    private ProgramSlugInteractor programSlugInteractor;
    private TelesurApiService telesurApiService;

    public ProgramSlugSlugPresenter() {
        telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        programSlugInteractor = new ProgramSlugInteractor(telesurApiService);
    }


    @Override public void onStart() {
        try {OttoBus.getInstance().register(this);} catch (Exception e) {e.printStackTrace();}
    }

    @Override public void onStop() {
        try {OttoBus.getInstance().unregister(this);} catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void onItemSelected(String programSlug, int position) {
        programSlugMvpView.OttoPost(programSlug,position);
    }

    @Override
    public void attachedView(ProgramSlugMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        programSlugMvpView = view;
        programSlugInteractor.getSlugFromServer(this);
    }

    @Override
    public void detachView() {
        programSlugMvpView = null;

    }


    @Override
    public void onLoaderNews(ArrayList<ProgramItem> viewModelArrayList) {

        programSlugMvpView.showProgramList(viewModelArrayList);


    }

    @Override
    public void onFaliedToGetData(String error) {

        if(error.equals(NetworkErrorException.SERVER_INTERNET_ERROR)){
            ProgramItem program = new ProgramItem();
            program.setName("Error de conexión");
            program.setSlug("error");
            ArrayList<ProgramItem> programItems = new ArrayList<>();
            programItems.add(program);
            programSlugMvpView.showProgramList(programItems);
        }

        //else
           // programSlugMvpView.showUnknownErrorMessage();

    }
}
