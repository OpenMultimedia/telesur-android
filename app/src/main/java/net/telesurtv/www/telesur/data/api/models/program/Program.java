package net.telesurtv.www.telesur.data.api.models.program;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 19/08/15.
 */
public class Program {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("slug")
    private String slug;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
