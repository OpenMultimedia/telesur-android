package net.telesurtv.www.telesur.views.review;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.ReviewViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 16/07/15.
 */
public class RecyclerReviewAdapter extends RecyclerView.Adapter<RecyclerReviewAdapter.ViewHolder> {


    List<ReviewViewModel> reviewViewModelList;


    ItemRecyclerClickListenerReview itemRecyclerClickListener;

    public void setItemRecyclerClickListener(ItemRecyclerClickListenerReview itemRecyclerClickListener) {
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }


    public RecyclerReviewAdapter() {
        this.reviewViewModelList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final ReviewViewModel reviewViewModel = reviewViewModelList.get(position);
        Glide.with(holder.imageViewReview.getContext()).load(reviewViewModel.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(200, 200).into(holder.imageViewReview);
        holder.txtAuthor.setText(Html.fromHtml(reviewViewModel.getAuthor()));
        holder.txtTitle.setText(reviewViewModel.getTitle());
        holder.txtDescription.setText(Html.fromHtml(reviewViewModel.getDescription()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (itemRecyclerClickListener != null) {
                    itemRecyclerClickListener.itemRecycleOnClickReview(position, reviewViewModel,view.findViewById(R.id.image_view_reviewer));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return reviewViewModelList.size();
    }

    public void setListItems(List<ReviewViewModel> listItems) {
        if (listItems != null) {
            clear();
            this.reviewViewModelList.addAll(listItems);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (reviewViewModelList != null) {
            reviewViewModelList.clear();
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAuthor, txtTitle, txtDescription;
        ImageView imageViewReview;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtAuthor = (TextView) itemView.findViewById(R.id.txt_author);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtDescription = (TextView) itemView.findViewById(R.id.txt_description);
            imageViewReview = (ImageView) itemView.findViewById(R.id.image_view_reviewer);

        }
    }


}
