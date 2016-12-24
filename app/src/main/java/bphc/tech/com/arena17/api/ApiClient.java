package bphc.tech.com.arena17.api;

import bphc.tech.com.arena17.app.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tejeshwar on 19/12/16.
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.APP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
