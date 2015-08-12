package net.telesurtv.www.telesur.views.dialog;

import android.app.Activity;
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

import net.telesurtv.www.telesur.R;


/**
 * Created by Jhordan on 07/31/15.
 */
public class ReadTelesur extends DialogFragment {

    public ReadTelesur() {

    }

    public static ReadTelesur newInstance(){
        return new ReadTelesur();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_about_read, container, false);


        return v;

    }

}



