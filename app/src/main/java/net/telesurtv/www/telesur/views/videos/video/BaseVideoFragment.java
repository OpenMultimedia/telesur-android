package net.telesurtv.www.telesur.views.videos.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.telesurtv.www.telesur.ItemRecyclerClickListener;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.views.view.LoadMoreDetector;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jhordan on 28/10/15.
 */
public abstract class BaseVideoFragment extends Fragment implements VideosMvpView,ItemRecyclerClickListener,LoadMoreDetector.Listener {

    @Bind(R.id.srl_recycler) SwipeRefreshLayout srl_video;
    @Bind(R.id.rv_recycler)  RecyclerView rv_video;
    @Bind(R.id.pb_recycler)  ProgressBar pv_video;
    @Bind(R.id.txt_recycler) TextView txt_video;
    @Bind(R.id.iv_recycler)  ImageView iv_video;

    private VideoPresenter videoPresenter;
    private RecyclerVideoAdapter videoAdapter;
    protected abstract String getSection();

    LoadMoreDetector loadMoreDetector;
    int queryFirst = 0;
    int queryLast = 25;
    int aux = 0;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoAdapter = new RecyclerVideoAdapter();
        videoAdapter.setItemRecyclerClickListener(this);
    }

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_recycler, container, false);
        ButterKnife.bind(this,rootView);
        videoPresenter = new VideoPresenter();
        videoPresenter.attachedView(this);
        setupRecyclerView();
        videoPresenter.setVideoSection(getSection(),1,queryLast);
        Log.i("value_request", Integer.toString(queryFirst) + Integer.toString(queryLast));
        return rootView;
    }

    @Override
    public void showVideoList(ArrayList<VideoViewModel> videoViewModelArrayList) {
        videoAdapter.setListItems(videoViewModelArrayList);
        rv_video.setAdapter(videoAdapter);
    }

    @Override public void showProgress() {
        srl_video.setVisibility(View.GONE);
        rv_video.setVisibility(View.GONE);
        pv_video.setVisibility(View.VISIBLE);
        txt_video.setVisibility(View.VISIBLE);
        iv_video.setVisibility(View.GONE);
        txt_video.setText(getString(R.string.loading_message));
    }

    @Override public void hideProgress() {
        pv_video.setVisibility(View.GONE);
        txt_video.setVisibility(View.GONE);
        iv_video.setVisibility(View.GONE);
        srl_video.setVisibility(View.VISIBLE);
        rv_video.setVisibility(View.VISIBLE);
    }

    @Override public void showConnectionErrorMessage() {
        pv_video.setVisibility(View.GONE);
        txt_video.setVisibility(View.VISIBLE);
        iv_video.setVisibility(View.VISIBLE);
        srl_video.setVisibility(View.GONE);
        txt_video.setText(getString(R.string.expected_error_wifi));
        iv_video.setImageResource(R.mipmap.not_server);
    }

    @Override public void showUnknownErrorMessage() {
        pv_video.setVisibility(View.GONE);
        txt_video.setVisibility(View.VISIBLE);
        iv_video.setVisibility(View.VISIBLE);
        srl_video.setVisibility(View.GONE);
        txt_video.setText(getString(R.string.expected_error_token));
        iv_video.setImageResource(R.mipmap.ic_uknow_error);
    }

    @Override public void launchReproductor(int position, VideoViewModel videoViewModel) {
        Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
        intent.putExtra("video_url", videoViewModel.getVideoURL());
        intent.putExtra("video_title", videoViewModel.getTitle());
        intent.putExtra("video_category", videoViewModel.getCategory());
        intent.putExtra("video_duration", videoViewModel.getDuration());
        intent.putExtra("video_description", videoViewModel.getDescription());
        intent.putExtra("video_data", videoViewModel.getData());
        intent.putExtra("video_link", videoViewModel.getLinkVideoNavegator());
        intent.putExtra("video_image", videoViewModel.getBackground());
        intent.putExtra("video_share", "-Video: ");
        startActivity(intent);

    }

    @Override public void itemRecycleOnClick(int position, VideoViewModel videoViewModel) {
        videoPresenter.onItemSelected(position, videoViewModel);
    }

    @Override public void onDestroy() {
        videoPresenter.detachView();
        super.onDestroy();
    }

    protected void setupRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rv_video.getContext());
        rv_video.setLayoutManager(linearLayoutManager);
        rv_video.setItemAnimator(new DefaultItemAnimator());
        loadMoreDetector = new LoadMoreDetector(linearLayoutManager);
        loadMoreDetector.setListener(this);
        loadMoreDetector.setEnabled(true);
        loadMoreDetector.setLoading(false);
        rv_video.addOnScrollListener(loadMoreDetector);
    }


    @Override
    public void onResume() {
        super.onResume();
       // loadMoreDetector.setEnabled(true);
        loadMoreDetector.setLoading(false);


        System.out.println(   loadMoreDetector.isEnabled());
        System.out.println(   loadMoreDetector.isLoading());
    }

    @Override
    public void onLoadMore() {
        aux = queryLast;
        queryLast+= 25;
        queryFirst = aux;
        Log.i("value_request", Integer.toString(queryFirst) + Integer.toString(queryLast));
        Snackbar.make(getView(), "Cargando...", Snackbar.LENGTH_LONG).setAction("", null).show();
        videoPresenter.setVideoSection(getSection(), queryFirst, queryLast);

    }

  /*  @Override
    public void onLoadMore() {
        Toast.makeText(getActivity(), "estas en el ultimo", Toast.LENGTH_SHORT).show();
        loadMoreDetector.setEnabled(true);
        loadMoreDetector.setLoading(false);


    }*/
}
