package com.androidtraining.imdbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        TextView tvTitle= findViewById(R.id.tvDetailTitle);
        TextView tvYear = findViewById(R.id.tvDetailYear);
        TextView tvType = findViewById(R.id.tvDetailType);
        TextView tvPlot = findViewById(R.id.tvDetailPlot);
    }
}
