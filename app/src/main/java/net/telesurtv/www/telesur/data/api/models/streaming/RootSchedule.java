package net.telesurtv.www.telesur.data.api.models.streaming;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jhordan on 08/09/15.
 */

@Root(name = "root")
public class RootSchedule {

    @Element(required = true, name = "semana")
    Week week;

    public Week getWeek() {
        return week;
    }
}
