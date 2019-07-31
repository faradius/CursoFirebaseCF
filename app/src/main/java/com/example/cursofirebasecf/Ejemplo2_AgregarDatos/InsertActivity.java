package com.example.cursofirebasecf.Ejemplo2_AgregarDatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.cursofirebasecf.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InsertActivity extends AppCompatActivity {
    EditText dato;
    Button insertData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //Firebase firebase //version desactualizada para seguir usando en el curso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        //Codigo obsoleto que se esta usando en el curso
        //Firebase.setAndroidContext(this);
        //firebase = new Firebase("https://cursofirebasecf-97a1b.firebaseio.com/")

        dato = findViewById(R.id.data);
        insertData = findViewById(R.id.insertData);

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myref = database.getReference("Name");
                myref.setValue(dato.getText().toString());
                //Codigo obsoleto que se esta usando en el curso
                //Firebase refFirebase = firebase.child("");
                //refFirebase.setValue(dato.getText().toString());
            }
        });
    }
}
