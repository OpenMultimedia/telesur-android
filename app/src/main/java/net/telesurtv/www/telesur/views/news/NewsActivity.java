package net.telesurtv.www.telesur.views.news;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.pushwoosh.BasePushMessageReceiver;
import com.pushwoosh.BaseRegistrationReceiver;
import com.pushwoosh.PushManager;
import com.squareup.otto.Produce;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.telesurtv.www.telesur.drawer.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.api.models.Notification;
import net.telesurtv.www.telesur.model.Image;
import net.telesurtv.www.telesur.storage.Preferences;
import net.telesurtv.www.telesur.util.InternetConnection;
import net.telesurtv.www.telesur.util.OttoBus;
import net.telesurtv.www.telesur.views.adapter.FragmentAdapter;
import net.telesurtv.www.telesur.views.streaming.streaming.StreamingDetailActivity;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends BaseNavigationDrawerActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FrameLayout frameLayoutBackground;
    private ImageView imageView, iconNews, backgroundNews;
    private FloatingActionButton floatingActionButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AnimatorSet animatorSet;
    private Interpolator mInterpolator;
    int currentPagerPosition;
    String linkFromNotification;
    //push woosh

    BroadcastReceiver mBroadcastReceiver = new BaseRegistrationReceiver() {
        @Override
        public void onRegisterActionReceive(Context context, Intent intent) {
            checkMessage(intent);
        }
    };

    //Push message receiver
    private BroadcastReceiver mReceiver = new BasePushMessageReceiver() {
        @Override
        protected void onMessageReceive(Intent intent) {
            //JSON_DATA_KEY contains JSON payload of push notification.
            showMessage("push message is " + intent.getExtras().getString(JSON_DATA_KEY));
        }
    };

    //Registration of the receivers
    public void registerReceivers() {
        IntentFilter intentFilter = new IntentFilter(getPackageName() + ".action.PUSH_MESSAGE_RECEIVE");

        registerReceiver(mReceiver, intentFilter, getPackageName() + ".permission.C2D_MESSAGE", null);

        registerReceiver(mBroadcastReceiver, new IntentFilter(getPackageName() + "." + PushManager.REGISTER_BROAD_CAST_ACTION));
    }

    public void unregisterReceivers() {
        //Unregister receivers on pause
        try {
            unregisterReceiver(mReceiver);
        } catch (Exception e) {
            // pass.
        }

        try {
            unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e) {
            //pass through
        }
    }


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


        //Register receivers for push notifications
        registerReceivers();

        //Create and start push manager
        PushManager pushManager = PushManager.getInstance(this);

        //Start push manager, this will count app open for Pushwoosh stats as well
        try {
            pushManager.onStartup(this);
        } catch (Exception e) {
            //push notifications are not available or AndroidManifest.xml is not configured properly
        }

        //Register for push!
        pushManager.registerForPushNotifications();

        checkMessage(getIntent());


        // other code

    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            OttoBus.getInstance().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        try {

            OttoBus.getInstance().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        //Re-register receivers on resume
        registerReceivers();
        setViewPagerListener();


        if (InternetConnection.isNetworkMobile(this)) {
            if (!InternetConnection.connectionState(this) && !InternetConnection.mobileConnection(this)) {
                showToast(R.string.not_internet_conection);
            }
        } else if (!InternetConnection.connectionState(this)) {
            showToast(R.string.not_internet_conection);
        }


    }


    @Override
    public void onPause() {
        super.onPause();

        //Unregister receivers on pause
        unregisterReceivers();
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
        floatingActionButton =  (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.theme_green_primary)));


        floatingActionButton.setOnClickListener((View view) -> startActivity(new Intent(NewsActivity.this, StreamingDetailActivity.class)));


        // intialize interpolator
        mInterpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.overshoot);

