package net.telesurtv.www.telesur.views.settings;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pushwoosh.PushManager;
import com.pushwoosh.SendPushTagsCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jhordan on 29/09/15.
 */
public class Notification {

    private static final String NOTIFICATION_SECTION = "seleccion";

    private static Map<String, Object> setMapTags(String selection) {
        Map<String, Object> tags = new HashMap<>();
        tags.put(NOTIFICATION_SECTION, selection);
        return tags;
    }


    public static void sentTagsToPushWoosh(final Context context,String tags) {

        PushManager.sendTags(context, setMapTags(tags), new SendPushTagsCallBack() {
            @Override
            public void taskStarted() {
                //Making push changes
            }

            @Override
            public void onSentTagsSuccess(Map<String, String> map) {

              //  Log.v("NOTIFICATION_TEST", (String) map.get(NOTIFICATION_SECTION));
                Toast.makeText(context, "Se han guardado los temas de tu interes correctamente", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSentTagsError(Exception e) {
                e.printStackTrace();
            }

        });

    }


}
