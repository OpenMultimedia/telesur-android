package net.telesurtv.www.telesur.views.videos.video;

import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.video.Videos;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.util.Config;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 29/10/15.
 */
public class VideoInteractor {

    private TelesurApiService telesurApiService;

    public VideoInteractor(TelesurApiService telesurApiService) {
        this.telesurApiService = telesurApiService;
    }

    public void getVideosFromServer(String section,int initQuery, int lastQuery, TelesurVideosCallback telesurVideosCallback) {
        telesurApiService.getVideoList("completo", initQuery, lastQuery, section)
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

            if (videos.getCategoria() == null)
                video.setCategory("teleSUR");
            else
                video.setCategory(videos.getCategoria().getNombre());

            video.setVisitCounter(videos.getEstadisticas().getVistas());
            video.setVideoURL(videos.getArchivo_url());

            videoViewModels.add(video);


        }

        return videoViewModels;
    }
}
