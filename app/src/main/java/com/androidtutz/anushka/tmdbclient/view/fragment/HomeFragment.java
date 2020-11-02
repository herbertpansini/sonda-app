package com.androidtutz.anushka.tmdbclient.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.view.adapter.HomeAdapter;
import com.androidtutz.anushka.tmdbclient.model.Wiki;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Wiki> list;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listview = (ListView) view.findViewById(R.id.listview);
        populateList();
        HomeAdapter adapter = new HomeAdapter(getActivity(), list);
        listview.setAdapter(adapter);
        return view;
    }

    private void populateList() {
        list = new ArrayList<>();

        list.add(new Wiki("Gênero", "Editora"));
        list.add(new Wiki("Fundação", "1939 (como Timely Comics)"));
        list.add(new Wiki("Fundadores", "Martin Goodman"));
        list.add(new Wiki("Sede", "135 W. 50th Street, Nova York, NY, Estados Unidos"));
        list.add(new Wiki("Proprietário(s)", "The Walt Disney Company, dona da Marvel Entertainment, LLC, companhia à qual pertence a Marvel Comics."));
        list.add(new Wiki("Presidente", "Kevin Feige"));
        list.add(new Wiki("Pessoas-chave", "C.B. Cebulski, editor-chefe Dan Buckley, editor, COO Stan Lee, antigo editor-chefe, editor"));
        list.add(new Wiki("Produtos", "Revistas, livros e filmes"));
        list.add(new Wiki("Renda líquida", "US$326.3 milhões (2015)"));
        list.add(new Wiki("Website oficial", "www.marvel.com"));
    }
}