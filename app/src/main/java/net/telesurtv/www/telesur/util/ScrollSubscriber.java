package net.telesurtv.www.telesur.util;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jhordan on 17/08/15.
 */
public class ScrollSubscriber implements Observer, Display {

    Observable observable;
    private int value;


    public ScrollSubscriber(Observable observable){
        this.observable = observable;
        observable.addObserver(this);

    }
    @Override
    public void update(Observable observable, Object o) {

        if(observable instanceof AppBarLayoutSubject){
            AppBarLayoutSubject appBarLayoutSubject = (AppBarLayoutSubject)observable;
            value = appBarLayoutSubject.getValue();

        }

    }


    @Override
    public void getValue() {

        System.out.println("esto es mi subscriber " + value);

    }
}
