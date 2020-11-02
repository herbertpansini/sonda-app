package com.androidtutz.anushka.tmdbclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://gateway.marvel.com/v1/public/";

    private static final String TS = "1";
    private static final String API_KEY = "f3ca09e81ccbb2027c4db98d89755038";
    private static final String HASH = "eb85b646028edcd63d14a78964cf1a90";
    private static final String SORT = "name";

    public static CharacterDataService getCharacterService() {
        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        HttpUrl originalHttpUrl = originalRequest.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("ts", TS)
                                .addQueryParameter("apikey", API_KEY)
                                .addQueryParameter("hash", HASH)
                                .addQueryParameter("orderBy", SORT)
                                .build();

                        Request.Builder requestBuilder = originalRequest.newBuilder()
                                .url(url);

                        Request newRequest = requestBuilder.build();

                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(httpLoggingInterceptor)
                .build();

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(okHttpClient)
                        .build();
            }
            return retrofit.create(CharacterDataService.class);
    }
}