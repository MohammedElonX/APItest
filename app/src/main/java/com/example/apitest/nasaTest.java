package com.example.apitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import okhttp3.OkHttpClient;

public class nasaTest extends AppCompatActivity {

    private OkHttpClient client;
    private TextView textV;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_test);

        textV = findViewById(R.id.nasaTextView2);
        btn = findViewById(R.id.butn);
    }
}
