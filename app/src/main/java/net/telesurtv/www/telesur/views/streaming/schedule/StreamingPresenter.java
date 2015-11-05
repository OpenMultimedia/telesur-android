package net.telesurtv.www.telesur.views.streaming.schedule;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.Streaming;

import java.util.List;

/**
 * Created by Jhordan on 04/11/15.
 */
public class StreamingPresenter implements Presenter<SecheduleMvpView>,TelesurStreamingCallBack{

    private SecheduleMvpView secheduleMvpView;
    private SecheduleInteractor secheduleInteractor;
    private TelesurApiService telesurApiService;

    public StreamingPresenter(){
        telesurApiService = ClientServiceTelesur.getStaticRestAdapter().create(TelesurApiService.class);
        secheduleInteractor = new SecheduleInteractor(telesurApiService);

    }

    @Override
    public void attachedView(SecheduleMvpView view) {
        if(view == null)
            throw new IllegalArgumentException("You can't set a null view");

        secheduleMvpView = view;
        secheduleMvpView.showProgress();
        secheduleInteractor.getScheduleFromServer(this);
    }


    @Override
    public void onItemSelected(int position, Streaming streaming) {
      secheduleMvpView.launchCanal(position,streaming);
    }


    @Override
    public void onLoaderVideos(List<Streaming> program, List<SimpleSectionedRecyclerViewAdapter.Section> sectionList) {
        secheduleMvpView.hideProgress();
        secheduleMvpView.showProgramScheduleList(program,sectionList);
    }

    @Override
    public void onFaliedToGetData(String error) {
        if(error.equals(NetworkErrorException.SERVER_INTERNET_ERROR))
            secheduleMvpView.showConnectionErrorMessage();
        else
            secheduleMvpView.showUnknownErrorMessage();
    }

    @Override
    public void detachView() {
        secheduleMvpView = null;
    }
}
