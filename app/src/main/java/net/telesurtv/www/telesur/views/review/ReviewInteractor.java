package net.telesurtv.www.telesur.views.review;

import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.review.Item;
import net.telesurtv.www.telesur.model.ReviewViewModel;
import net.telesurtv.www.telesur.util.Config;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 27/10/15.
 */
public class ReviewInteractor {

    private TelesurApiService telesurApiService;

    public ReviewInteractor(TelesurApiService telesurApiService) {
        this.telesurApiService = telesurApiService;
    }

    public void getNewsFromServer(String section, TelesurReviewCallback telesurReviewCallback) {

        telesurApiService.getNewsList(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Reviews -> {telesurReviewCallback.onLoaderNews(setViewModelArrayList(Reviews.getRss().getChannel().getItem()));},
                           throwable -> {try{telesurReviewCallback.onFaliedToGetData(throwable.getMessage());}catch (Exception e){e.printStackTrace();}});
    }


    private ArrayList<ReviewViewModel> setViewModelArrayList(List<Item> items) {
        ArrayList<ReviewViewModel> newsViewModelArrayList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {

            Item item = items.get(i);

            ReviewViewModel reviewViewModel = new ReviewViewModel();

            reviewViewModel.setTitle(item.getTitle());
            reviewViewModel.setLink(item.getLink());

            if (item.getEnclosure() != null)
                reviewViewModel.setImageUrl(item.getEnclosure().getUrl());
            else
                reviewViewModel.setImageUrl("http://www.telesurtv.net/arte/LogoBlanco648X351.jpg");


            if (item.getAuthor().toString().startsWith("["))
                reviewViewModel.setAuthor(item.getAuthor().toString().substring(item.getAuthor().toString().indexOf("[") + 1, item.getAuthor().toString().indexOf("]")));
            else
                reviewViewModel.setAuthor(item.getAuthor().toString());


            if (item.getDescription() != null)
                reviewViewModel.setDescription(item.getDescription());
            else
                reviewViewModel.setDescription("Sin descripción");
            reviewViewModel.setContent(item.getContent());

            if (item.getLastTime() != null)
                reviewViewModel.setDate(Config.getDateFormated(item.getLastTime()));
            else
                reviewViewModel.setDate("");

            newsViewModelArrayList.add(reviewViewModel);

        }
        return newsViewModelArrayList;

    }

}
