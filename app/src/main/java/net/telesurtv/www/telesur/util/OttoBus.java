package net.telesurtv.www.telesur.util;

import com.squareup.otto.Bus;

/**
 * Created by Jhordan on 19/08/15.
 */
public class OttoBus {

    private static Bus bus;

    public static Bus getInstance() {
        if (bus == null)
            bus = new Bus();

        return bus;
    }
}
