package net.telesurtv.www.telesur.data.api.models.video;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 29/07/15.
 */
public class Videos {

    @SerializedName("player_html_url")
    private String player_html_url;


    @SerializedName("titulo")
    private String titulo;


    @SerializedName("thumbnail_grande")
    private String thumbnail_grande;


    @SerializedName("duracion")
    private String duracion;


    @SerializedName("fecha")
    private String fecha;


    @SerializedName("categoria")
    private Category categoria;


    @SerializedName("estadisticas")
    private Statistics estadisticas;


    @SerializedName("archivo_url")
    private String archivo_url;


    @SerializedName("descripcion")
    private String descripcion;


    @SerializedName("programa")
    private Program programa;

    private String descarga_url;

    @SerializedName("navegador_url")
    private String navegatorURL;

    @SerializedName("corresponsal")
    private Corresponsal corresponsal;

    @SerializedName("tema")
    private Topic topic;


    @SerializedName("pais")
    private Country country;

    public Country getCountry() {
        return country;
    }


    public Topic getTopic() {
        return topic;
    }

    public Corresponsal getCorresponsal() {
        return corresponsal;
    }

    public Program getPrograma() {
        return programa;
    }

    public String getPlayer_html_url() {
        return player_html_url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getThumbnail_grande() {
        return thumbnail_grande;
    }

    public String getDescarga_url() {
        return descarga_url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getFecha() {
        return fecha;
    }

    public Category getCategoria() {
        return categoria;
    }

    public Statistics getEstadisticas() {
        return estadisticas;
    }

    public String getArchivo_url() {
        return archivo_url;
    }

    public String getNavegatorURL() {
        return navegatorURL;
    }

}
