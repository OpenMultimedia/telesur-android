package net.telesurtv.www.telesur.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.telesurtv.www.telesur.ItemRecyclerClickListenerNews;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 16/07/15.
 */
public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<NewsViewModel> newsViewModelListItems;
    private static final int TYPE_ITEM_1 = 0;
    private static final int TYPE_ITEM_2 = 1;
    private static final int TYPE_ITEM_3 = 2;

    ItemRecyclerClickListenerNews itemRecyclerClickListenerNews;

    public void setItemRecyclerClickListenerNews(ItemRecyclerClickListenerNews itemRecyclerClickListenerNews) {
        this.itemRecyclerClickListenerNews = itemRecyclerClickListenerNews;
    }

    public RecyclerNewsAdapter() {
        this.newsViewModelListItems = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolderType = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_ITEM_1:
                viewHolderType = new ViewHolder(inflater.inflate(R.layout.item_news, parent, false));
                break;
            case TYPE_ITEM_2:
                viewHolderType = new ViewHolder2(inflater.inflate(R.layout.item_news_two, parent, false));
                break;
            case TYPE_ITEM_3:
                viewHolderType = new ViewHolder3(inflater.inflate(R.layout.item_news_three, parent, false));
                break;


        }
        return viewHolderType;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final NewsViewModel newsViewModel = newsViewModelListItems.get(position);

        switch (holder.getItemViewType()) {
            case TYPE_ITEM_1:
                setTypeItem1(((ViewHolder) holder), newsViewModel);
                break;

            case TYPE_ITEM_2:
                setTypeItem2(((ViewHolder2) holder), newsViewModel);
                break;

            case TYPE_ITEM_3:
                setTypeItem3(((ViewHolder3) holder), newsViewModel);
                break;


        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (itemRecyclerClickListenerNews != null) {

                    itemRecyclerClickListenerNews.itemRecycleOnClickNews(position, newsViewModel);
                }


            }
        });

      /*  Glide.with(holder.imageViewNews.getContext()).load(newsViewModel.getImgNews()).into(holder.imageViewNews);
        holder.txtTitleNews.setText(newsViewModel.getTitleNews());
        holder.txtDataNews.setText(newsViewModel.getDataNews());
        holder.txtCategory.setText(newsViewModel.getCategoryNews());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Context context = view.getContext();
                // Intent intent = new Intent(context, VideoDetailActivity.class);
                // context.startActivity(intent);
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return newsViewModelListItems.size();
    }

    public void addItem(NewsViewModel item) {
        this.newsViewModelListItems.add(5, item);
        notifyDataSetChanged();
    }

    public void setListItems(List<NewsViewModel> listItems) {
        if (listItems != null) {
            clear();
            this.newsViewModelListItems.addAll(listItems);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (newsViewModelListItems != null) {
            newsViewModelListItems.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 3 == 0)
            return TYPE_ITEM_2;
        else if (position % 5 == 0)
            return TYPE_ITEM_3;


        return TYPE_ITEM_1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitleNews, txtDataNews, txtCategory;
        ImageView imageViewNews;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTitleNews = (TextView) itemView.findViewById(R.id.txt_title_news);
            txtCategory = (TextView) itemView.findViewById(R.id.txt_category_news);
            txtDataNews = (TextView) itemView.findViewById(R.id.txt_data_news);
            imageViewNews = (ImageView) itemView.findViewById(R.id.image_view_picture_news);

        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView txtTitleNewsTwo, txtDataNewsTwo, txtCategoryTwo;
        ImageView imageViewNewsTwo;
        View view;

        public ViewHolder2(View itemView) {
            super(itemView);
            view = itemView;
            txtTitleNewsTwo = (TextView) itemView.findViewById(R.id.txt_title_news_two);
            txtCategoryTwo = (TextView) itemView.findViewById(R.id.txt_category_news_two);
            txtDataNewsTwo = (TextView) itemView.findViewById(R.id.txt_data_news_two);
            imageViewNewsTwo = (ImageView) itemView.findViewById(R.id.image_view_picture_news_two);

        }

    }

    static class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView txtTitleNewsThree, txtDataNewsThree, txtCategoryThree;
        ImageView imageViewNewsThree;
        View view;

        public ViewHolder3(View itemView) {
            super(itemView);
            view = itemView;
            txtTitleNewsThree = (TextView) itemView.findViewById(R.id.txt_title_news_three);
            txtCategoryThree = (TextView) itemView.findViewById(R.id.txt_category_news_three);
            txtDataNewsThree = (TextView) itemView.findViewById(R.id.txt_data_news_three);
            imageViewNewsThree = (ImageView) itemView.findViewById(R.id.image_view_picture_news_three);

        }

    }

    private void setTypeItem1(ViewHolder holder, NewsViewModel newsViewModel) {

        Glide.with(holder.imageViewNews.getContext()).load(newsViewModel.getImgNews()).into(holder.imageViewNews);
        holder.txtTitleNews.setText(newsViewModel.getTitleNews());
        holder.txtDataNews.setText(newsViewModel.getDataNews());
        holder.txtCategory.setText(newsViewModel.getCategoryNews());
    }

    private void setTypeItem2(ViewHolder2 holder, NewsViewModel newsViewModel) {

        Glide.with(holder.imageViewNewsTwo.getContext()).load(newsViewModel.getImgNews()).into(holder.imageViewNewsTwo);
        holder.txtTitleNewsTwo.setText(newsViewModel.getTitleNews());
        holder.txtDataNewsTwo.setText(newsViewModel.getDataNews());
        holder.txtCategoryTwo.setText(newsViewModel.getCategoryNews());
    }

    private void setTypeItem3(ViewHolder3 holder, NewsViewModel newsViewModel) {

        Glide.with(holder.imageViewNewsThree.getContext()).load(newsViewModel.getImgNews()).into(holder.imageViewNewsThree);
        holder.txtTitleNewsThree.setText(newsViewModel.getTitleNews());
        holder.txtDataNewsThree.setText(newsViewModel.getDataNews());
        holder.txtCategoryThree.setText(newsViewModel.getCategoryNews());
    }


}
