package com.example.antoniomordhar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class AniadirActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);
    }
    public void goToAnActivityHome(View view) {
        Intent Intent = new Intent(this, MainActivity.class);
        startActivity(Intent);
    }

    public void sendPost(View view) {
        Thread thread = new Thread(new Runnable() {
            EditText nom = (EditText)findViewById(R.id.nominput);
            EditText ca = (EditText)findViewById(R.id.cainput);
            EditText pre = (EditText)findViewById(R.id.preinput);
            String strnom = nom.getText().toString();
            String strca = ca.getText().toString();
            int strpre = Integer.parseInt(pre.getText().toString());
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2:8080/SampleWebProject/restservices/productcatalog/insert");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("id", 123);
                    jsonParam.put("name", strnom);
                    jsonParam.put("category", strca);
                    jsonParam.put("unitPrice", strpre);


                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
