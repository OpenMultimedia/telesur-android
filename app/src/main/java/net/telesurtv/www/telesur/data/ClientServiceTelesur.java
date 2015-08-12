package net.telesurtv.www.telesur.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import net.telesurtv.www.telesur.data.api.ItemAdapterFactory;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Jhordan on 28/07/15.
 */
public class ClientServiceTelesur {

    private static RestAdapter restAdapter;


    private static RestAdapter telesurRestAdapter() {

        return new RestAdapter.Builder()
                .setEndpoint(EndPoint.TELESUR_API)
                      // .setConverter(new GsonConverter(getGsonBuilder()))
                        // .setErrorHandler(new RetrofitErrorHandler())
                .build();

    }

    public static RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            restAdapter = telesurRestAdapter();
        }
        return restAdapter;
    }




    private static Gson getConverter() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new ItemAdapterFactory())
                .create();
    }


    private static Gson getGsonBuilder() {

        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
    }


}
