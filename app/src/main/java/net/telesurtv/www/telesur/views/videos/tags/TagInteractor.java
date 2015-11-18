package net.telesurtv.www.telesur.views.videos.tags;

import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.video.Videos;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.views.videos.video.TelesurVideosCallback;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 29/10/15.
 */
public class TagInteractor {

    private TelesurApiService telesurApiService;

    public TagInteractor(TelesurApiService telesurApiService) {
        this.telesurApiService = telesurApiService;
    }

    public void getVideosFromServer(String section,int initQuery, int lastQuery, TelesurVideosCallback telesurVideosCallback) {
        telesurApiService.getVideoList("completo", initQuery, lastQuery, section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videosArrayList -> {telesurVideosCallback.onLoaderVideos(setVideos(videosArrayList));},
                        throwable -> {try {telesurVideosCallback.onFaliedToGetData(throwable.getMessage());} catch (Exception e) {e.printStackTrace();}});

    }

    public void getTagCorresponsal(String section,String corresponsal,int initQuery, int lastQuery, TelesurVideosCallback telesurVideosCallback) {
        telesurApiService.getTagListCorresponsal("completo", initQuery, lastQuery, section, corresponsal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videosArrayList -> {telesurVideosCallback.onLoaderVideos(setVideos(videosArrayList));},
                        throwable -> {try {telesurVideosCallback.onFaliedToGetData(throwable.getMessage());} catch (Exception e) {e.printStackTrace();}});

    }


    public void getTagTopic(String section,String topic,int initQuery, int lastQuery, TelesurVideosCallback telesurVideosCallback) {
        telesurApiService.getTagListTopic("completo", initQuery, lastQuery, section, topic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videosArrayList -> {telesurVideosCallback.onLoaderVideos(setVideos(videosArrayList));},
                        throwable -> {try {telesurVideosCallback.onFaliedToGetData(throwable.getMessage());} catch (Exception e) {e.printStackTrace();}});

    }


    public void getTagCountry(String section,String country,int initQuery, int lastQuery, TelesurVideosCallback telesurVideosCallback) {
        telesurApiService.getTagListCountry("completo", initQuery, lastQuery, section, country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videosArrayList -> {telesurVideosCallback.onLoaderVideos(setVideos(videosArrayList));},
                        throwable -> {try {telesurVideosCallback.onFaliedToGetData(throwable.getMessage());} catch (Exception e) {e.printStackTrace();}});

    }


    public void getTagCategory(String section,String category,int initQuery, int lastQuery, TelesurVideosCallback telesurVideosCallback) {
        telesurApiService.getTagListCategory("completo", initQuery, lastQuery, section, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videosArrayList -> {telesurVideosCallback.onLoaderVideos(setVideos(videosArrayList));},
                        throwable -> {try {telesurVideosCallback.onFaliedToGetData(throwable.getMessage());} catch (Exception e) {e.printStackTrace();}});

    }




    private ArrayList<VideoViewModel> setVideos(ArrayList<Videos> videosArrayList) {

        ArrayList<VideoViewModel> videoViewModels = new ArrayList<>();

        for (int i = 0; i < videosArrayList.size(); i++) {

            Videos videos = videosArrayList.get(i);

            VideoViewModel video = new VideoViewModel();
            video.setTitle(videos.getTitulo());
            video.setBackground(videos.getThumbnail_grande());
            video.setDuration(videos.getDuracion());
            video.setData(Config.date_to_human(videos.getFecha()));
            video.setDescription(videos.getDescripcion());
            video.setLinkVideoNavegator(videos.getNavegatorURL());


            if(videos.getCorresponsal() == null){
                video.setCorresponsalName("");
                video.setCorresponsalSlug("");
            }else{
                video.setCorresponsalName(videos.getCorresponsal().getNombre());
                video.setCorresponsalSlug(videos.getCorresponsal().getSlug());
            }


            if(videos.getCountry() == null)
                video.setCorresponsalContrySlug("");
            else
                video.setCorresponsalContrySlug(videos.getCountry().getSlug());


            if(videos.getTopic() == null)
                video.setTopicSlug("");
            else
                video.setTopicSlug(videos.getTopic().getSlug());


            if (videos.getCategoria() == null){
                video.setCategory("Telesur");
                video.setCategorySlug("");
            }

            else{
                video.setCategorySlug(videos.getCategoria().getSlug());
                video.setCategory(videos.getCategoria().getNombre());

            }


            video.setVisitCounter(videos.getEstadisticas().getVistas());
            video.setVideoURL(videos.getArchivo_url());

            videoViewModels.add(video);


        }

        return videoViewModels;
    }
}
