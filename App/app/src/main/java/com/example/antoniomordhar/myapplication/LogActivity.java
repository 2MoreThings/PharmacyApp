package com.example.antoniomordhar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logreg);
    }

    public void goToAnActivityHome(View view) {
        Intent Intent = new Intent(this, MainActivity.class);
        startActivity(Intent);
    }
}
