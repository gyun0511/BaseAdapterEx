package com.demo.android.baseadapterex;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> mylist1 = new ArrayList<>();
    MyAdapter adapter;
    Zoo z;
    ArrayList<String> mylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);


        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest("http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        z = gson.fromJson(response, Zoo.class);

                        Log.d("TEST", z.result.results[1].E_Name);
                        Log.d("TEST", z.result.results[0].E_Pic_URL);
                        Log.d("TEST", "Length :"+ z.result.results.length);
                        for (int i=0;i<z.result.results.length;i++)
                        {
                            Log.d("TEST", "ADD :"+ z.result.results[i].E_Pic_URL);
                            mylist.add(z.result.results[i].E_Pic_URL);
                            mylist1.add(z.result.results[i].E_Name);
                        }

                        adapter = new MyAdapter(MainActivity.this, mylist1,mylist);
                        Log.d("TEST","Size"+mylist.size());
                        listView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
        queue.start();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        StringBuilder sb = new StringBuilder();
        if (item.getItemId() == R.id.menu_check) {

//
//            int i;
//            for (i = 0; i < mylist1.size(); i++) {
//                if (adapter.check1[i]) {
//                    sb.append(names[i] + ",");
//                }
//            }
        }
        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


}
