package net.telesurtv.www.telesur.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.telesurtv.www.telesur.R;

/**
 * Created by Jhordan on 15/07/15.
 */
public class FragmentEmpity extends Fragment {

    public FragmentEmpity() {
    }

    public static FragmentEmpity newInstance() {
        return new FragmentEmpity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_empity, container, false);



        return rootView;
    }


}
