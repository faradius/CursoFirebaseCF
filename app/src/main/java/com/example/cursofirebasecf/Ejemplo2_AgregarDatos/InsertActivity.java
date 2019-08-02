package com.example.cursofirebasecf.Ejemplo2_AgregarDatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.cursofirebasecf.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class InsertActivity extends AppCompatActivity {
    EditText dato, clave;
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
        clave = findViewById(R.id.key);
        insertData = findViewById(R.id.insertData);

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DatabaseReference myref = database.getReference("Name");
                myref.setValue(dato.getText().toString());¨*/

                String value = dato.getText().toString();
                String key = clave.getText().toString();
                DatabaseReference myref = database.getReference();

                //myref.child("ciudad").setValue(value);
                //myref.child(key).setValue(value);
                //myref.push().setValue(value);

                /*Agregar un objeto a Firebase
                User user = new User("Raul","Poza Rica",20);
                myref.child("users").child(key).setValue(user);
                */

                List<User> userList = new ArrayList<>();



                //Codigo obsoleto que se esta usando en el curso
                //Firebase refFirebase = firebase.child("");
                //refFirebase.setValue(dato.getText().toString());

                /*String value = dato.getText().toString();
                * Firebase child = firebase.child("name");
                * child.setValue(values);*/
            }
        });
    }
}
