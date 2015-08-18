package net.telesurtv.www.telesur.util;

import java.util.Observable;

/**
 * Created by Jhordan on 17/08/15.
 */
public class AppBarLayoutSubject extends Observable {

    private int value;

    public AppBarLayoutSubject(){}

    public void scrollChanged(){
        setChanged();
        notifyObservers();
    }

    public void setValueAppBarLayout(int value){
        this.value = value;
        scrollChanged();

    }

    public int getValue() {
        return value;
    }
}
