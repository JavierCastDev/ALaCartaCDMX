package com.javiercast.alacartacdmx;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    private String[] datos;
    private ArrayAdapter<String> adapter;

    public MenuFragment(String[] datos) {
        this.datos = datos;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView listView = view.findViewById(R.id.listViewMenu);

        if (datos != null) {
            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, datos);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, v, position, id) -> {
                String seleccionado = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), DescripcionPlatilloActivity.class);
                intent.putExtra("platillo", seleccionado);
                startActivity(intent);
            });
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) {
                    adapter.getFilter().filter(newText);
                }
                return false;
            }
        });
    }
}