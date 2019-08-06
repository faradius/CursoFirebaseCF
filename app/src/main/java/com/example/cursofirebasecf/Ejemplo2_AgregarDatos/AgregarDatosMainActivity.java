package com.example.cursofirebasecf.Ejemplo2_AgregarDatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cursofirebasecf.Ejemplo3_RecyclerViewCRUD.TODOActivity;
import com.example.cursofirebasecf.R;

public class AgregarDatosMainActivity extends AppCompatActivity {
    //Se declara el widget que se va a utilizar
    Button insertarDatos, obtenerDatos, listaDatos, todoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_datos_main);

        //Se vincula el widget con la interfaz
        insertarDatos = findViewById(R.id.btnInsertActivity);
        obtenerDatos = findViewById(R.id.btnObtenerDatos);
        listaDatos = findViewById(R.id.btnListActivity);
        todoActivity = findViewById(R.id.todoActivity);

        //Se crea el metodo Onclick para pasar de un activity a otro
        insertarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarDatosMainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

        obtenerDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent(AgregarDatosMainActivity.this, RetrieveActivity.class);
                startActivity(rintent);
            }
        });

        listaDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentList = new Intent(AgregarDatosMainActivity.this, ListItemsActivity.class);
                startActivity(intentList);
            }
        });

        todoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTODO = new Intent(AgregarDatosMainActivity.this, TODOActivity.class);
                startActivity(intentTODO);
            }
        });
    }
}
