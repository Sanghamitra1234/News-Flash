package com.example.newsflash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.newsflash.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<News> newsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        mAdapter=new NewsAdapter(newsList);

        recyclerView.setAdapter(mAdapter);
        fetchData();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                mAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

    public  void fetchData(){
        //RequestQueue queue = Volley.newRequestQueue(this);
        Log.v("responsePost","inside fetch");
        String url ="https://newsapi.org/v2/top-headlines?country=in&apiKey=9be82774c8174ffeb211146163f2ee55";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("articles");

                            for(int index=0;index<jsonArray.length();index++) {
                                String sourceName=jsonArray.getJSONObject(index).getJSONObject("source").getString("name");
                                String title=jsonArray.getJSONObject(index).getString("title");
                                String url=jsonArray.getJSONObject(index).getString("url");

                                //Date
                                String publishedDate=jsonArray.getJSONObject(index).getString("publishedAt");
                                newsList.add(new News(sourceName,title,url,publishedDate));
                             }
                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

//                    private String getDateString(JSONObject jsonObject) throws JSONException, ParseException {
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                        String d=jsonObject.getString("publishedAt");
//                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(d);
//                        Date d2 = new Date();
//                        Date d1 = sdf.parse(date.toString());
//                        long difference_In_Time = d2.getTime() - d1.getTime();
//                        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
//                        return difference_In_Hours+"hours ago";
//                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
       MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}