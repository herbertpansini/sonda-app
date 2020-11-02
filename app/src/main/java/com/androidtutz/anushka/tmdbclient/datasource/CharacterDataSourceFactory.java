package com.androidtutz.anushka.tmdbclient.datasource;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.androidtutz.anushka.tmdbclient.service.CharacterDataService;

public class CharacterDataSourceFactory extends DataSource.Factory {
    private CharacterDataSource characterDataSource;
    private CharacterDataService characterDataService;
    private Application application;
    private MutableLiveData<CharacterDataSource> mutableLiveData;

    public CharacterDataSourceFactory(CharacterDataService characterDataService, Application application) {
        this.characterDataService = characterDataService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        characterDataSource = new CharacterDataSource(characterDataService, application);
        mutableLiveData.postValue(characterDataSource);
        return characterDataSource;
    }

    public MutableLiveData<CharacterDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
