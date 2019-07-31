package com.example.cursofirebasecf.Ejemplo2_AgregarDatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cursofirebasecf.R;

public class AgregarDatosMainActivity extends AppCompatActivity {
    Button insertarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_datos_main);

        insertarDatos = findViewById(R.id.btnInsertActivity);

        insertarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarDatosMainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
    }
}
