package net.telesurtv.www.telesur.data.api.models.news;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Jhordan on 03/08/15.
 */
public class ParserNews {

    public static News[] getListNews(String jsonlistNews) {

        try {
            JsonElement jsonElement = new JsonParser().parse(jsonlistNews);

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.has("rss")) {
                JsonElement rssDataElement = jsonObject.get("rss");
                JsonObject rssObject = rssDataElement.getAsJsonObject();

                if (rssObject.has("channel")) {
                    JsonElement channelDataElement = rssObject.get("channel");
                    JsonObject channelObject = channelDataElement.getAsJsonObject();

                    if (channelObject.has("item")) {
                        JsonElement newsJsonArray = channelObject.get("item");
                        return new GsonBuilder().create().fromJson(newsJsonArray, News[].class);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
