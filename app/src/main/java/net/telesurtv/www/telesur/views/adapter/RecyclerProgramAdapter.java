package net.telesurtv.www.telesur.views.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import net.telesurtv.www.telesur.ItemRecyclerClickListenerReview;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.ProgramViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 16/07/15.
 */
public class RecyclerProgramAdapter extends RecyclerView.Adapter<RecyclerProgramAdapter.ViewHolder> {


    List<ProgramViewModel> programViewModelList;


    float[] vibrant;
    ItemRecyclerClickListenerReview itemRecyclerClickListener;

    public void setItemRecyclerClickListener(ItemRecyclerClickListenerReview itemRecyclerClickListener) {
        this.itemRecyclerClickListener = itemRecyclerClickListener;
    }


    public RecyclerProgramAdapter() {
        this.programViewModelList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final ProgramViewModel programViewModel = programViewModelList.get(position);

        Target imageLoaded = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {

                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                  int color =    palette.getDarkMutedColor(R.color.theme_default_primary_dark);

                        Palette.Swatch vibrantSwatch = palette.getDarkMutedSwatch();
                        if (vibrantSwatch != null)
                            holder.linearLayoutProgram.setBackgroundColor(color);
                    }
                });

                holder.imageViewProgram.setImageBitmap(bitmap);


                // Getting the different types of colors from the Image
                //Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();


//                Palette.Swatch vibrantLightSwatch = palette.getLightVibrantSwatch();
//                float[] vibrantlight = vibrantLightSwatch.getHsl();
//
//                Palette.Swatch vibrantDarkSwatch = palette.getDarkVibrantSwatch();
//                float[] vibrantdark = vibrantDarkSwatch.getHsl();
//
//                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
//                float[] muted = mutedSwatch.getHsl();
//
//                Palette.Swatch mutedlightswatch = palette.getLightMutedSwatch();
//                float[] mutedlight = mutedlightswatch.getHsl();
//
//                Palette.Swatch mutedDarkSwatch = palette.getDarkMutedSwatch();
//                float[] muteddark = mutedDarkSwatch.getHsl();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.with(holder.imageViewProgram.getContext()).load(programViewModel.getImage()).into(imageLoaded);
        Picasso.with(holder.iconProgram.getContext()).load(programViewModel.getProgramImage()).into(holder.iconProgram);
        holder.txtTitle.setText(programViewModel.getTitle());
        holder.txtCategoryProgram.setText(programViewModel.getCategory());










/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (itemRecyclerClickListener != null) {
                    itemRecyclerClickListener.itemRecycleOnClickReview(position, programViewModel);
                }
            }
        });*/


    }


    @Override
    public int getItemCount() {
        return programViewModelList.size();
    }

    public void setListItems(List<ProgramViewModel> listItems) {
        if (listItems != null) {
            clear();
            this.programViewModelList.addAll(listItems);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (programViewModelList != null) {
            programViewModelList.clear();
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtCategoryProgram;
        ImageView imageViewProgram, iconProgram;
        LinearLayout linearLayoutProgram;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            txtTitle = (TextView) itemView.findViewById(R.id.txt_title_program);
            imageViewProgram = (ImageView) itemView.findViewById(R.id.image_view_program);
            linearLayoutProgram = (LinearLayout) itemView.findViewById(R.id.linear_background_card_program);
            iconProgram = (ImageView) itemView.findViewById(R.id.program_image);
            txtCategoryProgram = (TextView)itemView.findViewById(R.id.txt_category_progrm);


        }
    }


}
