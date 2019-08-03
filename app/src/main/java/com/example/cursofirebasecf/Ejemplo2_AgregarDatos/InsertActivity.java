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
    //Se declaran los widget
    EditText dato, clave;
    Button insertData;
    //Se hace la referencia a la base de datos de firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //Firebase firebase //version desactualizada para seguir usando en el curso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        //Codigo obsoleto que se esta usando en el curso
        //Firebase.setAndroidContext(this);
        //firebase = new Firebase("https://cursofirebasecf-97a1b.firebaseio.com/")

        //Se vinculan los widget con la interfaz
        dato = findViewById(R.id.data);
        clave = findViewById(R.id.key);
        insertData = findViewById(R.id.insertData);

        //Se crea el metodo Onclick para realizar un evento al presionar el boton
        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DatabaseReference myref = database.getReference("Name");
                myref.setValue(dato.getText().toString());¨*/

                //Se cachan los datos de los EditText y se almacenan en unas variables de tipo String
                String value = dato.getText().toString();
                String key = clave.getText().toString();

                //Se crea la referencia a los datos de la base de datos
                DatabaseReference myref = database.getReference();

                /*se hace referencia a la base y despues se referencia el dato del hijo con "child" lo cual queda representado
                  como su llave, key o ID y al final se agrega el dato del EditText*/
                //myref.child("ciudad").setValue(value);

                /*En esta parte es lo mismo pero aqui ya se utiliza ambos EditText para capturar la llave y el valor */
                //myref.child(key).setValue(value);

                /*Pero el setValue no permite agregar mas datos, es decir de acuerdo a la referencia lo que hace
                * es como actualizar el valor de acuerdo a la referencia de la llave, en caso de que la llave no
                * exista entonces si permitiria agregar un nuevo valor y una nueva llave a la base de datos como
                * si se agregara un dato nuevo, para solucionar este error existe otro metodo llamado PUSH en el
                * el cual este si permite agregar datos a la base de datos y este no actualiza el dato como lo hace
                * el setvalue por si solo*/

                //myref.push().setValue(value);

                /*Se Agrega un objeto a Firebase de tipo User (esto se creo anteriormente en el paquete Ejemplo2_AgregarDatos)
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
