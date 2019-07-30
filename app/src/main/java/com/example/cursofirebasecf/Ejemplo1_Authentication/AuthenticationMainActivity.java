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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationMainActivity extends AppCompatActivity {
    private static final String TAG = "AuthMainActivity";
    Button registrar,iniciarSesion,cerrarSesion;
    EditText correo, pass;
    TextView estado;
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_main);

        registrar = findViewById(R.id.register);
        iniciarSesion = findViewById(R.id.login);
        cerrarSesion = findViewById(R.id.logout);

        correo = findViewById(R.id.correo);
        pass = findViewById(R.id.password);

        estado = findViewById(R.id.state);

        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    Log.d(TAG,"Esta logeado: " + user.getUid());
                }else{
                   Log.d(TAG, "No esta logeado");
                }
            }
        };

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

    private void updateState(){
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            estado.setText("Sesion Iniciada: "+ user.getEmail());
        }else{
            estado.setText("Sin sesion iniciada");
        }
    }

    private  void createUser(){
        String email,password;
        if (!checkFields()){
            Toast.makeText(getApplicationContext(),"Datos incorrectos", Toast.LENGTH_SHORT).show();
        }else {
            email = correo.getText().toString();
            password = pass.getText().toString();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Se creo el usuario", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void signIn(){
        String emailSign, passwordSign;
        if (!checkFields()){
            Toast.makeText(getApplicationContext(),"Datos incorrectos", Toast.LENGTH_SHORT).show();
        }else {
            emailSign = correo.getText().toString();
            passwordSign = pass.getText().toString();

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
            });
        }
    }

    private void signOut(){
        mAuth.signOut();
        updateState();
    }
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

}
