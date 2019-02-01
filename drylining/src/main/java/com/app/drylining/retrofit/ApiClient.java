package com.app.drylining.retrofit;


import com.app.drylining.util.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

   // public static final String BASE_URL ="http://dryliningapp.com/admin/api/";
    public static final String BASE_URL = Constant.WEBSERVICE_PATH;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100,TimeUnit.SECONDS).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)/*.client(client)*/
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}