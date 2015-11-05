package net.telesurtv.www.telesur.views.streaming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.Streaming;
import net.telesurtv.www.telesur.views.dialog.ScheduleItemDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jhordan on 04/11/15.
 */
public class StreamingFragment extends Fragment implements StreamingMvpView , StreamingOnItemClickListener{

    public static StreamingFragment newInstance() {
        return new StreamingFragment();
    }

    @Bind(R.id.srl_recycler) SwipeRefreshLayout srl_streaming;
    @Bind(R.id.rv_recycler)  RecyclerView rv_streaming;
    @Bind(R.id.pb_recycler)  ProgressBar pv_streaming;
    @Bind(R.id.txt_recycler) TextView txt_streaming;
    @Bind(R.id.iv_recycler)  ImageView iv_streaming;

    private StreamingPresenter streamingPresenter;
    private StreamingAdapter streamingAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_streaming_video, container, false);
        ButterKnife.bind(this,rootView);
        setupRecyclerView();
        streamingPresenter = new StreamingPresenter();
        streamingPresenter.attachedView(this);
        return rootView;

    }

    @Override
    public void showProgramScheduleList(List<Streaming> programm,List<SimpleSectionedRecyclerViewAdapter.Section> sectionList) {

        streamingAdapter = new StreamingAdapter(getActivity(), programm);
        streamingAdapter.setStreamingOnItemClickListener(this);
        SimpleSectionedRecyclerViewAdapter.Section[] sections =
                new SimpleSectionedRecyclerViewAdapter.Section[sectionList.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter =
                new SimpleSectionedRecyclerViewAdapter(getActivity(), R.layout.section, R.id.section_text, streamingAdapter);
        mSectionedAdapter.setSections(sectionList.toArray(sections));
        rv_streaming.setAdapter(mSectionedAdapter);

    }

    @Override
    public void showProgress() {
        srl_streaming.setVisibility(View.GONE);
        rv_streaming.setVisibility(View.GONE);
        pv_streaming.setVisibility(View.VISIBLE);
        txt_streaming.setVisibility(View.VISIBLE);
        iv_streaming.setVisibility(View.GONE);
        txt_streaming.setText(getString(R.string.loading_message));
    }

    @Override
    public void hideProgress() {
        pv_streaming.setVisibility(View.GONE);
        txt_streaming.setVisibility(View.GONE);
        iv_streaming.setVisibility(View.GONE);
        srl_streaming.setVisibility(View.VISIBLE);
        rv_streaming.setVisibility(View.VISIBLE);
    }

    @Override
    public void showConnectionErrorMessage() {
        pv_streaming.setVisibility(View.GONE);
        txt_streaming.setVisibility(View.VISIBLE);
        iv_streaming.setVisibility(View.VISIBLE);
        srl_streaming.setVisibility(View.GONE);
        txt_streaming.setText(getString(R.string.expected_error_wifi));
        iv_streaming.setImageResource(R.mipmap.not_server);
    }

    @Override
    public void showUnknownErrorMessage() {
        pv_streaming.setVisibility(View.GONE);
        txt_streaming.setVisibility(View.VISIBLE);
        iv_streaming.setVisibility(View.VISIBLE);
        srl_streaming.setVisibility(View.GONE);
        txt_streaming.setText(getString(R.string.expected_error_token));
        iv_streaming.setImageResource(R.mipmap.ic_uknow_error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        streamingPresenter.detachView();

    }

    private void setupRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rv_streaming.getContext());
        rv_streaming.setLayoutManager(linearLayoutManager);
        rv_streaming.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void itemRecycleOnClickStreaming(int position, Streaming streaming) {
        streamingPresenter.onItemSelected(position, streaming);
    }

    @Override
    public void launchCanal(int position, Streaming canal) {

        Bundle bundle = new Bundle();
        bundle.putString("title",canal.getName());
        bundle.putString("start_hour_v",canal.getStarHourVenezuela());
        bundle.putString("finish_hour_v", canal.getFinishHourVenezuela());
        bundle.putString("start_hour", canal.getStarHour());
        bundle.putString("finish_hour", canal.getFinishHour());
        bundle.putString("sinopsis", canal.getSinopsis());
        bundle.putString("photo", canal.getPhoto());

        ScheduleItemDialog scheduleItemDialog = ScheduleItemDialog.newInstance(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        scheduleItemDialog.show(fragmentManager,"");
    }
}