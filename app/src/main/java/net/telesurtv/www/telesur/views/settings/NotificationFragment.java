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
public class NotificationFragment extends Fragment {


    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings_notification, container, false);


        return rootView;

    }



}



