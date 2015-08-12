package net.telesurtv.www.telesur.views.view.video;

/**
 * Created by Jhordan on 27/07/15.
 */
public interface FensterVideoStateListener {

    void onFirstVideoFrameRendered();

    void onPlay();

    void onBuffer();

    boolean onStopWithExternalError(int position);
}
