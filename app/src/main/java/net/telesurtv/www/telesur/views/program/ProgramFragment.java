package net.telesurtv.www.telesur.views.program;

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
public class ProgramFragment extends Fragment {

    public ProgramFragment() {
    }

    public static ProgramFragment newInstance() {
        return new ProgramFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program, container, false);



        return rootView;
    }




}
