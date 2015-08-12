package net.telesurtv.www.telesur.data.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jhordan on 03/08/15.
 */
public class ItemAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);

                if (type.getRawType() == List.class)
                    return adaptJsonToNews(jsonElement, type);


                return null;
            }
        };
    }


    private <T> T adaptJsonToNews(JsonElement jsonElement, TypeToken<T> type) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.has("rss")) {

            JsonElement rssDataElement = jsonObject.get("rss");
            JsonObject rssObject = rssDataElement.getAsJsonObject();

            if (rssObject.has("channel")) {

                JsonElement channelDataElement = rssObject.get("channel");
                JsonObject channelObject = channelDataElement.getAsJsonObject();

                if (channelObject.has("item")) {
                    JsonElement newsJsonArray = channelObject.get("item");
                    return new Gson().fromJson(newsJsonArray, type.getType());
                }


            }

        }
        return null;
    }
}
