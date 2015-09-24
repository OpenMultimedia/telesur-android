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

import com.google.gson.GsonBuilder;
import com.squareup.otto.Produce;

import net.telesurtv.www.telesur.BaseNavigationDrawerActivity;
import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.data.ClientServiceTelesur;
import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.program.Program;
import net.telesurtv.www.telesur.drawer.ActionBarDrawerListener;
import net.telesurtv.www.telesur.model.ProgramItem;
import net.telesurtv.www.telesur.util.OttoBus;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class ProgramActivity extends BaseNavigationDrawerActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ProgramSpinnerAdapter spinnerAdapter;
    private String programSlug;
    List<ProgramItem> programItemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        setHighLevelActivityOther();
        initializeView();


        spinnerAdapter = new ProgramSpinnerAdapter(this);

        ProgramItem programItem = new ProgramItem();
        programItem.setName("Todos");
        programItem.setSlug("slug");
        programItemList.add(programItem);
        spinnerAdapter.addItems(programItemList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

    }




    @Override
    public void onStart() {
        super.onStart();

        try {
            OttoBus.getInstance().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        try {

            OttoBus.getInstance().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        getProgramList();



    }


    @Produce
    public String produceSlug() {
        return programSlug;
    }

    private void getProgramList() {

        TelesurApiService telesurApiService = ClientServiceTelesur.getRestAdapter().create(TelesurApiService.class);
        telesurApiService.getProgramList("100", "nombre", "slug").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Response>() {
            @Override
            public void call(Response response) {

                final StringWriter writer = new StringWriter();

                try {
                    IOUtils.copy(response.getBody().in(), writer, "UTF-8");

                    Program[] listNamePrograms = new GsonBuilder().create().fromJson(writer.toString(), Program[].class);
                    programItemList.clear();
                    for (int i = 0; i < listNamePrograms.length; i++) {
                        Program program = listNamePrograms[i];
                        ProgramItem programItem = new ProgramItem();
                        programItem.setName(program.getNombre());
                        programItem.setSlug(program.getSlug());
                        programItemList.add(programItem);

                        if(i == 0)
                            programSlug = program.getSlug();
                    }
                    updateUI(programItemList);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void updateUI(final List<ProgramItem> programItems) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinnerAdapter.addItems(programItems);
            }
        });

    }

    private void initializeView() {
        Toolbar toolbar = getToolbar();
        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner, toolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, lp);
        spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(ProgramActivity.this,Integer.toString(i),Toast.LENGTH_SHORT).show();

        if(i== 0)
            programSlug = "all";
        else
            programSlug = ((ProgramItem) spinner.getItemAtPosition(i)).getSlug();

        OttoBus.getInstance().post(programSlug);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
