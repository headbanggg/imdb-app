package com.androidtraining.imdbapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        String imdbid = intent.getStringExtra("myimdbid");
        String url = "http://www.omdbapi.com/?apikey=20c154f9&plot=full&i="+imdbid;
        final TextView tvTitle= findViewById(R.id.tvDetailTitle);
        final TextView tvYear = findViewById(R.id.tvDetailYear);
        final TextView tvType = findViewById(R.id.tvDetailType);
        final TextView tvPlot = findViewById(R.id.tvDetailPlot);
        final ImageView ivPoster= findViewById(R.id.ivDetailPoster);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String title = jsonObject.getString("Title");
                    String year = jsonObject.getString("Year");
                    String type = jsonObject.getString("Type");
                    String plot = jsonObject.getString("Plot");
                    String poster = jsonObject.getString("Poster");


                    Glide.with(MovieDetailActivity.this).load(poster).into(ivPoster);

                    tvTitle.setText(title);
                    tvYear.setText(year);
                    tvType.setText(type);
                    tvPlot.setText(plot);

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
