package net.telesurtv.www.telesur.views.news;

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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.NewsViewModel;
import net.telesurtv.www.telesur.util.Config;
import net.telesurtv.www.telesur.util.Theme;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jhordan on 29/10/15.
 */
public abstract class BaseNewsFragment extends Fragment implements NewsMVPView ,
        ItemRecyclerClickListenerNews,AppBarLayout.OnOffsetChangedListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.srl_recycler) SwipeRefreshLayout srl_news;
    @Bind(R.id.rv_recycler)  RecyclerView rv_news;
    @Bind(R.id.pb_recycler)  ProgressBar pv_news;
    @Bind(R.id.txt_recycler) TextView txt_news;
    @Bind(R.id.iv_recycler)  ImageView iv_news;

    private NewsPresenter newsPresenter;
    private RecyclerView.Adapter newsAdapter;
    private AppBarLayout appBarLayout;

    protected abstract String getSection();
    protected abstract String getTitleSection();
    protected abstract String themeSection();
    protected abstract RecyclerView.LayoutManager getLayoutManager();
    protected abstract RecyclerView.Adapter getAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_news_o, container, false);
        ButterKnife.bind(this, rootView);
        newsPresenter = new NewsPresenter(getActivity());
        newsPresenter.attachedView(this);
        intializeRecyclerView();
        initializeSwipeRefreshLayout();
        newsPresenter.setSection(getSection());
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void showReviewList(ArrayList<NewsViewModel> newsViewModels) {
        newsAdapter = getAdapter();
        if(newsAdapter instanceof RecyclerNewsAdapter){
            ((RecyclerNewsAdapter) newsAdapter).setListItems(newsViewModels);
            ((RecyclerNewsAdapter) newsAdapter).setItemRecyclerClickListenerNews(this);
        }else{
            ((RecyclerNewsOutstandingAdapter)newsAdapter).setListItems(newsViewModels);
            ((RecyclerNewsOutstandingAdapter) newsAdapter).setItemRecyclerClickListenerNews(this);
        }

        rv_news.setAdapter(newsAdapter);
    }

    @Override
    public void showProgress() {
        srl_news.setVisibility(View.GONE);
        rv_news.setVisibility(View.GONE);
        pv_news.setVisibility(View.VISIBLE);
        txt_news.setVisibility(View.VISIBLE);
        iv_news.setVisibility(View.GONE);
        txt_news.setText(getString(R.string.loading_message));
    }

    @Override
    public void hideProgress() {
        pv_news.setVisibility(View.GONE);
        txt_news.setVisibility(View.GONE);
        iv_news.setVisibility(View.GONE);
        srl_news.setVisibility(View.VISIBLE);
        rv_news.setVisibility(View.VISIBLE);
    }

    @Override
    public void showConnectionErrorMessage() {
        pv_news.setVisibility(View.GONE);
        txt_news.setVisibility(View.VISIBLE);
        iv_news.setVisibility(View.VISIBLE);
        srl_news.setVisibility(View.GONE);
        txt_news.setText(getString(R.string.expected_error_wifi));
        iv_news.setImageResource(R.mipmap.not_server);
    }

    @Override
    public void showUnknownErrorMessage() {
        pv_news.setVisibility(View.GONE);
        txt_news.setVisibility(View.VISIBLE);
        iv_news.setVisibility(View.VISIBLE);
        srl_news.setVisibility(View.GONE);
        txt_news.setText(getString(R.string.expected_error_token));
        iv_news.setImageResource(R.mipmap.ic_uknow_error);
    }

    @Override public void showProgressRefresh() {
        srl_news.setRefreshing(true);
    }

    @Override public void hideProgressRefresh() {
        if(srl_news.isRefreshing())
            srl_news.setRefreshing(false);

        srl_news.setVisibility(View.VISIBLE);
    }

    @Override
    public void launchDetail(int position, NewsViewModel newsViewModel, View imageView) {

        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("news", getItem(newsViewModel));
        intent.putExtra("news_section", getTitleSection());
        intent.putExtra("news_themes", themeSection());


        Pair<View, String> pairImage = Pair.create(imageView, getString(R.string.transition_image_view));
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairImage);
        if (Build.VERSION.SDK_INT >= 16)
            getActivity().startActivity(intent, optionsCompat.toBundle());

    }

    @Override
    public void onDestroy() {
        newsPresenter.detachView();
        super.onDestroy();
    }

    private void intializeRecyclerView() {
        rv_news.setLayoutManager(getLayoutManager());
        rv_news.addItemDecoration(new ItemOffsetDecoration(rv_news.getContext(), R.dimen.item_decoration));
        rv_news.setItemAnimator(new DefaultItemAnimator());
    }

    private void initializeSwipeRefreshLayout() {
        appBarLayout = (AppBarLayout)getActivity().findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
        Theme theme = Theme.valueOf(themeSection());
        srl_news.setColorSchemeResources(theme.getColorPrimary(), theme.getWindowBackground());
        srl_news.setOnRefreshListener(this);
        srl_news.setEnabled(false);

    }

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

    @Override
    public void itemRecycleOnClickNews(int position, NewsViewModel newsViewModel, View image) {
        newsPresenter.onItemSelected(position,newsViewModel,image);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if(i == 0)
            srl_news.setEnabled(true);
        else
            srl_news.setEnabled(false);
    }

    @Override
    public void onRefresh() {
        newsPresenter.isRefreshListener(srl_news.isRefreshing(), getSection());
    }
}
