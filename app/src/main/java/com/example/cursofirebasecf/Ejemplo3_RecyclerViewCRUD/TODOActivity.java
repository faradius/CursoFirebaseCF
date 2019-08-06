package com.example.cursofirebasecf.Ejemplo3_RecyclerViewCRUD;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cursofirebasecf.R;

import java.util.ArrayList;
import java.util.List;

public class TODOActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Grupo> grupos;
    GrupoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        createData();

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new GrupoAdapter(grupos,this);
        recyclerView.setAdapter(adapter);
    }

    public void createData(){
        grupos = new ArrayList<>();
        grupos.add(new Grupo(1,"Grupo A"));
        grupos.add(new Grupo(2,"Grupo B"));
        grupos.add(new Grupo(3,"Grupo C"));
        grupos.add(new Grupo(4,"Grupo D"));
    }
}
