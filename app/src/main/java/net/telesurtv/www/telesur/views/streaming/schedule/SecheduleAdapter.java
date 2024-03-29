package net.telesurtv.www.telesur.views.streaming.schedule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.TelesurApiConstants;
import net.telesurtv.www.telesur.model.Streaming;

import java.util.List;

/**
 * Created by Jhordan on 10/09/15.
 */
public class SecheduleAdapter extends RecyclerView.Adapter<SecheduleAdapter.SimpleViewHolder> {

    private final Context mContext;
    private List<Streaming> mData;

    private StreamingOnItemClickListener streamingOnItemClickListener;

    public void setStreamingOnItemClickListener(StreamingOnItemClickListener streamingOnItemClickListener) {
        this.streamingOnItemClickListener = streamingOnItemClickListener;
    }


    public void add(Streaming s, int position) {
        position = position == -1 ? getItemCount() : position;
        mData.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtHour, txtSinopsis, txtCaracas;
        public ImageView imageViewIcon;
        View view;

        public SimpleViewHolder(View view) {
            super(view);
            this.view = view;
            txtTitle = (TextView) view.findViewById(R.id.txt_title_streaming);
            txtHour = (TextView) view.findViewById(R.id.txt_hour_streaming);
            txtSinopsis = (TextView) view.findViewById(R.id.txt_sinopsis_streaming);
            txtCaracas = (TextView) view.findViewById(R.id.txt_hour_caracas);
            imageViewIcon = (ImageView) view.findViewById(R.id.icon_program);
        }
    }

    public SecheduleAdapter(Context context, List<Streaming> data) {
        mContext = context;
        // if (data != null)
        // mData = new ArrayList<>(Arrays.asList(data));
        mData = data;
        // else
        //   mData = new ArrayList<>();
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_streaming, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        Streaming streaming = mData.get(position);
        holder.txtTitle.setText(streaming.getName());
        holder.txtHour.setText("Hora local: " + streaming.getStarHourVenezuela() + " - " + streaming.getFinishHourVenezuela());
        holder.txtCaracas.setText(streaming.getStarHour() + "  -  " + streaming.getFinishHour());
        holder.txtSinopsis.setText(streaming.getSinopsis());
        Picasso.with(holder.imageViewIcon.getContext()).load(TelesurApiConstants.TELESUR_SCHEDULE_PHOTOS + streaming.getPhoto()).into(holder.imageViewIcon);

        holder.view.setOnClickListener((View view) -> {

            if (streamingOnItemClickListener != null)
                streamingOnItemClickListener.itemRecycleOnClickStreaming(position, streaming);

        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}