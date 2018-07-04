package pivotalsoft.com.JobSuchi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import pivotalsoft.com.JobSuchi.Adapters.NewsAdapter;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.NewsItem;

public class NewsListActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private RecyclerView recyclerNews;
    NewsItem newsItemList= new NewsItem();
    Gson gson;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("News");


       // newsItemList=new ArrayList<>();

        recyclerNews=(RecyclerView)findViewById(R.id.recyclerNews);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerNews.setLayoutManager(mLayoutManager1);
        recyclerNews.setItemAnimator(new DefaultItemAnimator());

       prepareTestData();
    }

    // response from gson lib
    private void prepareTestData(){

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.NEWS_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                hidePDialog();

                gson = new Gson();
                newsItemList = gson.fromJson(response,NewsItem.class);
                adapter = new NewsAdapter(NewsListActivity.this, newsItemList.getNewsdata());
                recyclerNews.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(NewsListActivity.this);
        requestQueue.add(stringRequest);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                onBackPressed();
                break;

        }
        return true;
    }
}
