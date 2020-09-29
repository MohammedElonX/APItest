package com.example.apitest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ScrollingActivity extends AppCompatActivity {

    private OkHttpClient client;
    private TextView textV;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not Available", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textV = findViewById(R.id.textVi);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getResponse();
            }
        });
    }

    private void getResponse() {
        String value = "Token b3aacd45d256fb778973feb6aa8839c1fcdb4f13";
        String token = (String) Base64.encodeToString(value.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        client = new OkHttpClient().newBuilder()
                .build();

        //This works
        Request st = new Request.Builder()
                .url("https://www.gladtidingsdata.com/api/user/")
                .method("GET", null)
                .addHeader("Authorization", "Token 09439c438a708e2056024a536ad9609842e105aa")
                .addHeader("Content-Type", "application/json")
                .build();

        //Doesn't work [internal server error]
        Request request = new Request.Builder()
                .url("https://www.husmodata.com/api/user/")
                .method("GET", null)
                .addHeader("Authorization", "Token b3aacd45d256fb778973feb6aa8839c1fcdb4f13")
                .addHeader("Content-Type", "application/json")
                .build();

        //This works
        MediaType mediaTyp = MediaType.parse("application/json");
        RequestBody bod = RequestBody.create(mediaTyp, " {\"network\": 1,\n \"amount\" :100, \n \"mobile_number\": \"07061181139\"}");
        Request Airrequest = new Request.Builder()
                .url("https://www.gladtidingsdata.com/api/topup/")
                .method("POST", bod)
                .addHeader("Authorization", "Token 09439c438a708e2056024a536ad9609842e105aa")
                .addHeader("Content-Type", "application/json")
                .build();

        //Does'nt work [Ported_number is required]
        MediaType mediaTye = MediaType.parse("application/json");
        RequestBody bdy = RequestBody.create(mediaTye, " {\"network\": 1,\n \"amount\" :100, \n \"mobile_number\": \"07061181139\"}");
        Request AirHrequest = new Request.Builder()
                .url("https://www.husmodata.com/api/topup/")
                .method("POST", bdy)
                .addHeader("Authorization", "Token b3aacd45d256fb778973feb6aa8839c1fcdb4f13")
                .addHeader("Content-Type", "application/json")
                .build();


        client.newCall(AirHrequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textV.setText("Error fetching data..!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            textV.setText("Output: \n" + response.body().string() + "\n" + response.message());
                        } catch (IOException e) {
                            textV.setText("Nothing found...!!!");
                        }
                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, monnifyApi.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
