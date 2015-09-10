package net.telesurtv.www.telesur.data.api.models.streaming;



import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jhordan on 08/09/15.
 */

@Root(name = "sabado")
public class Saturday {

    @Attribute
    String cuantos;

    public String getCuantos() {
        return cuantos;
    }

    @Element(name = "programas")
    Programs programs;

    public Programs getPrograms() {
        return programs;
    }

}
