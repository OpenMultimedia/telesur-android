package net.telesurtv.www.telesur.views.program.slug;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.squareup.otto.Produce;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.drawer.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.model.ProgramItem;
import net.telesurtv.www.telesur.util.OttoBus;

import java.util.ArrayList;


public class ProgramSlugActivity extends BaseNavigationDrawerActivity implements ProgramSlugMvpView, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ProgramSpinnerAdapter spinnerAdapter;
    private String programSlug = "all";
    private ProgramSlugSlugPresenter programSlugPresenter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        setHighLevelActivityOther();
        initializeViews();
        programSlugPresenter = new ProgramSlugSlugPresenter();
        programSlugPresenter.attachedView(this);
        spinnerAdapter = new ProgramSpinnerAdapter(this);

    }


    @Override public void onStart() {
        super.onStart();
        programSlugPresenter.onStart();
    }

    @Override public void onStop() {
        super.onStop();
        programSlugPresenter.onStop();
    }

    @Override public void showProgramList(ArrayList<ProgramItem> programItems) {
        spinnerAdapter.addItems(programItems);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override protected void onResume() {
        super.onResume();




      /*  if (InternetConnection.isNetworkMobile(ProgramActivity.this)) {
            if (!InternetConnection.connectionState(ProgramActivity.this) && !InternetConnection.mobileConnection(ProgramActivity.this)) {

                showToast(R.string.expected_error_wifi);
            }
        } else if (!InternetConnection.connectionState(ProgramActivity.this)) {
            showToast(R.string.expected_error_wifi);

        } else {
            //getProgramList();
        }*/


    }


    @Produce public String produceSlug() {
        return programSlug;
    }



    @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        programSlugPresenter.onItemSelected(programSlug,i);
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override public void OttoPost(String programSlug, int position ) {
        if (position == 0)
            programSlug = "all";
        else
            programSlug = ((ProgramItem) spinner.getItemAtPosition(position)).getSlug();

        OttoBus.getInstance().post(programSlug);
    }



    @Override protected void onDestroy() {
        programSlugPresenter.detachView();
        super.onDestroy();
    }


    private void initializeViews() {
        Toolbar toolbar = getToolbar();
        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner, toolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, lp);
        spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
    }


}
