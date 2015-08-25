package net.telesurtv.www.telesur.views.program;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;
import com.squareup.otto.Subscribe;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.video.VideoTemporal;
import net.telesurtv.www.telesur.model.ProgramViewModel;
import net.telesurtv.www.telesur.util.OttoBus;
import net.telesurtv.www.telesur.views.adapter.RecyclerProgramAdapter;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

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
 * Created by Jhordan on 15/07/15.
 */
public class ProgramFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public ProgramFragment() {
    }

    public static ProgramFragment newInstance() {
        return new ProgramFragment();
    }

    RecyclerView recyclerViewProgram;
    RecyclerProgramAdapter recyclerProgramAdapter;
    List<ProgramViewModel> programViewModelList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    String slugRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerProgramAdapter = new RecyclerProgramAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program, container, false);

        recyclerViewProgram = (RecyclerView) rootView.findViewById(R.id.program_recycler_view);

        //recyclerViewProgram.setLayoutManager(new LinearLayoutManager(recyclerViewProgram.getContext()));
        recyclerViewProgram.addItemDecoration(new ItemOffsetDecoration(recyclerViewProgram.getContext(), R.dimen.item_decoration));
        recyclerViewProgram.setItemAnimator(new DefaultItemAnimator());

        recyclerViewProgram.setAdapter(recyclerProgramAdapter);


        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.program_swipe_refresh);

        swipeRefreshLayout.setColorSchemeResources(R.color.primaryDark, R.color.primary);
        swipeRefreshLayout.setOnRefreshListener(this);



        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        try {
            OttoBus.getInstance().register(this);

        } catch (Exception e) {
            Log.e("error bus", e.toString());
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        try {
            OttoBus.getInstance().unregister(this);
        } catch (Exception e) {
            Log.e("error bus", e.toString());
        }


    }


    /**
     * get slug from producer
     *
     * @param slug id of program
     */

    @Subscribe
    public void subscribeSlug(String slug) {

        getListVideos(slug);
        slugRefresh = slug;

    }


    /**
     * +
     * this method make request to get Program List
     *
     * @param slugPrograma identificator of program
     */

    protected void getListVideos(String slugPrograma) {

        TelesurApiService clientServiceTelesur = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        clientServiceTelesur.getPrograms("completo", 1, 20, "programa", slugPrograma).
                subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Response>() {
            @Override
            public void call(Response response) {


                final StringWriter writer = new StringWriter();

                try {
                    IOUtils.copy(response.getBody().in(), writer, "UTF-8");
                    programViewModelList.clear();
                    VideoTemporal[] videoTemporal = new GsonBuilder().create().fromJson(writer.toString(), VideoTemporal[].class);

                    for (int i = 0; i < videoTemporal.length; i++) {
                        VideoTemporal videoItem = videoTemporal[i];
                        ProgramViewModel programViewModel = new ProgramViewModel();
                        programViewModel.setTitle(videoItem.getTitulo());
                        programViewModel.setImage(videoItem.getThumbnail_grande());
                        programViewModel.setProgramImage(videoItem.getPrograma().getImagen_url());


                        if (videoItem.getCategoria() == null)
                            programViewModel.setCategory("telesur");
                        else
                            programViewModel.setCategory(videoItem.getCategoria().getNombre());


                        programViewModelList.add(programViewModel);

                    }

                    updateUI(programViewModelList);

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


    private void updateUI(final List<ProgramViewModel> programViewModels) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerProgramAdapter.clear();
                recyclerProgramAdapter.setListItems(programViewModels);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRefresh() {

        if (slugRefresh != null)
            getListVideos(slugRefresh);

    }
}
