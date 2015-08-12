package net.telesurtv.www.telesur.data.api.models.video;

import java.util.List;

/**
 * Created by Jhordan on 29/07/15.
 */
public class Program {

    private String creatv_id;
    private List<CharactersProgram> conductores;
    private String twitter_widget;
    private String imagen_url;
    private String banner_url;
    private ProgramType tipo;
    private String twitter;
    private String id;
    private String horario;
    private String descripcion;
    private String nombre;
    private String idioma;
    private String slug;


    public String getCreatv_id() {
        return creatv_id;
    }

    public List<CharactersProgram> getConductores() {
        return conductores;
    }

    public String getTwitter_widget() {
        return twitter_widget;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public ProgramType getTipo() {
        return tipo;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getId() {
        return id;
    }

    public String getHorario() {
        return horario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getSlug() {
        return slug;
    }


}
