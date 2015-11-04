package net.telesurtv.www.telesur.views.program;

import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.program.Program;
import net.telesurtv.www.telesur.model.ProgramItem;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 03/11/15.
 */
public class ProgramInteractor {


    private TelesurApiService telesurApiService;

    public ProgramInteractor(TelesurApiService telesurApiService) {
        this.telesurApiService = telesurApiService;
    }

    public void getNewsFromServer(TelesurProgramCallback telesurProgramCallback) {

        telesurApiService.getProgramList("100", "nombre", "slug")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Programs -> { telesurProgramCallback.onLoaderNews(getProgramItems(Programs));},
                        throwable -> {try{telesurProgramCallback.onFaliedToGetData(throwable.getMessage());}catch (Exception e){e.printStackTrace();}});
    }


    private ArrayList<ProgramItem> getProgramItems(ArrayList<Program> programs){

        ArrayList<ProgramItem> programItems = new ArrayList<>();


        ProgramItem programItemAll = new ProgramItem();
        programItemAll.setName("Todos");
        programItemAll.setSlug("slug");
        programItems.add(programItemAll);

        for (int i = 0; i < programs.size(); i++) {
            Program program = programs.get(i);
            ProgramItem programItem = new ProgramItem();
            programItem.setName(program.getNombre());
            programItem.setSlug(program.getSlug());
            programItems.add(programItem);

           // if (i == 0)
             //   programSlug = program.getSlug();
        }
     return programItems;
    }


 /* this programs are bad becouse are english programs
    Laura Flanders Show
    Media Review
    Rear Window
    The Self Show*/
}
