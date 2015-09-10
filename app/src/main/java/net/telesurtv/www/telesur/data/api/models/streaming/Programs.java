package net.telesurtv.www.telesur.data.api.models.streaming;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Jhordan on 08/09/15.
 */
@Root(name="programas")
public class Programs {

    @ElementList(inline = true)
    List<Program> programList;

    public List<Program> getProgramList() {
        return programList;
    }
}

