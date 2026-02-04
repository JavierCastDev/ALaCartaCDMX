package com.javiercast.alacartacdmx;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroRestaurantActivity extends AppCompatActivity {

    private EditText etNombre;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_restaurant); // Asegúrate que este sea el nombre de tu XML

        // 1. Vinculamos los componentes del XML con Java
        etNombre = findViewById(R.id.etNombreRestaurant);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // 2. Configuramos el evento del botón
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();

                if (!nombre.isEmpty()) {
                    registrarRestaurante(nombre); // Llamamos al método aquí
                } else {
                    Toast.makeText(RegistroRestaurantActivity.this, "Escribe un nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 3. Aquí pegas el método que te pasé anteriormente
    private void registrarRestaurante(String nombre) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("name", nombre);

        long id = db.insert("restaurant", null, registro);
        db.close();

        if (id > 0) {
            Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK); // Avisa a la lista principal que hubo un cambio
            finish();
        } else {
            Toast.makeText(this, "Error al registrar restaurante", Toast.LENGTH_SHORT).show();
        }
    }
}