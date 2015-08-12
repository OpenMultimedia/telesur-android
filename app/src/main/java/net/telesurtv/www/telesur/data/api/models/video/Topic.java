package net.telesurtv.www.telesur.data.api.models.video;

/**
 * Created by Jhordan on 29/07/15.
 */
public class Topic {
    private String fecha_caducidad;
    private String fecha_creacion;
    private String slug;
    private String nombre;

    public String getFecha_caducidad() {
        return fecha_caducidad;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public String getSlug() {
        return slug;
    }

    public String getNombre() {
        return nombre;
    }
}
