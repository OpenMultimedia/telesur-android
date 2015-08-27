package net.telesurtv.www.telesur.views.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novoda.notils.caster.Views;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.views.adapter.RecyclerVideoAdapter;
import net.telesurtv.www.telesur.views.view.DelegatedSwipeRefreshLayout;
import net.telesurtv.www.telesur.views.view.FeedRecyclerItemDecoration;
import net.telesurtv.www.telesur.views.view.ViewDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 15/07/15.
 */
public class FragmentPrueba extends Fragment implements  SwipeRefreshLayout.OnRefreshListener,ViewDelegate {

    public FragmentPrueba() {
    }

    public static FragmentPrueba newInstance() {
        return new FragmentPrueba();
    }

    RecyclerView storiesList;
    DelegatedSwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prueba, container, false);
        refreshLayout = Views.findById(rootView, R.id.mi_refresh);
        storiesList = Views.findById(rootView, R.id.list_news);

        setupRefreshLayout();

       refreshLayout.postOnAnimation(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });

        setupRecyclerView(storiesList);

        return rootView;
    }


    protected void setupRefreshLayout() {
        refreshLayout.setColorSchemeResources(R.color.primaryDark, R.color.primary);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setViewDelegate(this);
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
      /*  if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
           recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        }
        else{
           recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 4));
        }*/


       // recyclerView.addItemDecoration(createItemDecoration(getResources()));
        // recyclerView.setHasFixedSize(true);
        //recyclerNewsAdapter.setNewsList(news());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
       // recyclerView.setAdapter(new RecyclerVideoAdapter());


    }

    private FeedRecyclerItemDecoration createItemDecoration(Resources resources) {
        int verticalItemSpacingInPx = resources.getDimensionPixelSize(R.dimen.feed_padding_infra_spans);
        int horizontalItemSpacingInPx = resources.getDimensionPixelSize(R.dimen.feed_padding_infra_spans);
        return new FeedRecyclerItemDecoration(verticalItemSpacingInPx, horizontalItemSpacingInPx);
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


    @Override
    public void onRefresh() {

    }

    @Override
    public boolean isReadyForPull() {
        return ViewCompat.canScrollVertically(storiesList, -1);
    }
}
