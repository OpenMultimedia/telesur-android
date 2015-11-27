package net.telesurtv.www.telesur.views.news;

import android.content.Context;
import android.content.Intent;

import net.telesurtv.www.telesur.data.TelesurApiConstants;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.review.Item;
import net.telesurtv.www.telesur.model.NewsViewModel;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.util.TimeAgo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 29/10/15.
 */
public class NewsInteractor {

    Context context;
    private TelesurApiService telesurApiService;

    public NewsInteractor(TelesurApiService telesurApiService, Context context) {
        this.telesurApiService = telesurApiService;
        this.context = context;
    }

    public void getNewsFromServer(String section, TelesurNewsCallback telesurNewsCallback) {

        telesurApiService.getNewsList(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Reviews -> {telesurNewsCallback.onLoaderNews(setViewModelArrayList(Reviews.getRss().getChannel().getItem(),section));},
                        throwable -> {
                            try {
                                telesurNewsCallback.onFaliedToGetData(throwable.getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
    }


    private ArrayList<NewsViewModel> setViewModelArrayList(List<Item> items, String section) {

        ArrayList<NewsViewModel> newsViewModelArrayList = new ArrayList<>();


        if(section.equals(TelesurApiConstants.RSS_OUSTANDING)){
            TimeAgo timeAgo = new TimeAgo(context.getResources());
            NewsViewModel newsViewModels = new NewsViewModel();
            newsViewModels.setImgNews("http://www.thegooru.com/wp-content/uploads/google-logo.jpg");
            newsViewModels.setTitleNews("soy un item agregado");
            newsViewModels.setDataNews(timeAgo.timeAgo(Config.parserToUseTimeAgo("Mon, 13 Aug 2015 13:42:00")));
        }

        for (int i = 0; i < items.size(); i++) {

            Item item = items.get(i);
            NewsViewModel newsViewModel = new NewsViewModel();

            if (item.getEnclosure() != null)
                newsViewModel.setImgNews(item.getEnclosure().getUrl());
            else
                newsViewModel.setImgNews("http://www.telesurtv.net/arte/LogoBlanco648X351.jpg");


            newsViewModel.setTitleNews(item.getTitle());
            newsViewModel.setCategoryNews(item.getCategory());
//                newsViewModel.setAuthorNews(notice.getAuthor().toString());
            newsViewModel.setDescriptionNews(item.getDescription());
            newsViewModel.setContentNews(item.getContent());
            newsViewModel.setLinkNews(item.getLink());




            if (item.getLastTime() != null) {
                TimeAgo timeAgo = new TimeAgo(context.getResources());
                newsViewModel.setDataNews(timeAgo.timeAgo(Config.parserToUseTimeAgo(item.getLastTime())));

            } else {
                newsViewModel.setDataNews("");
            }


            newsViewModelArrayList.add(newsViewModel);




        }
        return newsViewModelArrayList;
    }

}

