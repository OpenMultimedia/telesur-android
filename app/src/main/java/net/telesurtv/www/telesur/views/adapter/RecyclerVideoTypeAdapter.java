package net.telesurtv.www.telesur.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoMenu;
import net.telesurtv.www.telesur.views.ItemRecyclerClickListenerMenuVideo;

import java.util.List;

/**
 * Created by Jhordan on 27/07/15.
 */
public class RecyclerVideoTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<VideoMenu> videoMenuList;
    ItemRecyclerClickListenerMenuVideo itemRecyclerClickListener;


    public void setItemRecyclerClickListener(ItemRecyclerClickListenerMenuVideo itemRecyclerClickListener) {
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }

    public RecyclerVideoTypeAdapter(List<VideoMenu> videoMenuList) {
        this.videoMenuList = videoMenuList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_type, parent, false);
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
                    itemRecyclerClickListener.itemRecycleOnClick(position, videoMenu);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoMenuList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewIcon;
        TextView txtTitle;
        LinearLayout linearLayout;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            imageViewIcon = (ImageView) itemView.findViewById(R.id.image_view_icon_video_menu);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title_video_menu);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_video_background);

        }
    }


    private void setTypeItem(ViewHolder holder, VideoMenu videoMenu) {
        holder.txtTitle.setText(videoMenu.getTitle());
        holder.imageViewIcon.setImageResource(videoMenu.getIcon());
        holder.linearLayout.setBackgroundColor(videoMenu.getColorBackground());
    }


}
