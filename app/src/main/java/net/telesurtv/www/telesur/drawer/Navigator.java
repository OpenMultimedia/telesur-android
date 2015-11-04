package net.telesurtv.www.telesur.drawer;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import net.telesurtv.www.telesur.views.settings.AboutActivity;
import net.telesurtv.www.telesur.views.news.NewsActivity;
import net.telesurtv.www.telesur.views.program.ProgramActivity;
import net.telesurtv.www.telesur.views.review.ReviewActivity;
import net.telesurtv.www.telesur.views.settings.SettingsActivity;
import net.telesurtv.www.telesur.views.streaming.StreamingActivity;
import net.telesurtv.www.telesur.views.videos.video.VideosActivity;

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
        startNewActivityBack(SettingsActivity.class);
    }

    public void toAbout() {
        startNewActivityBack(AboutActivity.class);
    }

    private void startNewActivity(Class<?> Class) {
        Intent intent = new Intent(activity, Class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ActivityCompat.startActivity(activity, intent, null);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    private void startNewActivityBack(Class<?> Class) {
        Intent newsIntent = new Intent(activity, Class);
        ActivityCompat.startActivity(activity, newsIntent, null);

    }


}
