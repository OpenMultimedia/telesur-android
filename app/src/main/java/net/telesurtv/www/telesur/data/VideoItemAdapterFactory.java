package net.telesurtv.www.telesur.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Jhordan on 29/07/15.
 */
public class VideoItemAdapterFactory implements TypeAdapterFactory {
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


                return adapterJsonToVideo(jsonElement, type);


            }
        }.nullSafe();

    }

    private <T> T adapterJsonToVideo(JsonElement jsonElement, TypeToken<T> type) {
        return new Gson().fromJson(jsonElement.getAsJsonArray(), type.getType());

    }


}
