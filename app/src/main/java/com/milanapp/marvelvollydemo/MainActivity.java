package com.milanapp.marvelvollydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.milanapp.marvelvollydemo.Adapter.MyAdapter;
import com.milanapp.marvelvollydemo.Model.Marvel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  static final String URL = "https://simplifiedcoding.net/demos/marvel";
    private RecyclerView recyclerView;
    private List<Marvel> mlist;

    private JsonArrayRequest arrayRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_Recycler);
        mlist = new ArrayList<>();

        JsonRequest();


    }

    private void JsonRequest() {

        arrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject object = null;
                for (int i =0; i<response.length(); i++){

                    try {
                        object = response.getJSONObject(i);
                        Marvel marvel = new Marvel();
                        marvel.setName(object.getString("name"));
                        marvel.setRealname(object.getString("realname"));
                        marvel.setTeam(object.getString("team"));
                        marvel.setPublisher(object.getString("publisher"));
                        marvel.setBio(object.getString("bio"));
                        marvel.setFirstappearance(object.getString("firstappearance"));

                        marvel.setImageurl(object.getString("imageurl"));
                        mlist.add(marvel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecyclerview(mlist);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "milan", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(arrayRequest);


    }

    private void setuprecyclerview(List<Marvel> mlist) {


        MyAdapter recyclerviewAdapter = new MyAdapter(this,mlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerviewAdapter );
    }
}
