package com.androidtutz.anushka.tmdbclient.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.view.adapter.CharacterAdapter;
import com.androidtutz.anushka.tmdbclient.databinding.FragmentCharacterBinding;
import com.androidtutz.anushka.tmdbclient.model.Character;
import com.androidtutz.anushka.tmdbclient.viewmodel.MainActivityViewModel;

public class CharacterFragment extends Fragment {
    private Context mContext;

    private PagedList<Character> characters;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private FragmentCharacterBinding fragmentCharacterBinding;

    public CharacterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();

        mainActivityViewModel= ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

        getCharacters();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentCharacterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character, container, false);
        return fragmentCharacterBinding.getRoot();
    }

    public void getCharacters() {
        mainActivityViewModel.getCharactersPagedList().observe(this, new Observer<PagedList<Character>>() {
            @Override
            public void onChanged(@Nullable PagedList<Character> charactersFromLiveData) {
                characters = charactersFromLiveData;
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView() {
        recyclerView = fragmentCharacterBinding.rvMovies;
        characterAdapter = new CharacterAdapter(this.mContext);
        characterAdapter.submitList(characters);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(characterAdapter);
        characterAdapter.notifyDataSetChanged();
    }
}