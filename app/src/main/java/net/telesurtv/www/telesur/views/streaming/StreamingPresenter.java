package net.telesurtv.www.telesur.views.streaming;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.model.Streaming;

import java.util.List;

/**
 * Created by Jhordan on 04/11/15.
 */
public class StreamingPresenter implements Presenter<StreamingMvpView>,TelesurStreamingCallBack{

    private StreamingMvpView streamingMvpView;
    private StreamingInteractor streamingInteractor;
    private TelesurApiService telesurApiService;

    public StreamingPresenter(){
        telesurApiService = ClientServiceTelesur.getStaticRestAdapter().create(TelesurApiService.class);
        streamingInteractor = new StreamingInteractor(telesurApiService);

    }

    @Override
    public void attachedView(StreamingMvpView view) {
        if(view == null)
            throw new IllegalArgumentException("You can't set a null view");

        streamingMvpView = view;
        streamingMvpView.showProgress();
        streamingInteractor.getScheduleFromServer(this);
    }


    @Override
    public void onItemSelected(int position, Streaming streaming) {
      streamingMvpView.launchCanal(position,streaming);
    }


    @Override
    public void onLoaderVideos(List<Streaming> program, List<SimpleSectionedRecyclerViewAdapter.Section> sectionList) {
        streamingMvpView.hideProgress();
        streamingMvpView.showProgramScheduleList(program,sectionList);
    }

    @Override
    public void onFaliedToGetData(String error) {
        if(error.equals(NetworkErrorException.SERVER_INTERNET_ERROR))
            streamingMvpView.showConnectionErrorMessage();
        else
            streamingMvpView.showUnknownErrorMessage();
    }

    @Override
    public void detachView() {
        streamingMvpView = null;
    }
}
