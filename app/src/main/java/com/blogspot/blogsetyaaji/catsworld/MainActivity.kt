package com.blogspot.blogsetyaaji.catsworld;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView lvcat;
    ArrayList<HashMap<String, String>> arrayCats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvcat = findViewById(R.id.rv_cat);
        lvcat.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        arrayCats = new ArrayList<>();

        getCats();
    }

    private void getCats() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String URL = "https://api.thecatapi.com/v1/breeds";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                if (response != null) {
                    Log.d("hasil", "onResponse: " + response.toString());
                    for (int a = 0; a < response.length(); a++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(a);
                            HashMap<String, String> map = new HashMap<>();
                            map.put("nama", jsonObject.getString("name"));
                            map.put("asal", jsonObject.getString("origin"));
                            arrayCats.add(map);

                            CatAdapter catAdapter = new CatAdapter(MainActivity.this, arrayCats);
                            lvcat.setAdapter(catAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("hasil", "onErrorResponse: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Server error, try again", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
}