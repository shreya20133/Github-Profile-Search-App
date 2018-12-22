package com.example.dell1.github_okhttp;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    ProgressBar pb;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb=findViewById(R.id.pb);
        editText=findViewById(R.id.et);
        Button btn=findViewById(R.id.searchbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName=editText.getText().toString();
                pb.setVisibility(View.VISIBLE);
                makeNetworkCall("https://api.github.com/search/users?q="+userName);
            }
        });
    }
    private void makeNetworkCall(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;
                final String result = response.body().string();

                final Gson gson = new Gson();
                final Result apiResponse = gson.fromJson(result, Result.class);
                   Log.e("Tag","hi:"+apiResponse);
                (MainActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        rv=findViewById(R.id.rv);

//                        ArrayList<User> users = apiResponse.getUsers();
                        pb.setVisibility(View.GONE);

//                        if (apiResponse.getIncompleteResults()) {
//                            Toast.makeText(getBaseContext(), "Something went wrong!Try Again", Toast.LENGTH_SHORT).show();
//                        }
                        LinearLayoutManager llm=new LinearLayoutManager(MainActivity.this);
                        rv.setLayoutManager(llm);
                            ProfileAdapter profileAdapter = new ProfileAdapter(apiResponse, MainActivity.this);
                            rv.setAdapter(profileAdapter);

                    }
                });
            }});}

    }
