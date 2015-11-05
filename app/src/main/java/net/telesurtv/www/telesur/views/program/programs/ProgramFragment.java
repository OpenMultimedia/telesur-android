package net.telesurtv.www.telesur.views.program.programs;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.ProgramViewModel;
import net.telesurtv.www.telesur.util.OttoBus;
import net.telesurtv.www.telesur.views.videos.video.VideoDetailActivity;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 15/07/15.
 */
public class ProgramFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ItemRecyclerClickListenerProgram {

    public ProgramFragment() {
    }

    public static ProgramFragment newInstance() {
        return new ProgramFragment();
    }

    RecyclerView recyclerViewProgram;
    RecyclerProgramAdapter recyclerProgramAdapter;
    List<ProgramViewModel> programViewModelList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBarProgram;
    private ImageView imageViewServer;
    TextView txtPrograms;
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
        recyclerProgramAdapter.setItemRecyclerClickListenerProgram(this);
        recyclerViewProgram.setAdapter(recyclerProgramAdapter);

        imageViewServer = (ImageView) rootView.findViewById(R.id.image_server);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.program_swipe_refresh);
        progressBarProgram = (ProgressBar) rootView.findViewById(R.id.progress_bar_program);
        txtPrograms = (TextView) rootView.findViewById(R.id.txt_message_program);

        swipeRefreshLayout.setColorSchemeResources(R.color.primaryDark, R.color.primary);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setVisibility(View.GONE);
        progressBarProgram.setVisibility(View.VISIBLE);
        txtPrograms.setVisibility(View.GONE);
        imageViewServer.setVisibility(View.GONE);


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
        progressBarProgram.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);

        System.out.println("ññego el slug" + slug);

        if (slug.equals("all")) {
            //getAllVideos();
            slugRefresh = "all";
        } else {
           // getListVideos(slug);
            slugRefresh = slug;
        }


    }


    /**
     * this method make request all Videos
     */

    protected void getAllVideos() {


        /*
        TelesurApiService clientServiceTelesur = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        clientServiceTelesur.getAllPrograms("completo", 1, 20, "programa").
                subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Response>() {
            @Override
            public void call(Response response) {


                final StringWriter writer = new StringWriter();

                try {
                   // IOUtils.copy(response.getBody().in(), writer, "UTF-8");
                    programViewModelList.clear();
                    VideoTemporal[] videoTemporal = new GsonBuilder().create().fromJson(writer.toString(), VideoTemporal[].class);

                    for (int i = 0; i < videoTemporal.length; i++) {
                        VideoTemporal videoItem = videoTemporal[i];
                        ProgramViewModel programViewModel = new ProgramViewModel();
                        programViewModel.setTitle(videoItem.getTitulo());
                        programViewModel.setImage(videoItem.getThumbnail_grande());
                        programViewModel.setProgramImage(videoItem.getPrograma().getImagen_url());
                        programViewModel.setData(Config.date_to_human(videoItem.getFecha()));
                        programViewModel.setDescription(videoItem.getDescripcion());
                        programViewModel.setUrl(videoItem.getArchivo_url());
                        programViewModel.setLinkNavegation(videoItem.getNavegatorURL());

                        if (videoItem.getCategoria() == null)
                            programViewModel.setCategory("telesur");
                        else
                            programViewModel.setCategory(videoItem.getCategoria().getNombre());


                        programViewModelList.add(programViewModel);

                    }

                    updateUI(programViewModelList);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();

                progressBarProgram.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.GONE);
                txtPrograms.setVisibility(View.VISIBLE);


                if (InternetConnection.isNetworkMobile(getActivity())) {
                    if (!InternetConnection.connectionState(getActivity()) && !InternetConnection.mobileConnection(getActivity())) {

                        txtPrograms.setText(R.string.not_internet_conection);
                        imageViewServer.setVisibility(View.VISIBLE);
                    }
                } else if (!InternetConnection.connectionState(getActivity())) {

                    txtPrograms.setText(R.string.not_internet_conection);
                    imageViewServer.setVisibility(View.VISIBLE);
                } else {
                    txtPrograms.setText(R.string.expected_error_token);
                    imageViewServer.setVisibility(View.VISIBLE);

                }

            }
        });

*/
    }

   protected void getListVideos(String slugPrograma) {
/*
        TelesurApiService clientServiceTelesur = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        clientServiceTelesur.getPrograms("completo", 1, 20, "programa", slugPrograma).
                subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Response>() {
            @Override
            public void call(Response response) {


                final StringWriter writer = new StringWriter();

                try {
                  //  IOUtils.copy(response.getBody().in(), writer, "UTF-8");
                    programViewModelList.clear();
                    VideoTemporal[] videoTemporal = new GsonBuilder().create().fromJson(writer.toString(), VideoTemporal[].class);

                    for (int i = 0; i < videoTemporal.length; i++) {
                        VideoTemporal videoItem = videoTemporal[i];
                        ProgramViewModel programViewModel = new ProgramViewModel();
                        programViewModel.setTitle(videoItem.getTitulo());
                        programViewModel.setImage(videoItem.getThumbnail_grande());
                        programViewModel.setProgramImage(videoItem.getPrograma().getImagen_url());
                        programViewModel.setData(Config.date_to_human(videoItem.getFecha()));
                        programViewModel.setDescription(videoItem.getDescripcion());
                        programViewModel.setUrl(videoItem.getArchivo_url());
                        programViewModel.setLinkNavegation(videoItem.getNavegatorURL());

                        if (videoItem.getCategoria() == null)
                            programViewModel.setCategory("telesur");
                        else
                            programViewModel.setCategory(videoItem.getCategoria().getNombre());


                        programViewModelList.add(programViewModel);

                    }

                    updateUI(programViewModelList);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

*/
    }


    private void updateUI(final List<ProgramViewModel> programViewModels) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerProgramAdapter.clear();
                recyclerProgramAdapter.setListItems(programViewModels);
                progressBarProgram.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRefresh() {

        // if (slugRefresh != null)
        //   getListVideos(slugRefresh);

        if (slugRefresh != null) {
            if (slugRefresh.equals("all"))
                getAllVideos();
            else
                getListVideos(slugRefresh);
        }


    }

    @Override
    public void itemRecycleOnClickProgram(int position, ProgramViewModel videoViewModel) {
        Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
        intent.putExtra("video_url", videoViewModel.getUrl());
        intent.putExtra("video_title", videoViewModel.getTitle());
        intent.putExtra("video_category", videoViewModel.getCategory());
        intent.putExtra("video_duration", videoViewModel.getDuration());
        intent.putExtra("video_description", videoViewModel.getDescription());
        intent.putExtra("video_data", videoViewModel.getData());
        intent.putExtra("video_link", videoViewModel.getLinkNavegation());
        intent.putExtra("video_share", "-Programa: ");
        startActivity(intent);
    }
}
