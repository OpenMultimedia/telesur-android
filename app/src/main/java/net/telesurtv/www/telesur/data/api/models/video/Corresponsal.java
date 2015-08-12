package net.telesurtv.www.telesur.data.api.models.video;

/**
 * Created by Jhordan on 29/07/15.
 */
public class Corresponsal {

    private String orden;
    private String twitter;
    private String activo;
    private String link;
    private String nombre;
    private CountryCorresponsal pais;
    private String slug;

    public String getOrden() {
        return orden;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getActivo() {
        return activo;
    }

    public String getLink() {
        return link;
    }

    public String getNombre() {
        return nombre;
    }

    public CountryCorresponsal getPais() {
        return pais;
    }

    public String getSlug() {
        return slug;
    }

}
