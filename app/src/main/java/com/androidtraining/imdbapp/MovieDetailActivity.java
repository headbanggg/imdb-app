package com.androidtraining.imdbapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    TextView tvTitle;
    TextView tvYear;
    TextView tvType;
    TextView tvPlot;
    RelativeLayout rlPoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //todo(2): intent ile imdbid çekilecek ve URL oluşturulacak.
        Intent intent =getIntent();
        String imdbId = intent.getStringExtra("imdbid");
        String url = "https://www.omdbapi.com/?apikey=bc7175&plot=full&i="+imdbId;
         tvTitle= findViewById(R.id.tvDetailTitle);
         tvYear = findViewById(R.id.tvDetailYear);
         tvType = findViewById(R.id.tvDetailType);
         tvPlot = findViewById(R.id.tvDetailPlot);
         rlPoster=findViewById(R.id.rlDetailPoster);

        //todo(4): requestqueue  oluşturulacak.
         requestQueue= Volley.newRequestQueue(this);
        filmDetayiIndir(url);
    }


    //todo(5): filmDetayiIndir metodu oluşturulacak.
    void filmDetayiIndir(final String url){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String title=jsonObject.getString("Title");
                    String year = jsonObject.getString("Year");
                    String plot= jsonObject.getString("Plot");
                    String type = jsonObject.getString("Type");
                    String poster = jsonObject.getString("Poster");

                    tvTitle.setText(title);
                    tvYear.setText(year);
                    tvType.setText(type);
                    tvPlot.setText(plot);
                   // rlPoster.setBackground();
                    Glide.with(MovieDetailActivity.this).load(poster).into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            rlPoster.setBackground(resource);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }
}
