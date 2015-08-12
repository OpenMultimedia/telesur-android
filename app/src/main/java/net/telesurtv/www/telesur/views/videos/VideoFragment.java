package net.telesurtv.www.telesur.views.videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoMenu;
import net.telesurtv.www.telesur.views.ItemRecyclerClickListenerMenuVideo;
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
        recyclerVideoTypeAdapter = new RecyclerVideoTypeAdapter(getMenuList());
        recyclerViewMenu = (RecyclerView) rootView.findViewById(R.id.video_menu_recycler);
        recyclerViewMenu.setLayoutManager(new GridLayoutManager(recyclerViewMenu.getContext(), 3));
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


        int[] primaryColors = {
                getResources().getColor(R.color.primary_video_menu_news_theme),
                getResources().getColor(R.color.primary_video_menu_interview_theme),
                getResources().getColor(R.color.primary_video_menu_documental_theme),
                getResources().getColor(R.color.primary_video_menu_info_theme),
                getResources().getColor(R.color.primary_video_menu_special_theme),
                getResources().getColor(R.color.primary_video_menu_report_theme)
        };


        int[] colors = {
                getResources().getColor(R.color.video_menu_news_theme),
                getResources().getColor(R.color.video_menu_interview_theme),
                getResources().getColor(R.color.video_menu_documental_theme),
                getResources().getColor(R.color.video_menu_info_theme),
                getResources().getColor(R.color.video_menu_special_theme),
                getResources().getColor(R.color.video_menu_report_theme)
        };

        int[] icons = {
                R.drawable.ic_v_menu_news,
                R.drawable.ic_v_menu_interview,
                R.drawable.ic_v_menu_documental,
                R.drawable.ic_v_menu_info,
                R.drawable.ic_v_menu_specials,
                R.drawable.ic_v_menu_report
        };


        ArrayList<VideoMenu> videoMenus = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            VideoMenu videoMenu = new VideoMenu();
            videoMenu.setTitle(titles[i]);
            videoMenu.setColorBackground(colors[i]);
            videoMenu.setIcon(icons[i]);
            videoMenu.setPrimaryColor(primaryColors[i]);
            videoMenus.add(videoMenu);
        }


        return videoMenus;
    }


    @Override
    public void itemRecycleOnClick(int position, VideoMenu videoMenu) {

        Intent intent = new Intent(getActivity(), VideoListDetailActivity.class);
        intent.putExtra("video_primary", videoMenu.getPrimaryColor());
        intent.putExtra("video_primary_dark", videoMenu.getColorBackground());
        intent.putExtra("video_position", position);
        startActivity(intent);
    }
}