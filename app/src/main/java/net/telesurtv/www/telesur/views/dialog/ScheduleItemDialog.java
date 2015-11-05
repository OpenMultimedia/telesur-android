package net.telesurtv.www.telesur.views.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.TelesurApiConstants;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Jhordan on 07/31/15.
 */
public class ScheduleItemDialog extends DialogFragment {


    public static ScheduleItemDialog newInstance(Bundle bundle) {
        ScheduleItemDialog scheduleItemDialog = new ScheduleItemDialog();
        scheduleItemDialog.setArguments(bundle);
        return scheduleItemDialog;
    }

    @Bind(R.id.txt_title_streaming) TextView title_canal;
    @Bind(R.id.txt_hour_streaming) TextView hour_canal;
    @Bind(R.id.txt_sinopsis_streaming) TextView sinopsis_canal;
    @Bind(R.id.txt_hour_caracas) TextView hour_v_canal;
    @Bind(R.id.icon_program) ImageView image_canal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_about_read, container, false);
        ButterKnife.bind(this,rootView);

        System.out.println(getArguments().getString("title"));
        initalizeDialog(getArguments());
        return rootView;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_about_read);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }


    private void initalizeDialog(Bundle bundle){
        if(bundle != null){
            title_canal.setText(bundle.getString("title"));
            hour_canal.setText("Hora local " + bundle.getString("start_hour_v") + " - " + bundle.getString("finish_hour_v"));
            hour_v_canal.setText("Hora "+ bundle.getString("start_hour") + "  -  " + bundle.getString("finish_hour"));
            sinopsis_canal.setText(bundle.getString("sinopsis"));
            Picasso.with(getActivity()).load(TelesurApiConstants.TELESUR_SCHEDULE_PHOTOS + bundle.getString("photo")).into(image_canal);

        }
    }
}



