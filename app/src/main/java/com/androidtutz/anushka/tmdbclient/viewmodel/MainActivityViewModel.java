package com.androidtutz.anushka.tmdbclient.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.androidtutz.anushka.tmdbclient.datasource.CharacterDataSource;
import com.androidtutz.anushka.tmdbclient.datasource.CharacterDataSourceFactory;
import com.androidtutz.anushka.tmdbclient.model.Character;
import com.androidtutz.anushka.tmdbclient.service.CharacterDataService;
import com.androidtutz.anushka.tmdbclient.service.RetrofitInstance;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {
    LiveData<CharacterDataSource> characterDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Character>> charactersPagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        CharacterDataService characterDataService = RetrofitInstance.getCharacterService();
        CharacterDataSourceFactory factory = new CharacterDataSourceFactory(characterDataService, application);
        characterDataSourceLiveData = factory.getMutableLiveData();

         PagedList.Config config=(new PagedList.Config.Builder())
                                   .setEnablePlaceholders(true)
                                   .setInitialLoadSizeHint(20)
                                   .setPageSize(20)
                                   .setPrefetchDistance(4)
                                    .build();

         executor= Executors.newFixedThreadPool(5);

        charactersPagedList = (new LivePagedListBuilder<Long, Character>(factory,config))
                 .setFetchExecutor(executor)
                 .build();
    }

    public LiveData<PagedList<Character>> getCharactersPagedList() {
        return charactersPagedList;
    }
}
