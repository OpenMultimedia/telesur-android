package net.telesurtv.www.telesur;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.EndPoint;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.news.News;
import net.telesurtv.www.telesur.data.api.models.news.ParserNews;
import net.telesurtv.www.telesur.model.NewsViewModel;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.util.Theme;
import net.telesurtv.www.telesur.util.TimeAgo;
import net.telesurtv.www.telesur.views.news.NewsDetailActivity;
import net.telesurtv.www.telesur.views.news.RecyclerNewsAdapter;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

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
public abstract class BaseFragmentNews extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ItemRecyclerClickListenerNews, AppBarLayout.OnOffsetChangedListener {


    public final static String THEME_OUSTANDING = "news";
    public final static String THEME_LATAM = "interview";
    public final static String THEME_WORLD = "documental";
    public final static String THEME_SPORT = "infografi";
    public final static String THEME_CULTURE = "special";


    private RecyclerView recyclerViewNewsList;
    private SwipeRefreshLayout refreshLayoutNews;
    public RecyclerNewsAdapter recyclerNewsAdapter;
    private AppBarLayout appBarLayout;
    private ProgressBar progressBarNews;
    private TextView txtMessage;
    private List<NewsViewModel> newsViewModelArrayList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerNewsAdapter = new RecyclerNewsAdapter();
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_news, container, false);
        initializeViews(rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupRefreshLayout();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {


        outState.putString("download_news", getAdapterItems());
        super.onSaveInstanceState(outState);
    }

    private String getAdapterItems() {
        return new GsonBuilder().create().toJson(recyclerNewsAdapter.getNewsViewModelListItems(),
                new TypeToken<List<NewsViewModel>>() {
                }.getType());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.getString("download_news") != null) {
                recyclerNewsAdapter.setListItems(getNewsListSavedInstance(savedInstanceState));

            }
        }
    }

    private List<NewsViewModel> getNewsListSavedInstance(Bundle savedInstanceState) {
        return new GsonBuilder().create().fromJson(savedInstanceState.getString("download_news"), new TypeToken<List<NewsViewModel>>() {
        }.getType());
    }


    /**
     * This method make a request to the server
     *
     * @param section news section to download
     */
    private void getListNews(String section) {

        progressBarNews.setVisibility(View.VISIBLE);
        TelesurApiService telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        telesurApiService.getNewsList(section).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<Response>() {
                    @Override
                    public void call(Response response) {


                        if (response.getStatus() == EndPoint.REQUEST_SUCCES)
                            updateUI(setValuesToViewModel(response, newsViewModelArrayList));
                        else
                            progressBarNews.setVisibility(View.GONE);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }


    /**
     * @param newsViewModelList videoList to update UI
     */
    private void updateUI(final List<NewsViewModel> newsViewModelList) {

        if (newsViewModelList.size() > 0) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerNewsAdapter.clear();
                    recyclerNewsAdapter.setListItems(newsViewModelList);
                    progressBarNews.setVisibility(View.GONE);
                    refreshLayoutNews.setVisibility(View.VISIBLE);
                    refreshLayoutNews.setRefreshing(false);
                    refreshLayoutNews.setEnabled(false);


                }
            });

        } else {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerNewsAdapter.clear();
                    refreshLayoutNews.setVisibility(View.GONE);
                    progressBarNews.setVisibility(View.GONE);
                    refreshLayoutNews.setRefreshing(false);
                    txtMessage.setVisibility(View.VISIBLE);
                    txtMessage.setText("Not found result");
                    refreshLayoutNews.setEnabled(false);

                }
            });

        }


    }


    private List<NewsViewModel> setValuesToViewModel(Response response, List<NewsViewModel> newsViewModelArrayList) {

        try {

            final StringWriter writer = new StringWriter();
            IOUtils.copy(response.getBody().in(), writer, EndPoint.UTF_8);
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
                newsViewModel.setLinkNews(notice.getLink());


                if (notice.getLastTime() != null) {
                    TimeAgo timeAgo = new TimeAgo(getResources());
                    newsViewModel.setDataNews(timeAgo.timeAgo(Config.parserToUseTimeAgo(notice.getLastTime())));

                } else {
                    newsViewModel.setDataNews("");
                }


                newsViewModelArrayList.add(newsViewModel);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsViewModelArrayList;
    }

    /**
     * appBarLayout is remove the listener
     */
    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    /* Override this method to get expandible state of AppBarLayout
     * if i = 0 the AppBarLayout is expandable
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        refreshLayoutNews.setEnabled(i == 0);


    }


    /**
     * refresh make the request on more time
     */
    @Override
    public void onRefresh() {
        getListNews(getSection());
    }


    /**
     * interface Item Listener
     *
     * @param position      of item
     * @param newsViewModel Model according to position
     */

    @Override
    public void itemRecycleOnClickNews(int position, NewsViewModel newsViewModel, View imageView) {

        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("news", getItem(newsViewModel));
        intent.putExtra("news_section", getTitleSection());
        intent.putExtra("news_themes", themeSection());


        Pair<View, String> pairImage = Pair.create(imageView, getString(R.string.transition_image_view));
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairImage);
        if (Build.VERSION.SDK_INT >= 16)
            getActivity().startActivity(intent, optionsCompat.toBundle());
    }


    /**
     * Bundle params to sent detail. I WAS A LAZY THIS CODE SHOULD BE PARSELABLE
     *
     * @param newsViewModel
     * @return
     */
    private Bundle getItem(NewsViewModel newsViewModel) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.NEWS_TITLE, newsViewModel.getTitleNews());
        bundle.putString(Config.NEWS_DESCRIPTION, newsViewModel.getDescriptionNews());
        bundle.putString(Config.NEWS_AUTHOR, newsViewModel.getAuthorNews());
        bundle.putString(Config.NEWS_CONTENT, newsViewModel.getContentNews());
        bundle.putString(Config.NEWS_CATEGORY, newsViewModel.getCategoryNews());
        bundle.putString(Config.NEWS_IMAGE, newsViewModel.getImgNews());
        bundle.putString(Config.NEWS_DATE, newsViewModel.getDataNews());
        bundle.putString(Config.NEWS_LINK, newsViewModel.getLinkNews());
        return bundle;
    }


    // initialize recyclerView
    protected void setupRecyclerView() {

        final int spans = getResources().getInteger(R.integer.review_columns);
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

    // initialize swipeRefreshLayout according to section
    private void setupRefreshLayout() {
        Theme theme = Theme.valueOf(themeSection());
        refreshLayoutNews.setColorSchemeResources(theme.getColorPrimary(), theme.getWindowBackground());
        refreshLayoutNews.setOnRefreshListener(this);
        refreshLayoutNews.setEnabled(false);

    }

    /**
     * get the views from fragment
     *
     * @param view context of views where is conteiner child views
     */
    public void initializeViews(View view) {
        recyclerViewNewsList = (RecyclerView) view.findViewById(R.id.news_recycler);
        refreshLayoutNews = (SwipeRefreshLayout) view.findViewById(R.id.news_base_refresh);
        appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appbar);
        progressBarNews = (ProgressBar) view.findViewById(R.id.progress_bar_news);
        txtMessage = (TextView) view.findViewById(R.id.txt_message_news);
        appBarLayout.addOnOffsetChangedListener(this);
        progressBarNews.setVisibility(View.VISIBLE);
        txtMessage.setVisibility(view.GONE);
        refreshLayoutNews.setVisibility(View.GONE);
    }

    // get kind of section
    protected abstract String getSection();

    // get title of section
    protected abstract String getTitleSection();

    // get theme of section
    protected abstract String themeSection();


}
