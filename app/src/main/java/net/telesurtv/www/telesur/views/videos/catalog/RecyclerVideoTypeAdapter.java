package net.telesurtv.www.telesur.views.videos.catalog;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoMenu;
import net.telesurtv.www.telesur.util.Theme;

import java.util.List;

/**
 * Created by Jhordan on 27/07/15.
 */
public class RecyclerVideoTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<VideoMenu> videoMenuList;
    ItemRecyclerClickListenerMenuVideo itemRecyclerClickListener;
    private final Resources mResources;


    public void setItemRecyclerClickListener(ItemRecyclerClickListenerMenuVideo itemRecyclerClickListener) {
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }

    public RecyclerVideoTypeAdapter(List<VideoMenu> videoMenuList, Context context) {
        this.videoMenuList = videoMenuList;
        mResources = context.getResources();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_category, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final VideoMenu videoMenu = videoMenuList.get(position);


        setTypeItem((ViewHolder) holder, videoMenu);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (itemRecyclerClickListener != null) {
                    itemRecyclerClickListener.itemRecycleOnClick(position, videoMenu,view.findViewById(R.id.category_title_video));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoMenuList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        public TextView title;
        LinearLayout mBackground;
        View view;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            icon = (ImageView) itemView.findViewById(R.id.category_icon_video);
            title = (TextView) itemView.findViewById(R.id.category_title_video);
            mBackground = (LinearLayout)itemView.findViewById(R.id.category_background_video);



        }
    }


    private void setTypeItem(ViewHolder holder, VideoMenu videoMenu) {

        Theme theme = Theme.valueOf(videoMenu.getTheme());
        holder.title.setText(videoMenu.getTitle());
       // holder.title.setTextColor(getColor(theme.getTxtColorPrimary()));
        holder.title.setTextColor(getColor(R.color.white));
       // holder.title.setBackgroundColor(getColor(theme.getColorPrimary()));
       // holder.title.setBackgroundColor(getColor(R.color.white));
       // holder.icon.setBackgroundColor(getColor(theme.getWindowBackground()));
        holder.icon.setImageResource(videoMenu.getIcon());
     //   holder.mBackground.setBackgroundColor(getColor(theme.getWindowBackground()));
        holder.mBackground.setBackgroundColor(getColor(theme.getColorPrimary()));


    }

    private int getColor(@ColorRes int colorRes) {
        return mResources.getColor(colorRes);
    }



}
