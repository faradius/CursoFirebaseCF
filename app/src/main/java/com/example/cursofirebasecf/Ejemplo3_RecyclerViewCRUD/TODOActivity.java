package com.example.cursofirebasecf.Ejemplo3_RecyclerViewCRUD;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cursofirebasecf.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TODOActivity extends AppCompatActivity {
    EditText addIdGroup,addNameGroup;
    Button btnAdd;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    //List<Grupo> grupos;
    //GrupoAdapter adapter;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<Grupo,GrupoAdapter.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        addIdGroup = findViewById(R.id.addIdGroup);
        addNameGroup = findViewById(R.id.addGroupName);
        btnAdd = findViewById(R.id.btnAdd);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        //createData();

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //adapter = new GrupoAdapter(grupos,this);
        FirebaseRecyclerOptions<Grupo> options =
                new FirebaseRecyclerOptions.Builder<Grupo>()
                        .setQuery(databaseReference.child("Grupos"),Grupo.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Grupo, GrupoAdapter.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GrupoAdapter.ViewHolder holder, final int position, @NonNull Grupo model) {
                holder.idtxt.setText(String.valueOf(model.getId()));
                holder.nametxt.setText(model.getGroupname());
                holder.imageViewTrash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.getRef(position).removeValue();
                    }
                });
            }

            @NonNull
            @Override
            public GrupoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
                return new GrupoAdapter.ViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(addIdGroup.getText().toString());
                String groupname = addNameGroup.getText().toString();
                Grupo grupo = new Grupo(id,groupname);
                databaseReference.child("Grupos").push().setValue(grupo);
            }
        });
    }

    /*public void createData(){
        grupos = new ArrayList<>();
        grupos.add(new Grupo(1,"Grupo A"));
        grupos.add(new Grupo(2,"Grupo B"));
        grupos.add(new Grupo(3,"Grupo C"));
        grupos.add(new Grupo(4,"Grupo D"));
    }*/
}
