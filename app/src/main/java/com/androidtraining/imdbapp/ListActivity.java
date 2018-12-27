package com.androidtraining.imdbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
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
        etUserInput.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
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
                JSONObject jsonObject = new JSONObject();
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("Search");
                    for (int i=0;i<jsonArray.length();i++){

                        String title=jsonArray.getJSONObject(i).getString("Title");
                        String year = jsonArray.getJSONObject(i).getString("Year");
                        String imdbID = jsonArray.getJSONObject(i).getString("imdbID");
                        String type = jsonArray.getJSONObject(i).getString("Type");
                        String poster = jsonArray.getJSONObject(i).getString("Poster");
                        Movie movie= new Movie(title,year,imdbID,type,poster);
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
