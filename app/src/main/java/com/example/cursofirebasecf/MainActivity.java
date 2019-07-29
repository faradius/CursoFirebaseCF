package com.example.cursofirebasecf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    Button botonA, botonB;
    private FirebaseAnalytics mFirebaseAnalytics;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        bundle = new Bundle();

        botonA = findViewById(R.id.btnA);
        botonB = findViewById(R.id.btnB);

        botonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID,String.valueOf(view.getId()));
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);
                Log.d("Mensaje","Se dio click en el boton a");
            }
        });

        botonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAnalytics.logEvent("BotonB",bundle);
                Log.d("Mensaje","Se dio click en el boton b");
            }
        });
    }
}