//        getImageListNews(TelesurApiConstants.RSS_OUSTANDING);

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
    /*private void getImageListNews(String section) {

        TelesurApiService telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        telesurApiService.getNewsList(section).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<Response>() {
                    @Override
                    public void call(Response response) {

                        final StringWriter writer = new StringWriter();

                        try {
                          //  IOUtils.copy(response.getBody().in(), writer, "UTF-8");

                            News[] listNews = ParserNews.getListNews(writer.toString());
                            List<Image> imageList = new ArrayList<>();
                            imageList.clear();
                            for (int i = 0; i < listNews.length; i++) {

                                News notice = listNews[i];
                                Image image = new Image();
                                if (notice.getEnclosure().getUrl() != null)
                                    image.setUrl(notice.getEnclosure().getUrl());
                                else
                                    image.setUrl("http://www.telesurtv.net/arte/LogoBlanco648X351.jpg");


                                imageList.add(image);
                            }

                            updateUI(imageList);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }*/

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

    private void checkMessage(Intent intent) {

        if (null != intent) {
            if (intent.hasExtra(PushManager.PUSH_RECEIVE_EVENT)) {
                getNotification(intent.getExtras().getString(PushManager.PUSH_RECEIVE_EVENT));
                showMessage(intent.getExtras().getString(PushManager.PUSH_RECEIVE_EVENT));
            } else if (intent.hasExtra(PushManager.REGISTER_EVENT)) {
                showMessage("register");
            } else if (intent.hasExtra(PushManager.UNREGISTER_EVENT)) {
                showMessage("unregister");
            } else if (intent.hasExtra(PushManager.REGISTER_ERROR_EVENT)) {
                showMessage("register error");
            } else if (intent.hasExtra(PushManager.UNREGISTER_ERROR_EVENT)) {
                showMessage("unregister error");
            }


            resetIntentValues();
        }
    }


    private void getNotification(String notificationMessage) {

        // String titleDomi = "Líderes mundiales piden fin del bloqueo a Cuba en la ONU";
        // String sectionDomi = "M";
        // String linkDomi = "http://www.telesurtv.net/news/Israel-pide-dialogo-con-Palestina-un-dia-despues-de-atacar-Gaza-20151001-0020.html";

        // openDetailFromNotification(sectionDomi, linkDomi);


        Notification notification = new GsonBuilder().create().fromJson(notificationMessage, Notification.class);
        Log.i("data", notification.getTitle());
        Log.i("data", notification.getUserData().getSection());
        Log.i("data", notification.getUserData().getLink());

        showMessage(notification.getTitle() + " " + notification.getUserData().getSection() + " " + notification.getUserData().getLink());
        //      showMessage(notificationMessage);

        openDetailFromNotification(notification.getUserData().getSection(), notification.getUserData().getLink());

        Preferences.setNotification(NewsActivity.this, "execute");
        //Intent intent = new Intent(NewsActivity.this, NotificationActivity.class);
        //intent.putExtra("link", notification.getUserData().getLink());
        //intent.putExtra("link", "http://www.telesurtv.net/news/Israel-pide-dialogo-con-Palestina-un-dia-despues-de-atacar-Gaza-20151001-0020.html");
        // startActivity(intent);


    }

    @Produce
    public String produceLink() {
        return linkFromNotification;
    }

    /**
     * Will check main Activity intent and if it contains any PushWoosh data, will clear it
     */
    private void resetIntentValues() {
        Intent mainAppIntent = getIntent();
        if (mainAppIntent.hasExtra(PushManager.PUSH_RECEIVE_EVENT)) {
            mainAppIntent.removeExtra(PushManager.PUSH_RECEIVE_EVENT);
        } else if (mainAppIntent.hasExtra(PushManager.REGISTER_EVENT)) {
            mainAppIntent.removeExtra(PushManager.REGISTER_EVENT);
        } else if (mainAppIntent.hasExtra(PushManager.UNREGISTER_EVENT)) {
            mainAppIntent.removeExtra(PushManager.UNREGISTER_EVENT);
        } else if (mainAppIntent.hasExtra(PushManager.REGISTER_ERROR_EVENT)) {
            mainAppIntent.removeExtra(PushManager.REGISTER_ERROR_EVENT);
        } else if (mainAppIntent.hasExtra(PushManager.UNREGISTER_ERROR_EVENT)) {
            mainAppIntent.removeExtra(PushManager.UNREGISTER_ERROR_EVENT);
        }

        setIntent(mainAppIntent);
    }

    private void openDetailFromNotification(String section, String link) {


        final String OUSTANDING = "P";
        final String LATAM = "L";
        final String WORLD = "M";
        final String SPORTS = "D";
        final String CULTURE = "C";
        int positionNotify = 0;

        switch (section) {
            case OUSTANDING:
                positionNotify = 0;
                break;
            case LATAM:
                positionNotify = 1;
                break;
            case WORLD:
                positionNotify = 2;
                break;
            case SPORTS:
                positionNotify = 3;
                break;
            case CULTURE:
                positionNotify = 4;
                break;
        }


        System.out.println("dato " + section);
        System.out.println("dato " + link);
        System.out.println("dato " + positionNotify);

        setViewPagerListenerNoti(positionNotify);
        linkFromNotification = link;

        OttoBus.getInstance().post(linkFromNotification);


        System.out.println("producer " + linkFromNotification);

        System.out.println("dato position notify" + positionNotify);

        System.out.println(linkFromNotification);


    }

    private void setViewPagerListenerNoti(int pos) {
        viewPager.setCurrentItem(pos);
        currentPagerPosition = pos;
    }

    private void showMessage(String message) {
        // Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    private void showToast(int message) {
        Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT).show();
    }

}



