package net.telesurtv.www.telesur.data.api.models.streaming;



import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Jhordan on 08/09/15.
 */

@Root(name = "lunes")
public class Monday {

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
