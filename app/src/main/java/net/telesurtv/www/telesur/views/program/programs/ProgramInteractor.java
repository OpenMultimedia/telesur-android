package net.telesurtv.www.telesur.views.program.programs;

import net.telesurtv.www.telesur.data.TelesurApiService;
import net.telesurtv.www.telesur.data.api.models.video.VideoTemporal;
import net.telesurtv.www.telesur.model.ProgramViewModel;
import net.telesurtv.www.telesur.util.Config;

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

    public void getProgram(String slug,int initQuery, int lastQuery, TelesurProgramCallback telesurProgramCallback) {
        telesurApiService.getPrograms("completo", initQuery, lastQuery, "programa", slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(programList -> {telesurProgramCallback.onLoaderPrograms(getPrograms(programList));},
                        throwable -> {try {telesurProgramCallback.onFaliedToGetData(throwable.getMessage());} catch (Exception e) {e.printStackTrace();}});

    }

    public void getAllPrograms(int initQuery, int lastQuery,TelesurProgramCallback telesurProgramCallback){
        telesurApiService.getAllPrograms("completo", initQuery, lastQuery, "programa")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(programList -> {telesurProgramCallback.onLoaderPrograms(getPrograms(programList));},
                        throwable -> {try {telesurProgramCallback.onFaliedToGetData(throwable.getMessage());} catch (Exception e) {e.printStackTrace();}});
    }



    private ArrayList<ProgramViewModel> getPrograms(ArrayList<VideoTemporal> videoTemporals){

        ArrayList<ProgramViewModel> programViewModels = new ArrayList<>();


        for(int i =0 ; i< videoTemporals.size(); i++){

            VideoTemporal video = videoTemporals.get(i);
            ProgramViewModel programViewModel = new ProgramViewModel();
            programViewModel.setTitle(video.getTitulo());
            programViewModel.setImage(video.getThumbnail_grande());
            programViewModel.setProgramImage(video.getPrograma().getImagen_url());
            programViewModel.setData(Config.date_to_human(video.getFecha()));
            programViewModel.setDescription(video.getDescripcion());
            programViewModel.setUrl(video.getArchivo_url());
            programViewModel.setLinkNavegation(video.getNavegatorURL());

            if (video.getCategoria() == null)
                programViewModel.setCategory("telesur");
            else
                programViewModel.setCategory(video.getCategoria().getNombre());


            programViewModels.add(programViewModel);
        }
        return programViewModels;
    }
}
