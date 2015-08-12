package net.telesurtv.www.telesur.views.news;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.model.Image;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;
import net.telesurtv.www.telesur.views.fragment.FragmentEmpity;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends BaseNavigationDrawerActivity implements ActionBarDrawerListener.Listener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FrameLayout frameLayout;
    private ImageView imageView;
    AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        setHighLevelActivity();
        setupViews();

        if (viewPager != null)
            setUpViewPager(viewPager);

        String[] images = {"http://images.bwbx.io/cms/2014-03-20/0320_gmail_970-630x420.jpg",
                "http://officesnapshots.com/wp-content/uploads/2008/02/gp5.jpg",
                "http://abduzeedo.com/files/originals/g/googleplex-01.jpg",
                "http://si.wsj.net/public/resources/images/SF-AB030_VALLEY_G_20110727135458.jpg"};

        List<Image> imageList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Image image = new Image();
            image.setUrl(images[i]);
            imageList.add(image);
        }

        setupHeaderImages(imageList,0);


    }


    @Override
    protected void onResume() {
        super.onResume();
      //  setViewPagerListener();
    }

    /**
     * getViews from xml
     */
    private void setupViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_news_theme);
        imageView = (ImageView) findViewById(R.id.image_view_background_animate);

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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setThemeBackground(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

        int backgroundColorTheme = getResources().getColor(R.color.black);
        int statusBarColorTheme = getResources().getColor(R.color.black);

        switch (position) {
            case 0:
                backgroundColorTheme = getResources().getColor(R.color.oustanding_theme);
                statusBarColorTheme = getResources().getColor(R.color.oustanding_status_bar);
                break;
            case 1:
                backgroundColorTheme = getResources().getColor(R.color.latam_theme);
                statusBarColorTheme = getResources().getColor(R.color.latam_status_bar);
                break;
            case 2:
                backgroundColorTheme = getResources().getColor(R.color.world_theme);
                statusBarColorTheme = getResources().getColor(R.color.world_status_bar);
                break;
            case 3:
                backgroundColorTheme = getResources().getColor(R.color.sports_theme);
                statusBarColorTheme = getResources().getColor(R.color.sports_status_bar);
                break;
            case 4:
                backgroundColorTheme = getResources().getColor(R.color.culture_theme);
                statusBarColorTheme = getResources().getColor(R.color.culture_status_bar);
                break;
        }

        frameLayout.setBackgroundColor(backgroundColorTheme);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(statusBarColorTheme);


    }

    ///


    private void setupHeaderImages(final List<Image> images, final int index) {

        if (index != images.size() - 1) {
            Picasso.with(this).load(images.get(index + 1).getUrl());
        }

        Picasso.with(this).load(images.get(index).getUrl()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                imageView.setAlpha(.15f);
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

}
