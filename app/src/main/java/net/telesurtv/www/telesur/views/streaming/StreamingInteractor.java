package net.telesurtv.www.telesur.views.streaming;

import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.streaming.Program;
import net.telesurtv.www.telesur.data.api.models.streaming.RootSchedule;
import net.telesurtv.www.telesur.model.Streaming;
import net.telesurtv.www.telesur.util.Config;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 04/11/15.
 */
public class StreamingInteractor {

    private TelesurApiService telesurApiService;


    private List<SimpleSectionedRecyclerViewAdapter.Section> sectionList = new ArrayList<>();
    private List<Streaming> streamingList = new ArrayList<>();

    public StreamingInteractor(TelesurApiService telesurApiService) {
        this.telesurApiService = telesurApiService;
    }


    public void getScheduleFromServer(TelesurStreamingCallBack telesurStreamingCallBack){

        telesurApiService.getSchedule()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RootViewSchedule -> {
                            sectionList = setStreamingSchedule(RootViewSchedule);
                            telesurStreamingCallBack.onLoaderVideos(streamingList,sectionList);
                        },
                        throwable -> {try{telesurStreamingCallBack.onFaliedToGetData(throwable.getMessage());}catch (Exception e){e.printStackTrace();}});

    }


    private  List<SimpleSectionedRecyclerViewAdapter.Section> setStreamingSchedule(RootSchedule response) {
        List<Program> programList = response.getWeek().getMonday().getPrograms().getProgramList();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(programList.size());
        for (int i = 0; i < programList.size(); i++) {
            Program monday = programList.get(i);
            System.out.println(monday.getName());
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

        List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0, "Lunes"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0)), "Martes"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1)), "Miercoles"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2)), "Jueves"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2) + integerList.get(3)), "Viernes"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2) + integerList.get(3) + integerList.get(4)), "Sabado"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section((integerList.get(0) + integerList.get(1) + integerList.get(2) + integerList.get(3) + integerList.get(4) + integerList.get(5)), "Domingo"));

        return sections;

    }
}
