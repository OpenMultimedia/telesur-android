package net.telesurtv.www.telesur.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.NewsViewModel;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.views.adapter.RecyclerVideoAdapter;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 15/07/15.
 */
public class FragmentListExample extends Fragment {

    public static FragmentListExample getInstance() {
        return new FragmentListExample();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_list_example, container, false);
        setupRecyclerView(rv);


        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
      /*  if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
           recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        }
        else{
           recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 4));
        }*/


        recyclerView.addItemDecoration(new ItemOffsetDecoration(recyclerView.getContext(), R.dimen.item_decoration));
        // recyclerView.setHasFixedSize(true);
        //recyclerNewsAdapter.setNewsList(news());
        //recyclerView.setAdapter(new RecyclerVideoAdapter());


    }


    // Generamos videos y repetimos para tener una lista grande
    private List<VideoViewModel> videos() {


        List<VideoViewModel> videoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            VideoViewModel video = new VideoViewModel();
            video.setTitle("Viola coalición saudí tercera tregua humanitaria en Yemen");
            video.setCategory("Política");
            video.setData("27 de julio 2015");
            video.setDuration("00:52");
            videoList.add(video);
        }

        return videoList;
    }

    // Generamos personas y repetimos para tener una lista grande
    private List<NewsViewModel> news() {


        List<NewsViewModel> newsViewModelList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            NewsViewModel newsViewModel = new NewsViewModel();
            newsViewModel.setTitleNews("'El Chapo' Guzmán se fuga de la cárcel por un túnel de 1,500 metros");
            newsViewModel.setDataNews("16 de julio de 2015");
            newsViewModelList.add(newsViewModel);
        }

        return newsViewModelList;
    }


}