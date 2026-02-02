package com.javiercast.alacartacdmx;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DescripcionPlatilloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_platillo);

        Toolbar toolbar = findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String platillo = getIntent().getStringExtra("platillo");
        getSupportActionBar().setTitle("Descripción del Alimento");

        TextView tvNombre = findViewById(R.id.txtNombrePlatillo);
        tvNombre.setText(platillo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}