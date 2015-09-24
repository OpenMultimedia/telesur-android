package net.telesurtv.www.telesur.views.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.R;


/**
 * Created by Jhordan on 07/31/15.
 */
public class AboutFragment extends Fragment {


    public static AboutFragment newInstance() {
        return new AboutFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings_about, container, false);


        return rootView;

    }



}



