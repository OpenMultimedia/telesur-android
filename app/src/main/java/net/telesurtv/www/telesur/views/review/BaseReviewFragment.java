package net.telesurtv.www.telesur.views.review;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.ReviewViewModel;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jhordan on 27/10/15.
 */
public abstract class BaseReviewFragment extends Fragment implements ReviewMvpView ,SwipeRefreshLayout.OnRefreshListener,ItemRecyclerClickListenerReview {

    @Bind(R.id.srl_recycler) SwipeRefreshLayout srl_review;
    @Bind(R.id.rv_recycler)  RecyclerView rv_review;
    @Bind(R.id.pb_recycler)  ProgressBar pv_review;
    @Bind(R.id.txt_recycler) TextView txt_review;
    @Bind(R.id.iv_recycler)  ImageView iv_review;

    private ReviewPresenter reviewPresenter;
    private RecyclerReviewAdapter reviewAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    protected abstract String getSection();
    protected abstract String getTitleSection();

    public static final String LOG_TAG = BaseReviewFragment.class.getSimpleName();

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewAdapter = new RecyclerReviewAdapter();
        reviewAdapter.setItemRecyclerClickListener(this);
    }

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_recycler, container, false);
        ButterKnife.bind(this, rootView);
        reviewPresenter = new ReviewPresenter();
        reviewPresenter.attachedView(this);
        initializeRecyclerView();
        initializeSwipeRefreshLayout();
        reviewPresenter.setSection(getSection());
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override public void showReviewList(ArrayList<ReviewViewModel> reviewViewModelArrayList) {
        reviewAdapter.setListItems(reviewViewModelArrayList);
        rv_review.setAdapter(reviewAdapter);

    }

    @Override public void showProgress() {
        srl_review.setVisibility(View.GONE);
        rv_review.setVisibility(View.GONE);
        pv_review.setVisibility(View.VISIBLE);
        txt_review.setVisibility(View.VISIBLE);
        iv_review.setVisibility(View.GONE);
        txt_review.setText(getString(R.string.loading_message));
    }

    @Override public void hideProgress() {
        pv_review.setVisibility(View.GONE);
        txt_review.setVisibility(View.GONE);
        iv_review.setVisibility(View.GONE);
        srl_review.setVisibility(View.VISIBLE);
        rv_review.setVisibility(View.VISIBLE);
    }

    @Override public void showProgressRefresh() {
        srl_review.setRefreshing(true);
    }

    @Override public void hideProgressRefresh() {
        if(srl_review.isRefreshing())
            srl_review.setRefreshing(false);

        srl_review.setVisibility(View.VISIBLE);
        rv_review.setVisibility(View.VISIBLE);
    }

    @Override public void showConnectionErrorMessage() {
        pv_review.setVisibility(View.GONE);
        txt_review.setVisibility(View.VISIBLE);
        iv_review.setVisibility(View.VISIBLE);
        srl_review.setVisibility(View.GONE);
        txt_review.setText(getString(R.string.expected_error_wifi));
        iv_review.setImageResource(R.mipmap.not_server);
    }

    @Override public void showUnknownErrorMessage() {
        pv_review.setVisibility(View.GONE);
        txt_review.setVisibility(View.VISIBLE);
        iv_review.setVisibility(View.VISIBLE);
        srl_review.setVisibility(View.GONE);
        txt_review.setText(getString(R.string.expected_error_token));
        iv_review.setImageResource(R.mipmap.ic_uknow_error);
    }


    @Override public void onDestroy() {
        reviewPresenter.detachView();
        super.onDestroy();
    }

    @Override public void onRefresh() {
        reviewPresenter.isRefreshListener(srl_review.isRefreshing(), getSection());
    }

    @Override public void itemRecycleOnClickReview(int position, ReviewViewModel reviewViewModel, View imageView) {
        reviewPresenter.onItemSelected(position, reviewViewModel, imageView);
    }

    @Override public void launchDetail(ReviewViewModel reviewViewModel,View imageView) {
        Intent intent = new Intent(getActivity(), ReviewDetailActivity.class);
        intent.putExtra("review_news", getItem(reviewViewModel));
        intent.putExtra("review_title", getTitleSection());
        startActivity(intent);

        //Pair<View, String> pairImage = Pair.create(imageView, getString(R.string.transition_image_view));
       // ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairImage);
        //if (Build.VERSION.SDK_INT >= 16)
           // getActivity().startActivity(intent, optionsCompat.toBundle());
    }

    public void initializeRecyclerView() {
       // int spans = getResources().getInteger(R.integer.review_columns);
       // staggeredGridLayoutManager = new StaggeredGridLayoutManager(spans, StaggeredGridLayoutManager.VERTICAL);
       // staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
       // rv_review.setLayoutManager(staggeredGridLayoutManager);
        rv_review.addItemDecoration(new ItemOffsetDecoration(rv_review.getContext(), R.dimen.item_decoration));
        rv_review.setItemAnimator(new DefaultItemAnimator());
        //rv_review.setHasFixedSize(false);

    }

    public void initializeSwipeRefreshLayout() {
        srl_review.setColorSchemeResources(R.color.primaryDark, R.color.primary);
        srl_review.setOnRefreshListener(this);
    }

    private Bundle getItem(ReviewViewModel reviewViewModel) {
        Bundle bundle = new Bundle();
        bundle.putString("review_title", reviewViewModel.getTitle());
        bundle.putString("review_description", reviewViewModel.getDescription());
        bundle.putString("review_author", reviewViewModel.getAuthor());
        bundle.putString("review_content", reviewViewModel.getContent());
        bundle.putString("review_image", reviewViewModel.getImageUrl());
        bundle.putString("review_date", reviewViewModel.getDate());
        bundle.putString("review_link", reviewViewModel.getLink());
        return bundle;
    }



}
