package net.telesurtv.www.telesur;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.news.News;
import net.telesurtv.www.telesur.data.api.models.news.ParserNews;
import net.telesurtv.www.telesur.model.ReviewViewModel;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.views.adapter.RecyclerReviewAdapter;
import net.telesurtv.www.telesur.views.review.ReviewDetailActivity;
import net.telesurtv.www.telesur.views.view.DelegatedSwipeRefreshLayout;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;
import net.telesurtv.www.telesur.views.view.ViewDelegate;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 28/07/15.
 */
public abstract class BaseFragmentReview extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ItemRecyclerClickListenerReview {

    RecyclerView recyclerViewNewsList;
    SwipeRefreshLayout refreshLayoutNews;
    public RecyclerReviewAdapter recyclerReviewAdapter;
    List<ReviewViewModel> newsViewModelArrayList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerReviewAdapter = new RecyclerReviewAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_review, container, false);

        setUpViews(rootView);
        setupRecyclerView();
        setupRefreshLayout();


        return rootView;
    }


    /**
     * getVies from xml
     *
     * @param view rootView fragment
     */
    public void setUpViews(View view) {
        recyclerViewNewsList = (RecyclerView) view.findViewById(R.id.news_recycler);
        refreshLayoutNews = (SwipeRefreshLayout) view.findViewById(R.id.review_base_refresh);


    }


    /**
     * initialize recyclerView
     */
    protected void setupRecyclerView() {
        int spans = getResources().getInteger(R.integer.review_columns);
        recyclerViewNewsList.setLayoutManager(new StaggeredGridLayoutManager(spans, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewNewsList.addItemDecoration(new ItemOffsetDecoration(recyclerViewNewsList.getContext(), R.dimen.item_decoration));
        recyclerViewNewsList.setItemAnimator(new DefaultItemAnimator());
        recyclerReviewAdapter.setItemRecyclerClickListener(this);
        recyclerViewNewsList.setAdapter(recyclerReviewAdapter);
        getListNews(getSection());
    }


    /**
     * initialize swipeRefreshLayout
     */
    protected void setupRefreshLayout() {
        refreshLayoutNews.setColorSchemeResources(R.color.primaryDark, R.color.primary);
        refreshLayoutNews.setOnRefreshListener(this);

    }


    @Override
    public void onRefresh() {


        getListNews(getSection());
    }


    /**
     * @param newsViewModelList videoList to update UI
     */
    private void updateUI(final List<ReviewViewModel> newsViewModelList) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerReviewAdapter.clear();
                recyclerReviewAdapter.setListItems(newsViewModelList);
                refreshLayoutNews.setRefreshing(false);

            }
        });
    }

    /**
     * This method make a request to the server
     *
     * @param section video section to download
     */


    private void getListNews(String section) {

        TelesurApiService telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        telesurApiService.getNewsList(section).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<Response>() {
                    @Override
                    public void call(Response response) {


                        final StringWriter writer = new StringWriter();
                        try {
                            IOUtils.copy(response.getBody().in(), writer, "UTF-8");

                            News[] listNews = ParserNews.getListNews(writer.toString());
                            newsViewModelArrayList.clear();
                            for (int i = 0; i < listNews.length; i++) {

                                News notice = listNews[i];

                                ReviewViewModel reviewViewModel = new ReviewViewModel();
                                reviewViewModel.setImageUrl(notice.getThumbnails().getUrl());
                                reviewViewModel.setAuthor(new String(notice.getAuthor().getBytes("UTF-16"), "UTF-8"));
                                reviewViewModel.setTitle(notice.getTitle());
                                reviewViewModel.setAuthor(notice.getAuthor());

                                if (notice.getDescription() != null)
                                    reviewViewModel.setDescription(notice.getDescription());
                                else
                                    reviewViewModel.setDescription("Sin descripción");
                                reviewViewModel.setContent(notice.getContent());

                                if (notice.getLastTime() != null)
                                    reviewViewModel.setDate(Config.getDateFormated(notice.getLastTime()));
                                else
                                    reviewViewModel.setDate("");


                                newsViewModelArrayList.add(reviewViewModel);

                                //  System.out.println(new String(notice.getEnclosure().getText().getBytes("UTF-8"), "UTF-8"));
                                //   System.out.println(new String(notice.getDescription().getBytes("US-ASCII"), "UTF-8"));
                                //  System.out.println(new String(notice.getContent().getBytes("UTF-8"), "UTF-8"));

                            }

                            updateUI(newsViewModelArrayList);

                            // NewsParser[] listNews = new GsonBuilder().create().fromJson(writer.toString(), NewsParser[].class);

                            //   System.out.println(listNews[0].getTitle());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        throwable.printStackTrace();
                    }
                });


    }


    /**
     * This method return the section according to the fragment
     *
     * @return
     */
    protected abstract String getSection();

    protected abstract String getTitleSection();


    @Override
    public void itemRecycleOnClickReview(int position, ReviewViewModel reviewViewModel) {

        Intent intent = new Intent(getActivity(), ReviewDetailActivity.class);
        intent.putExtra("review_news", getItem(reviewViewModel));
        intent.putExtra("review_title", getTitleSection());
        startActivity(intent);
    }


    private Bundle getItem(ReviewViewModel reviewViewModel) {
        Bundle bundle = new Bundle();
        bundle.putString("review_title", reviewViewModel.getTitle());
        bundle.putString("review_description", reviewViewModel.getDescription());
        bundle.putString("review_author", reviewViewModel.getAuthor());
        bundle.putString("review_content", reviewViewModel.getContent());
        bundle.putString("review_image", reviewViewModel.getImageUrl());
        bundle.putString("review_date", reviewViewModel.getDate());
        return bundle;
    }
}
