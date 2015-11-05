package net.telesurtv.www.telesur.views.view.video;

/**
 * Created by Jhordan on 27/07/15.
 */
public interface TelesurPlayerController {

    void setMediaPlayer(TelesurPlayer telesurPlayer);

    void setEnabled(boolean value);

    void show(int timeInMilliSeconds);

    void show();

    void hide();

    void setVisibilityListener(TelesurPlayerControllerVisibilityListener visibilityListener);

}
