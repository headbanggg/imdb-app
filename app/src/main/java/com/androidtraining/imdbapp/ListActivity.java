package com.androidtraining.imdbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    String baseUrl = "http://www.omdbapi.com/?apikey=20c154f9&s=";
    RequestQueue requestQueue;
    EditText etUserInput;
    List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        movieList= new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        etUserInput = findViewById(R.id.etUserInput);
        RecyclerView recyclerView = findViewById(R.id.rvMovie);
        final RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(movieList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListActivity.this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setAdapter(recyclerviewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        etUserInput.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
             movieList.clear();
             recyclerviewAdapter.notifyDataSetChanged();
            filmDatasiIndir();
         }
     });

    }

    private String urlyicevir(String baseUrl ,EditText userInput){
        String url= baseUrl+userInput.getText().toString();
        return url;
    }
    private void filmDatasiIndir(){

        String url=urlyicevir(baseUrl,etUserInput);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject= new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Search");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject movieObject =jsonArray.getJSONObject(i);
                        String title= movieObject.getString("Title");
                        String year = movieObject.getString("Year");
                        String imdbId= movieObject.getString("imdbID");
                        String type = movieObject.getString("Type");
                        String poster = movieObject.getString("Poster");

                        Movie movie = new Movie(title,year,imdbId,type,poster);
                        movieList.add(movie);


                    }

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
