package net.telesurtv.www.telesur.views.program;

import android.os.Bundle;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;


public class ProgramActivity extends BaseNavigationDrawerActivity implements ActionBarDrawerListener.Listener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        setHighLevelActivity();



    }




    @Override
    public void onNotImplementedFeatureSelected() {

    }


}
