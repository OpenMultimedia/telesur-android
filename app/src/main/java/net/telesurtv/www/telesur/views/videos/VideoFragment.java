package net.telesurtv.www.telesur.views.videos;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoMenu;
import net.telesurtv.www.telesur.ItemRecyclerClickListenerMenuVideo;
import net.telesurtv.www.telesur.util.Theme;
import net.telesurtv.www.telesur.util.TransitionHelper;
import net.telesurtv.www.telesur.views.adapter.RecyclerVideoTypeAdapter;
import net.telesurtv.www.telesur.views.view.ItemOffsetDecoration;

import java.util.ArrayList;

/**
 * Created by Jhordan on 15/07/15.
 */
public class VideoFragment extends Fragment implements ItemRecyclerClickListenerMenuVideo {

    public VideoFragment() {
    }

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    RecyclerView recyclerViewMenu;
    RecyclerVideoTypeAdapter recyclerVideoTypeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        initializeView(rootView);
        return rootView;
    }

    private void initializeView(View rootView) {

        int spans = getResources().getInteger(R.integer.video_span);
        recyclerVideoTypeAdapter = new RecyclerVideoTypeAdapter(getMenuList(), getActivity());
        recyclerViewMenu = (RecyclerView) rootView.findViewById(R.id.video_menu_recycler);
        recyclerViewMenu.setLayoutManager(new GridLayoutManager(recyclerViewMenu.getContext(), spans));
        recyclerViewMenu.addItemDecoration(new ItemOffsetDecoration(recyclerViewMenu.getContext(), R.dimen.item_decoration));
        recyclerViewMenu.setAdapter(recyclerVideoTypeAdapter);
        recyclerVideoTypeAdapter.setItemRecyclerClickListener(this);


    }

    private ArrayList<VideoMenu> getMenuList() {

        String[] titles = {
                getResources().getString(R.string.news_videos),
                getResources().getString(R.string.interview_videos),
                getResources().getString(R.string.documental_videos),
                getResources().getString(R.string.infogra_videos),
                getResources().getString(R.string.specials_videos),
                getResources().getString(R.string.report_videos)
        };


        String[] enumNames = {"news","report", "interview", "documental", "infografi", "special"};


        int[] icons = {
                R.drawable.ic_v_menu_news,
                R.drawable.ic_v_menu_interview,
                R.drawable.ic_v_menu_documental,
                R.drawable.ic_v_menu_info,
                R.drawable.ic_v_menu_specials,
                R.drawable.ic_v_menu_report
        };


        ArrayList<VideoMenu> videoMenus = new ArrayList<>();
        Theme theme;
        for (int i = 0; i < titles.length; i++) {
            VideoMenu videoMenu = new VideoMenu();
            videoMenu.setTitle(titles[i]);
            videoMenu.setIcon(icons[i]);
            videoMenu.setTheme(enumNames[i]);
            theme = Theme.valueOf(videoMenu.getTheme());
            videoMenu.setStyle(theme.getStyle());
            videoMenus.add(videoMenu);
        }


        return videoMenus;
    }

    @Override
    public void itemRecycleOnClick(int position, VideoMenu videoMenu, View toolbar) {


        if (Build.VERSION.SDK_INT >= 21) {
            versionHigherOrEqualSDKLollipop(position, videoMenu, toolbar);
        } else {
            versionLessLollipop(position, videoMenu);
        }


    }


    /**
     *  Intent for version Build.VERSION.SDK_INT >= 21
     * @param position
     * @param videoMenu
     * @param toolbar
     */
    private void versionHigherOrEqualSDKLollipop(int position, VideoMenu videoMenu, View toolbar) {
        Intent intent = new Intent(getActivity(), VideoListDetailActivity.class);
        intent.putExtra("video_theme", videoMenu.getStyle());
        intent.putExtra("video_position", position);
       // final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(getActivity(), Pair.create(toolbar, getString(R.string.transition_toolbar)));
        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(getActivity(), new Pair<>(toolbar, getString(R.string.transition_toolbar)));
       // Pair<View, String> pairTitle = Pair.create(toolbar, getString(R.string.transition_toolbar));
        //ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairs);
        ActivityOptions optionsCompat = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);
        if (Build.VERSION.SDK_INT >= 16)
            getActivity().startActivity(intent, optionsCompat.toBundle());
    }

    /**
     *  Intent for version Build.VERSION.SDK_INT < 21
     * @param position
     * @param videoMenu
     *
     */
    private void versionLessLollipop(int position, VideoMenu videoMenu) {
        Intent intent = new Intent(getActivity(), VideoListDetailActivity.class);
        intent.putExtra("video_theme", videoMenu.getStyle());
        intent.putExtra("video_position", position);
        startActivity(intent);
    }


}
