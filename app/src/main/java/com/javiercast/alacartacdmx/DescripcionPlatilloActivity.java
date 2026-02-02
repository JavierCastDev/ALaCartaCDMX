package com.javiercast.alacartacdmx;

import android.os.Bundle;
import android.widget.ImageView;
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
        String tipo = getIntent().getStringExtra("tipo");
        getSupportActionBar().setTitle("Detalle del Alimento");

        ImageView imagen = findViewById(R.id.imgPlatillo);
        TextView tvNombre = findViewById(R.id.txtNombrePlatillo);
        TextView tvDesc = findViewById(R.id.txtDescripcionPlatillo);

        tvNombre.setText(platillo);

        if ("comida".equals(tipo)) {
            imagen.setImageResource(R.drawable.foto_comida);
            tvDesc.setText("Platillo preparado con ingredientes frescos del centro histórico.");
        } else if ("bebida".equals(tipo)) {
            imagen.setImageResource(R.drawable.foto_bebida);
            tvDesc.setText("Bebida artesanal refrescante de la casa.");
        } else {
            imagen.setImageResource(R.drawable.foto_complemento);
            tvDesc.setText("El acompañamiento ideal para complementar tu experiencia.");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}