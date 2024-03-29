package net.telesurtv.www.telesur.views.videos.video;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.VideoViewModel;
import net.telesurtv.www.telesur.util.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 27/07/15.
 */
public class RecyclerVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<VideoViewModel> videoList;
    ItemRecyclerClickListener itemRecyclerClickListener;

    public void setItemRecyclerClickListener(ItemRecyclerClickListener itemRecyclerClickListener) {
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }

    public RecyclerVideoAdapter() {
        this.videoList = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final VideoViewModel videoViewModel = videoList.get(position);


        setTypeItem(((ViewHolder) holder), videoViewModel,position);









   holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                VideoViewModel videoItem = videoList.get(position);
                if (itemRecyclerClickListener != null) {

                    itemRecyclerClickListener.itemRecycleOnClick(position, videoItem);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }


    public void setListItems(List<VideoViewModel> listItems) {
        if (listItems != null) {
            clear();
            this.videoList.addAll(listItems);
            notifyDataSetChanged();
        }
    }

    public List<VideoViewModel> getListItems() {
        return this.videoList;
    }

    public void clear() {
        if (this.videoList != null) {
            this.videoList.clear();
            notifyDataSetChanged();
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewBackground;
        TextView txtDuration, txtTitle, txtCategory, txtData;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageViewBackground = (ImageView) itemView.findViewById(R.id.image_view_background);
            txtDuration = (TextView) itemView.findViewById(R.id.txt_duration);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtCategory = (TextView) itemView.findViewById(R.id.txt_category);
            txtData = (TextView) itemView.findViewById(R.id.txt_data);

        }
    }


    private void setTypeItem(ViewHolder holder, VideoViewModel video,int position) {
       holder.txtDuration.setText(video.getDuration());
        holder.txtTitle.setText(video.getTitle());
        holder.txtCategory.setText(video.getCategory());
       holder.txtData.setText(Config.date_to_human(video.getData()) );
        Glide.with(holder.imageViewBackground.getContext()).load(video.getBackground()).into(holder.imageViewBackground);
    }


}
