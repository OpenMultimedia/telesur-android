package net.telesurtv.www.telesur.data.api.models.video;

/**
 * Created by Jhordan on 29/07/15.
 */
public class State {

    private Country pais;
    private String codigo;
    private String slug;
    private String geotag;
    private String nombre;

    public Country getPais() {
        return pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getSlug() {
        return slug;
    }

    public String getGeotag() {
        return geotag;
    }

    public String getNombre() {
        return nombre;
    }

}
