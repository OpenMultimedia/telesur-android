package net.telesurtv.www.telesur.data.api.models.streaming;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jhordan on 07/09/15.
 */

@Root(name = "semana")
public class Week {

    @Element(name = "lunes")
    Monday monday;

    public Monday getMonday() {
        return monday;
    }

    @Element(name = "martes")
    Tuesday tuesday;

    public Tuesday getTuesday() {
        return tuesday;
    }


    @Element(name = "miercoles")
    Wednesday wednesday;

    public Wednesday getWednesday() {
        return wednesday;
    }


    @Element(name = "jueves")
    Thursday thursday;

    public Thursday getThursday() {
        return thursday;
    }

    @Element(name = "viernes")
    Friday friday;

    public Friday getFriday() {
        return friday;
    }

    @Element(name = "sabado")
    Saturday saturday;

    public Saturday getSaturday() {
        return saturday;
    }

    @Element(name = "domingo")
    Sunday sunday;

    public Sunday getSunday() {
        return sunday;
    }

}
