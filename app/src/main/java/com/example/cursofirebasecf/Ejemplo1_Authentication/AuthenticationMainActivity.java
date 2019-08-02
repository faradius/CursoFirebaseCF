package com.example.cursofirebasecf.Ejemplo1_Authentication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cursofirebasecf.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationMainActivity extends AppCompatActivity {
    private static final String TAG = "AuthMainActivity";
    //Se declaran los widgets
    Button registrar,iniciarSesion,cerrarSesion;
    EditText correo, pass;
    TextView estado;
    //Se declara la clase de FirebaseAuth para hacer uso del auntenticador
    private FirebaseAuth mAuth;
    /*Se declara un escuchador para el FirebaseAuth en el cual se hara uso
    para saber el estado de la auntenticación con Firebase*/
    private  FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_main);
        /*Vinculando los widgets con la interfaz*/
        registrar = findViewById(R.id.register);
        iniciarSesion = findViewById(R.id.login);
        cerrarSesion = findViewById(R.id.logout);
        correo = findViewById(R.id.correo);
        pass = findViewById(R.id.password);
        estado = findViewById(R.id.state);

        //Se crea una instancia hacia la base de datos o se vincula con la base de datos de Firebase
        mAuth = FirebaseAuth.getInstance();
        //Se crea un objeto del escuchador del auntenticador de cuentas en Firebase
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            //Se utiliza el onAuthStateChanged para determinar los cambios que suceden al auntenticarse
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Se crea un escuchador para analizar la captura de un usuario aunteticarse
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //Se valida la autenticacion de un usuario para saber si esta logueado o no
                if(user!=null){
                    Log.d(TAG,"Esta logeado: " + user.getUid()); //Se obtiene su id del usuario
                }else{
                   Log.d(TAG, "No esta logeado");
                }
            }
        };
        /*Se crea el evento de clic en los botones agregados en la interfaz de la aplicacion
        // y se agregan los metodos que corresponden a cada funcion (Iniciar sesion, crear cuenta, cerrar sesion)
           y se actualiza el estado de la sesión*/
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        updateState();
    }

    //Se crea un metodo en el cual verifica el estado de la sesión de la cuenta
    private void updateState(){
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            estado.setText("Sesion Iniciada: "+ user.getEmail());
        }else{
            estado.setText("Sin sesion iniciada");
        }
    }

    //Se crea un metodo en el cual se muestra un mensaje de acuerdo al estado de la sesión
    private void updateState(String mensaje){
        estado.setText(mensaje);
    }

    //Se crea un metodo para la creacion de usuarios
    private  void createUser(){
        //Se declaran las variables de correo y contraseña para almacenar las credenciales
        String email,password;
        //Se validan los campos para verificar que los campos tienen texto en caso contrario mandara un mensaje de "Datos incorrectos"
        if (!checkFields()){
            Toast.makeText(getApplicationContext(),"Datos incorrectos", Toast.LENGTH_SHORT).show();
        }else {
            /*Si los campos contiene texto entonces se almacenaran los datos capturados en
            los EditText para almacenarlos en las variables declararadas dentro del metodo*/
            email = correo.getText().toString();
            password = pass.getText().toString();
            /*Se hace uso de la clase FirebaseAuth para hacer uso del metodo "createUserWithEmailAndPassword" para crear
            * un usuario con correo y contraseña, despues de eso se utiliza un escuchador para saber lo que pasa, es decir,
            * si se creo correctamente el usuario o no se pudo crear*/
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Se creo el usuario", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
                /*Se coloca un escuchador de posibles errores que puede llegar a sucitar, en este caso seria verificar si el
                * usuario ya esta en uso o ya existe, y esto se le agrega el metodo que ya se habia creado, el cual es
                * "updateState" pero con argumentos en el metodo para asi mostrar el mensaje*/
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof FirebaseAuthUserCollisionException){
                        updateState("Este correo ya esta en uso");
                    }else{
                        updateState(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    //Se crea un metodo para iniciar sesion de la cuenta
    private void signIn(){
        String emailSign, passwordSign;
        //Se verifica los campos
        if (!checkFields()){
            Toast.makeText(getApplicationContext(),"Datos incorrectos", Toast.LENGTH_SHORT).show();
        }else {
            emailSign = correo.getText().toString();
            passwordSign = pass.getText().toString();

            //Se inicia sesión con correo y contraseña, despues se verifica lo que sucede al iniciar sesión con un listener
            mAuth.signInWithEmailAndPassword(emailSign,passwordSign).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Sign in", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Sign in failed",Toast.LENGTH_SHORT).show();
                    }

                    updateState();
                }
                //Se verifica los posibles errores que pueden suceder de manera mas personalizada
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof FirebaseAuthInvalidCredentialsException){
                        updateState("Contraseña equivocada");
                    }else if(e instanceof FirebaseAuthInvalidUserException){
                        updateState("No existe este usuario");
                    }else{
                        updateState(e.getLocalizedMessage());
                    }
                }
            });
        }
    }
    //Se crea el metodo cerrar sesion y se actualiza el estado
    private void signOut(){
        mAuth.signOut();
        updateState();
    }

    //Se crea un metodo para verificar los campos de los EditText
    private boolean checkFields(){
        String correoCheck,passCheck;
        correoCheck = correo.getText().toString();
        passCheck = pass.getText().toString();

        if (correoCheck.isEmpty()){
            Toast.makeText(getApplicationContext(),"Escribir un correo",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (passCheck.isEmpty()){
            Toast.makeText(getApplicationContext(),"Escribir una contraseña", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /*Se utiliza el metodo onStart del activity para que inicie el escuchador del aunteticador de
    firebase y asi pueda determinar el logueo de la cuenta*/
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    /*Aqui igual se utiliza el metodo onStop para que se elimine el escuchador del aunteticador de
    * Firebase y no se siga ejecutando en segundo plano*/
    protected void onStop(){
        super.onStop();
        if(mAuthStateListener!=null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

}
