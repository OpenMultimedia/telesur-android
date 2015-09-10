package net.telesurtv.www.telesur.data.api.models.streaming;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jhordan on 07/09/15.
 */
@Root(name="programa")
public class Program {

    @Attribute
    String id;

    public String getId() {
        return id;
    }

    @Element(name = "nombre",required = false)
    String name;

    @Element(name = "sinopsis",required = false)
    String sinapsis;

    @Element(name = "foto",required = false)
    String photo;

    @Element(name = "url",required = false)
    String url;

    @Element(name = "hora_ini",required = false)
    String hourStart;

    @Element(name = "hora_fin",required = false)
    String hourFinish;

    @Element(name = "conductor", required = false)
    String conductor;

    @Element(name = "estatus" ,required = false)
    String status;

    @Element(name = "tipo_xml",required = false)
    String kindXMl;

    @Element(name = "exclusivo",required = false)
    String exclusive;

    @Element(name = "duracion",required = false)
    String duration;

    public String getName() {
        return name;
    }

    public String getSinapsis() {
        return sinapsis;
    }

    public String getPhoto() {
        return photo;
    }

    public String getUrl() {
        return url;
    }

    public String getHourStart() {
        return hourStart;
    }

    public String getHourFinish() {
        return hourFinish;
    }

    public String getConductor() {
        return conductor;
    }

    public String getStatus() {
        return status;
    }

    public String getKindXMl() {
        return kindXMl;
    }

    public String getExclusive() {
        return exclusive;
    }

    public String getDuration() {
        return duration;
    }

}
