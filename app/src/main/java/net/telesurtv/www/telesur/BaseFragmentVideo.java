package net.telesurtv.www.telesur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.video.VideoTemporal;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.views.adapter.RecyclerVideoAdapter;
import net.telesurtv.www.telesur.views.view.DelegatedSwipeRefreshLayout;
import net.telesurtv.www.telesur.views.view.ViewDelegate;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 28/07/15.
 */
public abstract class BaseFragmentVideo extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ViewDelegate {

    RecyclerView recyclerViewVideoList;
    DelegatedSwipeRefreshLayout refreshLayoutVideo;
    public RecyclerVideoAdapter recyclerVideoAdapter;
    List<VideoViewModel> videoList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerVideoAdapter = new RecyclerVideoAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_video, container, false);

        setUpViews(rootView);
        setupRecyclerView();
        setupRefreshLayout();


        return rootView;
    }


    /**
     * getVies from xml
     *
     * @param view rootView fragment
     */
    public void setUpViews(View view) {
        recyclerViewVideoList = (RecyclerView) view.findViewById(R.id.pruebas_video_recycler);
        refreshLayoutVideo = (DelegatedSwipeRefreshLayout) view.findViewById(R.id.video_refresh);


    }


    /**
     * initialize recyclerView
     */
    protected void setupRecyclerView() {
        recyclerViewVideoList.setLayoutManager(new LinearLayoutManager(recyclerViewVideoList.getContext()));
        recyclerViewVideoList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVideoList.setAdapter(recyclerVideoAdapter);
        getListVideos(getSection());
    }


    /**
     * initialize swipeRefreshLayout
     */
    protected void setupRefreshLayout() {
        refreshLayoutVideo.setColorSchemeResources(R.color.primaryDark, R.color.primary);
        refreshLayoutVideo.setOnRefreshListener(this);
        refreshLayoutVideo.setViewDelegate(this);
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public boolean isReadyForPull() {
        return ViewCompat.canScrollVertically(recyclerViewVideoList, -1);
    }


    /**
     * @param videoViewModels videoList to update UI
     */
    private void updateUI(final List<VideoViewModel> videoViewModels) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerVideoAdapter.clear();
                recyclerVideoAdapter.setListItems(videoViewModels);
            }
        });
    }

    /**
     * This method make a request to the server
     *
     * @param section video section to download
     */
    protected void getListVideos(String section) {

        TelesurApiService clientServiceTelesur = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        clientServiceTelesur.getVideosList("completo", 1, 20, section).
                subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Response>() {
            @Override
            public void call(Response response) {


                final StringWriter writer = new StringWriter();
                try {
                    IOUtils.copy(response.getBody().in(), writer, "UTF-8");
                    // System.out.println("Request a la API" + writer.toString());
                    VideoTemporal[] videoTemporal = new GsonBuilder().create().fromJson(writer.toString(), VideoTemporal[].class);


                    videoList.clear();
                    for (int i = 0; i < videoTemporal.length; i++) {
                        VideoTemporal videoItem = videoTemporal[i];
                        VideoViewModel video = new VideoViewModel();
                        video.setTitle(videoItem.getTitulo());
                        video.setBackground(videoItem.getThumbnail_grande());
                        video.setDuration(videoItem.getDuracion());
                        video.setData(videoItem.getFecha());
                        if (videoItem.getCategoria() == null)
                            video.setCategory("Sección no especificada");
                        else
                            video.setCategory(videoItem.getCategoria().getNombre());

                        video.setVisitCounter(videoItem.getEstadisticas().getVistas());
                        video.setVideoURL(videoItem.getArchivo_url());
                       // video.setPrimaryColor(getPrimaryColor(i));

                        videoList.add(video);
                    }

                    updateUI(videoList);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });


    }








    /**
     * This method return the section according to the fragment
     *
     * @return
     */
    protected abstract String getSection();


}
