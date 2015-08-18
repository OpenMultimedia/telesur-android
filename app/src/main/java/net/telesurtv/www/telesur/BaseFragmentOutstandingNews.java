package net.telesurtv.www.telesur;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.news.ParserNews;
import net.telesurtv.www.telesur.data.api.models.news.News;
import net.telesurtv.www.telesur.model.NewsViewModel;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.util.TimeAgo;
import net.telesurtv.www.telesur.views.adapter.RecyclerNewsOutstandingAdapter;
import net.telesurtv.www.telesur.views.news.NewsDetailActivity;
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
public abstract class BaseFragmentOutstandingNews extends Fragment implements SwipeRefreshLayout.OnRefreshListener,ItemRecyclerClickListenerNews, AppBarLayout.OnOffsetChangedListener {

    RecyclerView recyclerViewNewsList;
   SwipeRefreshLayout refreshLayoutNews;
    public RecyclerNewsOutstandingAdapter recyclerNewsAdapter;
    private AppBarLayout appBarLayout;
    List<NewsViewModel> newsViewModelArrayList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerNewsAdapter = new RecyclerNewsOutstandingAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_news, container, false);

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
        refreshLayoutNews = (SwipeRefreshLayout) view.findViewById(R.id.news_base_refresh);
        appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
    }


    /**
     * initialize recyclerView
     */
    protected void setupRecyclerView() {
        int spans = getResources().getInteger(R.integer.review_columns);
        final int one_span = getResources().getInteger(R.integer.show_span_1);
        final int two_span = getResources().getInteger(R.integer.show_span_2);


        GridLayoutManager manager = new GridLayoutManager(getActivity(), spans);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return (position % 5 == 0 ? two_span : one_span);
            }
        });


        // recyclerViewNewsList.setLayoutManager(new GridLayoutManager(getActivity(),spans));
        recyclerViewNewsList.setLayoutManager(manager);
        recyclerViewNewsList.addItemDecoration(new ItemOffsetDecoration(recyclerViewNewsList.getContext(), R.dimen.item_decoration));
        recyclerViewNewsList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNewsList.setAdapter(recyclerNewsAdapter);
        recyclerNewsAdapter.setItemRecyclerClickListenerNews(this);
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
    private void updateUI(final List<NewsViewModel> newsViewModelList) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerNewsAdapter.clear();
                recyclerNewsAdapter.setListItems(newsViewModelList);


                TimeAgo timeAgo = new TimeAgo(getResources());

                NewsViewModel newsViewModels = new NewsViewModel();
                newsViewModels.setImgNews("http://www.thegooru.com/wp-content/uploads/google-logo.jpg");
                newsViewModels.setTitleNews("soy un item agregado");
                newsViewModels.setDataNews(timeAgo.timeAgo(Config.parserToUseTimeAgo("Mon, 13 Aug 2015 13:42:00")));
                recyclerNewsAdapter.addItem(newsViewModels);

                refreshLayoutNews.setRefreshing(false);
                refreshLayoutNews.setEnabled(false);
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
                            System.out.println("Request a la API" + writer.toString());

                            News[] listNews = ParserNews.getListNews(writer.toString());
                            newsViewModelArrayList.clear();
                            for (int i = 0; i < listNews.length; i++) {

                                News notice = listNews[i];

                                NewsViewModel newsViewModel = new NewsViewModel();
                                newsViewModel.setImgNews(notice.getEnclosure().getUrl());
                                newsViewModel.setTitleNews(notice.getTitle());
                                newsViewModel.setCategoryNews(notice.getCategory());
                                newsViewModel.setAuthorNews(notice.getAuthor());
                                newsViewModel.setDescriptionNews(notice.getDescription());
                                newsViewModel.setContentNews(notice.getContent());




                                if (notice.getLastTime() != null) {
                                    TimeAgo timeAgo = new TimeAgo(getResources());
                                    newsViewModel.setDataNews(timeAgo.timeAgo(Config.parserToUseTimeAgo(notice.getLastTime())));

                                } else {
                                    newsViewModel.setDataNews("");
                                }


                                newsViewModelArrayList.add(newsViewModel);

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
    public void itemRecycleOnClickNews(int position, NewsViewModel newsViewModel) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("news",getItem(newsViewModel));
        intent.putExtra("news_section",getTitleSection());
        startActivity(intent);
    }



    private Bundle getItem(NewsViewModel newsViewModel) {
        Bundle bundle = new Bundle();
        bundle.putString("news_title", newsViewModel.getTitleNews());
        bundle.putString("news_description", newsViewModel.getDescriptionNews());
        bundle.putString("news_author", newsViewModel.getAuthorNews());
        bundle.putString("news_content", newsViewModel.getContentNews());
        bundle.putString("news_category", newsViewModel.getCategoryNews());
        bundle.putString("news_image", newsViewModel.getImgNews());
        bundle.putString("news_date", newsViewModel.getDataNews());
        return bundle;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        refreshLayoutNews.setEnabled(i == 0);
    }
}
