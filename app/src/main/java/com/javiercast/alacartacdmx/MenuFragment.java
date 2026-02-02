package com.javiercast.alacartacdmx;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment {

    private String[] datos;

    public MenuFragment(String[] datos) {
        this.datos = datos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView listView = view.findViewById(R.id.listViewMenu);

        if (datos != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, datos);
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
}