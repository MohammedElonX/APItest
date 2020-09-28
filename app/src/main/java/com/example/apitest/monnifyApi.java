package com.example.apitest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class monnifyApi extends AppCompatActivity {

    private TextView textView;
    private Button btnn;
    private OkHttpClient cli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monnify_api);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not Available yet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnn = findViewById(R.id.button);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });

        textView = findViewById(R.id.textView);
    }

    private void getResult() {
        String value = "Basic MK_TEST_UVTAAJMW4M:KRH38AY22SV83H7ACSRQYUJE7JDYYTQM";
        String token = (String) Base64.encodeToString(value.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
       cli = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "abuhaneefah/tubhlahy");

        Request request = new Request.Builder()
                .url("https://sandbox.monnify.com/api/v1/auth/login/")
                .method("POST", body)
                .addHeader("Authorization", value)
                .build();
       cli.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Error fetching data..!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Output: \n" + res);
                    }
                });

            }
        });
    }

}
