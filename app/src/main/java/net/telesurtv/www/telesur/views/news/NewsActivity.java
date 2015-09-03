package net.telesurtv.www.telesur.views.news;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.EndPoint;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.news.News;
import net.telesurtv.www.telesur.data.api.models.news.ParserNews;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.model.Image;
import net.telesurtv.www.telesur.model.NewsViewModel;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class NewsActivity extends BaseNavigationDrawerActivity implements ActionBarDrawerListener.Listener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FrameLayout frameLayoutBackground;
    private ImageView imageView, iconNews, backgroundNews;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AnimatorSet animatorSet;
    private Interpolator mInterpolator;
    int currentPagerPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        setHighLevelActivity();
        initializeViewsAndInterpolator();

        if (viewPager != null)
            setUpViewPager(viewPager);


        if (savedInstanceState != null)
            viewPager.setCurrentItem(savedInstanceState.getInt("position"));
        else
            currentPagerPosition = 0;


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", currentPagerPosition);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentPagerPosition = savedInstanceState.getInt("position");

    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewPagerListener();
    }

    /**
     * getViews from xml and initialize interpolator
     */
    private void initializeViewsAndInterpolator() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        frameLayoutBackground = (FrameLayout) findViewById(R.id.frame_layout_news_theme);
        imageView = (ImageView) findViewById(R.id.image_view_background_animate);
        iconNews = (ImageView) findViewById(R.id.icon_news);
        backgroundNews = (ImageView) findViewById(R.id.background_news);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);


        // intialize interpolator
        mInterpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.overshoot);

        getImageListNews(EndPoint.RSS_OUSTANDING);

    }


    /**
     * @param circleColorTheme this method change color circle
     */
    private void setupColorShape(int circleColorTheme) {
        LayerDrawable bgDrawable = (LayerDrawable) backgroundNews.getBackground();
        final GradientDrawable shapeBackground = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.shape_background);
        final GradientDrawable shapeForeground = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.shape_foreground);
        shapeBackground.setColor(circleColorTheme);
        shapeForeground.setColor(circleColorTheme);
    }


    /*
     * add adapter to viewPager and set ViewPager to tabLayout
     */

    protected void setUpViewPager(ViewPager viewPager) {
        FragmentAdapter fmAdapter = new FragmentAdapter(getSupportFragmentManager());
        fmAdapter.addFragment(OutstandingFragment.newInstance(), getString(R.string.oustanding));
        fmAdapter.addFragment(LatamFragment.newInstance(), getString(R.string.latam));
        fmAdapter.addFragment(WorldFragment.newInstance(), getString(R.string.world));
        fmAdapter.addFragment(SportFragment.newInstance(), getString(R.string.sports));
        fmAdapter.addFragment(CultureFragment.newInstance(), getString(R.string.culture));
        viewPager.setAdapter(fmAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    /*
     * add listener to viewPager
     */
    private void setViewPagerListener() {


        ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPagerPosition = position;
                setThemeBackground(currentPagerPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(currentPagerPosition);

    }


    @Override
    public void onNotImplementedFeatureSelected() {

    }


    /**
     * Set color to FrameLayout
     *
     * @param position position of viewPager to set Color Background
     */
    private void setThemeBackground(int position) {


        int primaryColor = getResources().getColor(R.color.black);
        int primaryDarkColor = getResources().getColor(R.color.black);
        int backgroundColor = getResources().getColor(R.color.black);


        switch (position) {
            case 0:
                primaryColor = getResources().getColor(R.color.theme_red_primary);
                primaryDarkColor = getResources().getColor(R.color.theme_red_primary_dark);
                backgroundColor = getResources().getColor(R.color.theme_red_transparent_primary);


                break;
            case 1:
                primaryColor = getResources().getColor(R.color.theme_yellow_primary);
                primaryDarkColor = getResources().getColor(R.color.theme_yellow_primary_dark);
                backgroundColor = getResources().getColor(R.color.theme_yellow_transparent_primary);


                break;
            case 2:
                primaryColor = getResources().getColor(R.color.theme_green_primary);
                primaryDarkColor = getResources().getColor(R.color.theme_green_primary_dark);
                backgroundColor = getResources().getColor(R.color.theme_green_transparent_primary);

                break;
            case 3:
                primaryColor = getResources().getColor(R.color.theme_purple_primary);
                primaryDarkColor = getResources().getColor(R.color.theme_purple_primary_dark);
                backgroundColor = getResources().getColor(R.color.theme_purple_transparent_primary);

                break;
            case 4:
                primaryColor = getResources().getColor(R.color.theme_blue_primary);
                primaryDarkColor = getResources().getColor(R.color.theme_blue_primary_dark);
                backgroundColor = getResources().getColor(R.color.theme_blue_transparent_primary);

                break;
        }


        // getImageListNews(category);


        int[] icons = {R.drawable.ic_v_menu_news, R.drawable.ic_menu_america, R.drawable.ic_menu_world, R.drawable.ic_menu_soports, R.drawable.ic_menu_culture};
        frameLayoutBackground.setBackgroundColor(backgroundColor);
        collapsingToolbarLayout.setContentScrimColor(primaryColor);


        // setup shape
        setupColorShape(primaryColor);
        animateCircleShape(icons[position]);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(primaryDarkColor);

    }


    // animation Shape

    private void animateCircleShape(int iconCircle) {

        iconNews.setScaleX(0);
        iconNews.setScaleY(0);
        iconNews.setImageResource(iconCircle);
        iconNews.animate().scaleX(1).scaleY(1).setInterpolator(mInterpolator).setStartDelay(300);
        backgroundNews.setScaleY(0);
        backgroundNews.setScaleY(0);
        backgroundNews.animate().scaleX(1).scaleY(1).setInterpolator(mInterpolator).setStartDelay(300);

    }

    /**
     * list of images background to animate
     *
     * @param images
     * @param index
     */

    private void setupHeaderImages(final List<Image> images, final int index) {

        if (index != images.size() - 1) {
            Picasso.with(this).load(images.get(index + 1).getUrl()).fit().centerCrop();
        }

        Picasso.with(this).load(images.get(index).getUrl()).fit().centerCrop().into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                //  imageView.setAlpha(.40f);
                long duration = 5000;
                float transStartX;
                float transStartY;
                float transEndX;
                float transEndY;
                float scaleStart;
                float scaleEnd;
                int state = index % 4;
                if (state == 0) {
                    transStartX = -50;
                    transEndX = 50;
                    transStartY = -50;
                    transEndY = 50;
                    scaleStart = 1.5f;
                    scaleEnd = 1.75f;
                } else if (state == 1) {
                    transStartX = 50;
                    transEndX = 50;
                    transStartY = 50;
                    transEndY = -50;
                    scaleStart = 1.75f;
                    scaleEnd = 1.5f;
                } else if (state == 2) {
                    transStartX = 50;
                    transEndX = -50;
                    transStartY = -50;
                    transEndY = 50;
                    scaleStart = 1.5f;
                    scaleEnd = 1.75f;
                } else {
                    transStartX = -50;
                    transEndX = -50;
                    transStartY = 50;
                    transEndY = -50;
                    scaleStart = 1.75f;
                    scaleEnd = 1.5f;

                }

                ObjectAnimator transX = ObjectAnimator.ofFloat(imageView, "translationX", transStartX, transEndX).setDuration(duration);

                ObjectAnimator transY = ObjectAnimator.ofFloat(imageView, "translationY", transStartY, transEndY).setDuration(duration);

                ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", scaleStart, scaleEnd).setDuration(duration);

                ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", scaleStart, scaleEnd).setDuration(duration);

                animatorSet = new AnimatorSet();
                animatorSet.playTogether(transX, transY, scaleX, scaleY);

                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        int newindex = index == (images.size() - 1) ? 0 : (index + 1);
                        setupHeaderImages(images, newindex);

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                animatorSet.start();
            }

            @Override
            public void onError() {

            }
        });


    }

    /**
     * @param section getListImages
     */
    private void getImageListNews(String section) {

        TelesurApiService telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        telesurApiService.getNewsList(section).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<Response>() {
                    @Override
                    public void call(Response response) {

                        final StringWriter writer = new StringWriter();

                        try {
                            IOUtils.copy(response.getBody().in(), writer, "UTF-8");

                            News[] listNews = ParserNews.getListNews(writer.toString());
                            List<Image> imageList = new ArrayList<>();
                            imageList.clear();
                            for (int i = 0; i < listNews.length; i++) {

                                News notice = listNews[i];
                                Image image = new Image();
                                image.setUrl(notice.getEnclosure().getUrl());
                                imageList.add(image);
                            }

                            updateUI(imageList);

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
     * @param imageList listImages
     */
    private void updateUI(final List<Image> imageList) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                List<Image> imageArrayList = new ArrayList<>();
                imageArrayList.clear();
                for (int i = 0; i < imageList.size(); i++) {
                    Image image = new Image();
                    image.setUrl(imageList.get(i).getUrl());
                    imageArrayList.add(image);
                }
                setupHeaderImages(imageArrayList, 0);


            }
        });
    }


}
