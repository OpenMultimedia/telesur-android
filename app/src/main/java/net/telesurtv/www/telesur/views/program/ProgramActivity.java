package net.telesurtv.www.telesur.views.program;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.otto.Produce;

import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.ProgramItem;
import net.telesurtv.www.telesur.util.OttoBus;

import java.util.ArrayList;


public class ProgramActivity extends BaseNavigationDrawerActivity implements ProgramMvpView, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ProgramSpinnerAdapter spinnerAdapter;
    private String programSlug = "all";
    private ProgramPresenter programPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        setHighLevelActivityOther();
        initializeViews();

       /* ProgramItem programItem = new ProgramItem();
        programItem.setName("Todos");
        programItem.setSlug("slug");
        programItemList.add(programItem);

        spinnerAdapter = new ProgramSpinnerAdapter(this);
        spinnerAdapter.addItems(programItemList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);*/

        programPresenter = new ProgramPresenter();
        programPresenter.attachedView(this);

        spinnerAdapter = new ProgramSpinnerAdapter(this);


    }


    @Override
    public void onStart() {
        super.onStart();
        programPresenter.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
        programPresenter.onStop();
    }

    @Override
    public void showProgramList(ArrayList<ProgramItem> programItems) {
        spinnerAdapter.addItems(programItems);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        programPresenter.onResume();



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


    @Produce
    public String produceSlug() {
        return programSlug;
    }


    private void makeRequest() {

     /*   TelesurApiService telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        telesurApiService.getProgramList("100", "nombre", "slug")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(programListSlug -> Observable.from(programListSlug))
                .flatMap(programVideo -> telesurApiService.getPrograms("completo", 1, 20, "programa"))
                .subscribe(ListVieos -> )*/


    }

   /* private void getProgramList() {

        TelesurApiService telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        telesurApiService.getProgramList("100", "nombre", "slug").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Response>() {
            @Override
            public void call(Response response) {

                final StringWriter writer = new StringWriter();

                try {
                    //IOUtils.copy(response.getBody().in(), writer, "UTF-8");

                    programItemList.clear();

                    Program[] listNamePrograms = new GsonBuilder().create().fromJson(writer.toString(), Program[].class);
                    for (int i = 0; i < listNamePrograms.length; i++) {
                        Program program = listNamePrograms[i];
                        ProgramItem programItem = new ProgramItem();
                        programItem.setName(program.getNombre());
                        programItem.setSlug(program.getSlug());
                        programItemList.add(programItem);

                        if (i == 0)
                            programSlug = program.getSlug();
                    }
                    updateUI(programItemList);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }*/


    private void initializeViews() {
        Toolbar toolbar = getToolbar();
        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner, toolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, lp);
        spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(ProgramActivity.this, Integer.toString(i), Toast.LENGTH_LONG).show();
        if (i == 0)
            programSlug = "all";
        else
            programSlug = ((ProgramItem) spinner.getItemAtPosition(i)).getSlug();

        programPresenter.onItemSelected(programSlug);

    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void OttoPost(String programSlug) {
        OttoBus.getInstance().post(programSlug);
    }


    private void showToast(int message) {
        Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        programPresenter.detachView();
        super.onDestroy();
    }
}
