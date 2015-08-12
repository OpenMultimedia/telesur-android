package net.telesurtv.www.telesur.drawer;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import net.telesurtv.www.telesur.views.about.AboutActivity;
import net.telesurtv.www.telesur.views.news.NewsActivity;
import net.telesurtv.www.telesur.views.program.ProgramActivity;
import net.telesurtv.www.telesur.views.review.ReviewActivity;
import net.telesurtv.www.telesur.views.streaming.StreamingActivity;
import net.telesurtv.www.telesur.views.videos.VideosActivity;

/**
 * Created by Jhordan on 28/07/15.
 */
public class Navigator {

    private NavigatorActivity activity;

    public Navigator(NavigatorActivity activity) {
        this.activity = activity;
    }

    public void toNews() {
        startNewActivity(NewsActivity.class);
    }

    public void toVideos() {
        startNewActivity(VideosActivity.class);
    }

    public void toPrograms() {
        startNewActivity(ProgramActivity.class);
    }

    public void toReview() {
        startNewActivity(ReviewActivity.class);
    }

    public void toStreaming() {
        startNewActivity(StreamingActivity.class);
    }

    public void toSettings() {
    }

    public void toAbout() {
        startNewActivityBack(AboutActivity.class);
    }

    private void startNewActivity(Class<?> Class) {
        Intent newsIntent = new Intent(activity, Class);
        ActivityCompat.startActivity(activity, newsIntent, null);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    private void startNewActivityBack(Class<?> Class) {
        Intent newsIntent = new Intent(activity, Class);
        ActivityCompat.startActivity(activity, newsIntent, null);

    }


}
