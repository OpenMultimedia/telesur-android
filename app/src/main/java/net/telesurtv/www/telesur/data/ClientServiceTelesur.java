package net.telesurtv.www.telesur.data;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;

/**
 * Created by Jhordan on 28/07/15.
 */
public class ClientServiceTelesur {

    private static Retrofit retrofit;


    private static Retrofit telesurRestAdapter() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(TelesurApiConstants.TELESUR_API)
                .build();
    }

    public static Retrofit getRestAdapter() {
        if (retrofit == null)
            retrofit = telesurRestAdapter();

        return retrofit;
    }

    public static Retrofit getStaticRestAdapter() {
        return new Retrofit.Builder()
                .baseUrl(TelesurApiConstants.TELESUR_STATIC)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }


}
