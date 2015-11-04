package net.telesurtv.www.telesur.views.videos.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.ItemRecyclerClickListenerMenuVideo;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoMenu;
import net.telesurtv.www.telesur.views.videos.video.VideoListDetailActivity;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by Jhordan on 15/07/15.
 */
public class VideoCatalogFragment extends Fragment implements CatalogMvpView,ItemRecyclerClickListenerMenuVideo {

    public static VideoCatalogFragment newInstance() {
        return new VideoCatalogFragment();
    }

    RecyclerView recyclerViewMenu;
    RecyclerVideoTypeAdapter recyclerVideoTypeAdapter;
    CatalogPresenterImple catalogPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        initializeView(rootView);
        catalogPresenter = new CatalogPresenterImple();
        catalogPresenter.attachedView(this);
        return rootView;
    }

    @Override public void onResume() {
        super.onResume();
        catalogPresenter.onResume();
    }

    @Override public void onDestroy() {
        catalogPresenter.detachView();
        super.onDestroy();
    }

    @Override public void itemRecycleOnClick(int position, VideoMenu videoMenu, View toolbar) {
        catalogPresenter.onItemSelected(position, videoMenu, toolbar);
    }

    @Override public void showCatalogList(ArrayList<VideoMenu> videoMenuArrayList) {
        recyclerVideoTypeAdapter = new RecyclerVideoTypeAdapter(videoMenuArrayList, getActivity());
        recyclerViewMenu.setAdapter(recyclerVideoTypeAdapter);
        recyclerVideoTypeAdapter.setItemRecyclerClickListener(this);

    }

    @Override
    public void launchDetail(int position, VideoMenu videoMenu, View toolbar) {
        Intent intent = new Intent(getActivity(), VideoListDetailActivity.class);
        intent.putExtra("video_theme", videoMenu.getStyle());
        intent.putExtra("video_position", position);
        startActivity(intent);
    }


    private void initializeView(View rootView) {
        int spans = getResources().getInteger(R.integer.video_span);
        recyclerViewMenu = (RecyclerView) rootView.findViewById(R.id.video_menu_recycler);
        recyclerViewMenu.setLayoutManager(new GridLayoutManager(recyclerViewMenu.getContext(), spans));
        recyclerViewMenu.addItemDecoration(new ItemOffsetDecoration(recyclerViewMenu.getContext(), R.dimen.item_decoration));
    }


}
