package com.example.antoniomordhar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToAnActivityLista(View view) {
        Intent Intent = new Intent(this, ListaActivity.class);
        startActivity(Intent);
    }

    public void goToAnActivityAniadir(View view) {
        Intent Intent = new Intent(this, AniadirActivity.class);
        startActivity(Intent);
    }
    public void goToAnActivityLog(View view) {
        Intent Intent = new Intent(this, LogActivity.class);
        startActivity(Intent);
    }

    public void goToAnActivityCarrito(View view){
        Intent Intent = new Intent(this, CarritoActivity.class);
        startActivity(Intent);
    }
}
