package com.androidtutz.anushka.tmdbclient.datasource;

import android.app.Application;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.androidtutz.anushka.tmdbclient.model.Character;
import com.androidtutz.anushka.tmdbclient.model.DBResponse;
import com.androidtutz.anushka.tmdbclient.service.CharacterDataService;
import com.androidtutz.anushka.tmdbclient.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDataSource extends PageKeyedDataSource<Long, Character> {
    private CharacterDataService characterDataService;
    private Application application;

    public CharacterDataSource(CharacterDataService characterDataService, Application application) {
        this.characterDataService = characterDataService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Character> callback) {

        this.characterDataService = RetrofitInstance.getCharacterService();
        Call<DBResponse> call = this.characterDataService.getCharacters(0L);

        call.enqueue(new Callback<DBResponse>() {
            @Override
            public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {

                DBResponse dbResponse = response.body();
                ArrayList<Character> characters = new ArrayList<>();

                if(dbResponse != null && dbResponse.getData().getResults() != null) {
                    characters = (ArrayList<Character>) dbResponse.getData().getResults();

                    callback.onResult(characters, null, (long) 1);
                }
            }

            @Override
            public void onFailure(Call<DBResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Character> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Character> callback) {

        this.characterDataService = RetrofitInstance.getCharacterService();
        Call<DBResponse> call = this.characterDataService.getCharacters(params.key);

        call.enqueue(new Callback<DBResponse>() {
            @Override
            public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {

                DBResponse dbResponse = response.body();
                ArrayList<Character> characters = new ArrayList<>();

                if(dbResponse != null && dbResponse.getData().getResults() != null) {
                    characters = (ArrayList<Character>) dbResponse.getData().getResults();
                    callback.onResult(characters, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<DBResponse> call, Throwable t) {
            }
        });

    }
}
