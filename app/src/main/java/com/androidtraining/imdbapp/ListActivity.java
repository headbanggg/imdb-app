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

public class ListActivity extends AppCompatActivity {
    String baseUrl = "http://www.omdbapi.com/?apikey=20c154f9&s=";
    RequestQueue requestQueue;
    EditText etUserInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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
                System.out.println(response);
                Toast.makeText(ListActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

}
