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
import net.telesurtv.www.telesur.util.Theme;
import net.telesurtv.www.telesur.views.videos.video.VideoDetailActivity;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Jhordan on 03/11/15.
 */
public class ProgramFragment extends Fragment implements ProgramMvpView, ItemRecyclerClickListenerProgram,SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.srl_recycler)
    SwipeRefreshLayout srl_program;
    @Bind(R.id.rv_recycler)
    RecyclerView rv_program;
    @Bind(R.id.pb_recycler)
    ProgressBar pv_program;
    @Bind(R.id.txt_recycler)
    TextView txt_program;
    @Bind(R.id.iv_recycler)
    ImageView iv_program;

    private ProgramPresenter programPresenter;
    private RecyclerProgramAdapter programAdapter;
    private String slugRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        programAdapter = new RecyclerProgramAdapter();
        programAdapter.setItemRecyclerClickListenerProgram(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_recycler, container, false);
        ButterKnife.bind(this, rootView);
        setupRecyclerView();
        initializeSwipeRefreshLayout();
        programPresenter = new ProgramPresenter();
        programPresenter.attachedView(this);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onResume() {
        super.onResume();
      //  programPresenter.onStart();

        try {
            OttoBus.getInstance().register(this);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        //programPresenter.onStop();

        try {
            OttoBus.getInstance().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        programPresenter.detachView();
    }

    @Override
    public void showVideoList(ArrayList<ProgramViewModel> programViewModels) {
        programAdapter.setListItems(programViewModels);
        rv_program.setAdapter(programAdapter);

    }


    @Subscribe
    public void subscribeSlug(String slug) {

        System.out.println("llego el slug" + slug);

        if (slug.equals("all")) {
            programPresenter.getAllSections(1, 30);
            slugRefresh = "all";
        } else {
            programPresenter.setVideoSection(slug, 1, 30);
            slugRefresh = slug;
        }




    }


    private void setupRecyclerView() {

        rv_program.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.item_decoration));
        rv_program.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showProgress() {
        srl_program.setVisibility(View.GONE);
        rv_program.setVisibility(View.GONE);
        pv_program.setVisibility(View.VISIBLE);
        txt_program.setVisibility(View.VISIBLE);
        iv_program.setVisibility(View.GONE);
        txt_program.setText(getString(R.string.loading_message));
    }

    @Override
    public void hideProgress() {
        pv_program.setVisibility(View.GONE);
        txt_program.setVisibility(View.GONE);
        iv_program.setVisibility(View.GONE);
        srl_program.setVisibility(View.VISIBLE);
        rv_program.setVisibility(View.VISIBLE);
    }

    @Override
    public void showConnectionErrorMessage() {
        pv_program.setVisibility(View.GONE);
        txt_program.setVisibility(View.VISIBLE);
        iv_program.setVisibility(View.VISIBLE);
        srl_program.setVisibility(View.GONE);
        txt_program.setText(getString(R.string.expected_error_wifi));
        iv_program.setImageResource(R.mipmap.not_server);
    }

    @Override
    public void showUnknownErrorMessage() {
        pv_program.setVisibility(View.GONE);
        txt_program.setVisibility(View.VISIBLE);
        iv_program.setVisibility(View.VISIBLE);
        srl_program.setVisibility(View.GONE);
        txt_program.setText(getString(R.string.expected_error_token));
        iv_program.setImageResource(R.mipmap.ic_uknow_error);
    }


    @Override public void showProgressRefresh() {
        srl_program.setRefreshing(true);
    }

    @Override public void hideProgressRefresh() {
        if(srl_program.isRefreshing())
            srl_program.setRefreshing(false);

        srl_program.setVisibility(View.VISIBLE);
        rv_program.setVisibility(View.VISIBLE);
    }


    @Override
    public void itemRecycleOnClickProgram(int position, ProgramViewModel programViewModel) {
        programPresenter.onItemSelected(position, programViewModel);
    }

    @Override
    public void launchReproductor(int position, ProgramViewModel videoViewModel) {
        Intent intent = new Intent(getActivity(), ProgramDetailActivity.class);
        intent.putExtra("video_url", videoViewModel.getUrl());
        intent.putExtra("video_title", videoViewModel.getTitle());
        intent.putExtra("video_category", videoViewModel.getCategory());
        intent.putExtra("video_duration", videoViewModel.getDuration());
        intent.putExtra("video_description", videoViewModel.getDescription());
        intent.putExtra("video_data", videoViewModel.getData());
        intent.putExtra("video_link", videoViewModel.getLinkNavegation());
        intent.putExtra("video_image", videoViewModel.getImage());
        intent.putExtra("video_program_descripcion", videoViewModel.getDescriptionProgram());
        intent.putExtra("video_time_p", videoViewModel.getTime());
        intent.putExtra("video_share", "-Programa: ");
        startActivity(intent);
    }


    @Override
    public void onRefresh() {
        System.out.println("Refresh: " + slugRefresh);

        if(slugRefresh != null)
           programPresenter.isRefreshListener(srl_program.isRefreshing(), slugRefresh, 1, 30);
    }

    private void initializeSwipeRefreshLayout() {

        Theme theme = Theme.valueOf("news");
        srl_program.setColorSchemeResources(theme.getColorPrimary(), theme.getWindowBackground());
        srl_program.setOnRefreshListener(this);


    }
}
