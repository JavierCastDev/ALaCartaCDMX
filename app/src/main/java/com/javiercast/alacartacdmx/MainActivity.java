package com.javiercast.alacartacdmx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.bar);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.listView);

        char inicio = 'A';
        String[] items = new String[4];
        for (int i = 0; i < items.length; i++) {
            char letraActual = (char) (inicio + i);
            items[i] = "Restaurante " + letraActual;
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String seleccionado = (String) parent.getItemAtPosition(position);
            Toast.makeText(MainActivity.this, "Has seleccionado: " + seleccionado, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
        menu.setHeaderTitle("Seleccione una opción");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.opcion_comida) {
            Toast.makeText(this, "Abriendo Comida...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.opcion_bebida) {
            Toast.makeText(this, "Abriendo Bebidas...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.opcion_complementos) {
            Toast.makeText(this, "Abriendo Complementos...", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}