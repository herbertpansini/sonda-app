package com.androidtutz.anushka.tmdbclient.service;

import com.androidtutz.anushka.tmdbclient.model.DBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterDataService {
    @GET("characters")
    Call<DBResponse> getCharacters(@Query("offset") Long offset);
}
