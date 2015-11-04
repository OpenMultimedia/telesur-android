package net.telesurtv.www.telesur.views.streaming;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.streaming.Program;
import net.telesurtv.www.telesur.data.api.models.streaming.RootSchedule;
import net.telesurtv.www.telesur.model.ReviewViewModel;
import net.telesurtv.www.telesur.model.Streaming;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.util.InternetConnection;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 15/07/15.
 */
public class FragmentS extends Fragment {

    public FragmentS() {
    }

    public static FragmentS newInstance() {
        return new FragmentS();
    }

    RecyclerView mRecyclerView;
    ImageView imageView;
    SimpleAdapter mAdapter;
    ProgressBar progressBarReview;
    TextView txtMessage;
    private ImageView imageViewServer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_streaming_video, container, false);

        setUpRecyclerView(rootView);

        progressBarReview = (ProgressBar) rootView.findViewById(R.id.progress_bar_video);
        imageViewServer = (ImageView) rootView.findViewById(R.id.image_server);
        txtMessage = (TextView) rootView.findViewById(R.id.txt_message_video);
        progressBarReview.setVisibility(View.VISIBLE);
        txtMessage.setVisibility(View.GONE);
        imageViewServer.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);

        imageView = (ImageView) rootView.findViewById(R.id.image_view_play);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (InternetConnection.isNetworkMobile(getActivity())) {
                    if (!InternetConnection.connectionState(getActivity()) && !InternetConnection.mobileConnection(getActivity())) {

                        showToast(R.string.expected_error_wifi);
                    }
                } else if (!InternetConnection.connectionState(getActivity())) {
                    showToast(R.string.expected_error_wifi);

                } else {
                    startActivity(new Intent(getActivity(), StreamingDetailActivity.class));
                }


            }
        });




        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void showToast(int message) {
        Toast.makeText(getActivity(), getResources().getString(message), Toast.LENGTH_LONG).show();
    }

    private void getData() {
        TelesurApiService telesurApiService = ClientServiceTelesur.getStaticRestAdapter().create(TelesurApiService.class);
        telesurApiService.getSchedule().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<RootSchedule>() {
            @Override
            public void call(RootSchedule response) {
                setStreaming(response);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();

                mRecyclerView.setVisibility(View.GONE);
                progressBarReview.setVisibility(View.GONE);
                txtMessage.setVisibility(View.VISIBLE);

                if (InternetConnection.isNetworkMobile(getActivity())) {
                    if (!InternetConnection.connectionState(getActivity()) && !InternetConnection.mobileConnection(getActivity())) {

                        txtMessage.setText(R.string.not_internet_conection);
                        imageViewServer.setVisibility(View.VISIBLE);
                    }
                } else if (!InternetConnection.connectionState(getActivity())) {

                    txtMessage.setText(R.string.not_internet_conection);
                    imageViewServer.setVisibility(View.VISIBLE);
                } else {
                    txtMessage.setText(R.string.expected_error_token);
                    imageViewServer.setVisibility(View.VISIBLE);


                }
            }
        });
    }

    private void setUpRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.streaming_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateUI(final List<Streaming> programm, final List<Integer> integerList) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mAdapter = new SimpleAdapter(getActivity(), programm);
                List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

                sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0, "Lunes"));
                sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0)), "Martes"));
                sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1)), "Miercoles"));
                sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2)), "Jueves"));
                sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2) + integerList.get(3)), "Viernes"));
                sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2) + integerList.get(3) + integerList.get(4)), "Sabado"));
                sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2) + integerList.get(3) + integerList.get(4) + integerList.get(5)), "Domingo"));

                SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
                SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getActivity(), R.layout.section, R.id.section_text, mAdapter);
                mSectionedAdapter.setSections(sections.toArray(dummy));
                mRecyclerView.setAdapter(mSectionedAdapter);

                mRecyclerView.setVisibility(View.VISIBLE);
                progressBarReview.setVisibility(View.GONE);


            }
        });
    }

    private void setStreaming(RootSchedule response) {
        List<Integer> integerList = new ArrayList<>();
        List<Streaming> streamingList = new ArrayList<>();

        List<Program> programList = response.getWeek().getMonday().getPrograms().getProgramList();
        integerList.add(programList.size());
        for (int i = 0; i < programList.size(); i++) {
            Program monday = programList.get(i);
            Streaming streaming = new Streaming();
            streaming.setName(monday.getName());
            streaming.setPhoto(monday.getPhoto());
            streaming.setUrl(monday.getUrl());
            streaming.setStarHour(Config.getHour(monday.getHourStart()));
            streaming.setFinishHour(Config.getHour(monday.getHourFinish()));
            streaming.setSinopsis(monday.getSinapsis());
            streaming.setStarHourVenezuela(Config.getHourVenezuela(monday.getHourStart()));
            streaming.setFinishHourVenezuela(Config.getHourVenezuela(monday.getHourFinish()));
            streamingList.add(streaming);
        }

        List<Program> programListT = response.getWeek().getTuesday().getPrograms().getProgramList();
        integerList.add(programListT.size());
        for (int i = 0; i < programListT.size(); i++) {
            Program tuesday = programListT.get(i);
            Streaming streaming = new Streaming();
            streaming.setName(tuesday.getName());
            streaming.setPhoto(tuesday.getPhoto());
            streaming.setUrl(tuesday.getUrl());
            streaming.setStarHour(Config.getHour(tuesday.getHourStart()));
            streaming.setFinishHour(Config.getHour(tuesday.getHourFinish()));
            streaming.setSinopsis(tuesday.getSinapsis());
            streaming.setStarHourVenezuela(Config.getHourVenezuela(tuesday.getHourStart()));
            streaming.setFinishHourVenezuela(Config.getHourVenezuela(tuesday.getHourFinish()));
            streamingList.add(streaming);
        }

        List<Program> programListW = response.getWeek().getWednesday().getPrograms().getProgramList();
        integerList.add(programListW.size());
        for (int i = 0; i < programListW.size(); i++) {
            Program wednesday = programListW.get(i);
            Streaming streaming = new Streaming();
            streaming.setName(wednesday.getName());
            streaming.setPhoto(wednesday.getPhoto());
            streaming.setUrl(wednesday.getUrl());
            streaming.setStarHour(Config.getHour(wednesday.getHourStart()));
            streaming.setFinishHour(Config.getHour(wednesday.getHourFinish()));
            streaming.setSinopsis(wednesday.getSinapsis());
            streaming.setStarHourVenezuela(Config.getHourVenezuela(wednesday.getHourStart()));
            streaming.setFinishHourVenezuela(Config.getHourVenezuela(wednesday.getHourFinish()));
            streamingList.add(streaming);
        }

        List<Program> programListTT = response.getWeek().getThursday().getPrograms().getProgramList();
        integerList.add(programListTT.size());
        for (int i = 0; i < programListTT.size(); i++) {
            Program thursday = programListTT.get(i);
            Streaming streaming = new Streaming();
            streaming.setName(thursday.getName());
            streaming.setPhoto(thursday.getPhoto());
            streaming.setUrl(thursday.getUrl());
            streaming.setStarHour(Config.getHour(thursday.getHourStart()));
            streaming.setFinishHour(Config.getHour(thursday.getHourFinish()));
            streaming.setSinopsis(thursday.getSinapsis());
            streaming.setStarHourVenezuela(Config.getHourVenezuela(thursday.getHourStart()));
            streaming.setFinishHourVenezuela(Config.getHourVenezuela(thursday.getHourFinish()));
            streamingList.add(streaming);
        }

        List<Program> programListF = response.getWeek().getFriday().getPrograms().getProgramList();
        integerList.add(programListF.size());
        for (int i = 0; i < programListF.size(); i++) {
            Program friday = programListF.get(i);
            Streaming streaming = new Streaming();
            streaming.setName(friday.getName());
            streaming.setPhoto(friday.getPhoto());
            streaming.setUrl(friday.getUrl());
            streaming.setStarHour(Config.getHour(friday.getHourStart()));
            streaming.setFinishHour(Config.getHour(friday.getHourFinish()));
            streaming.setSinopsis(friday.getSinapsis());
            streaming.setStarHourVenezuela(Config.getHourVenezuela(friday.getHourStart()));
            streaming.setFinishHourVenezuela(Config.getHourVenezuela(friday.getHourFinish()));
            streamingList.add(streaming);
        }


        List<Program> programListS = response.getWeek().getSaturday().getPrograms().getProgramList();
        integerList.add(programListS.size());
        for (int i = 0; i < programListS.size(); i++) {
            Program saturday = programListS.get(i);
            Streaming streaming = new Streaming();
            streaming.setName(saturday.getName());
            streaming.setPhoto(saturday.getPhoto());
            streaming.setUrl(saturday.getUrl());
            streaming.setStarHour(Config.getHour(saturday.getHourStart()));
            streaming.setFinishHour(Config.getHour(saturday.getHourFinish()));
            streaming.setSinopsis(saturday.getSinapsis());
            streaming.setStarHourVenezuela(Config.getHourVenezuela(saturday.getHourStart()));
            streaming.setFinishHourVenezuela(Config.getHourVenezuela(saturday.getHourFinish()));
            streamingList.add(streaming);
        }


        List<Program> programListSS = response.getWeek().getSunday().getPrograms().getProgramList();
        integerList.add(programListSS.size());
        for (int i = 0; i < programListSS.size(); i++) {
            Program sunday = programListSS.get(i);
            Streaming streaming = new Streaming();
            streaming.setName(sunday.getName());
            streaming.setPhoto(sunday.getPhoto());
            streaming.setUrl(sunday.getUrl());
            streaming.setStarHour(Config.getHour(sunday.getHourStart()));
            streaming.setFinishHour(Config.getHour(sunday.getHourFinish()));
            streaming.setSinopsis(sunday.getSinapsis());
            streaming.setStarHourVenezuela(Config.getHourVenezuela(sunday.getHourStart()));
            streaming.setFinishHourVenezuela(Config.getHourVenezuela(sunday.getHourFinish()));
            streamingList.add(streaming);
        }


        updateUI(streamingList, integerList);

    }


}
