package pivotalsoft.com.JobSuchi;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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

import pivotalsoft.com.JobSuchi.Adapters.MyTestAdpter;
import pivotalsoft.com.JobSuchi.Adapters.TestAdapter;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.MyTestsItem;
import pivotalsoft.com.JobSuchi.Response.TestItem;

public class MyTestsActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
private RecyclerView recyclerView;
private MyTestAdpter adpter;
MyTestsItem myTestsItem =new MyTestsItem();
Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tests);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("My Tests");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String userid =pref.getString("userid",null);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerTests);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        prepareTestData(userid);
    }

    // response from gson lib
    private void prepareTestData(String userid){

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.STUDENT_TESTS_URL+userid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                hidePDialog();

                gson = new Gson();
                myTestsItem = gson.fromJson(response,MyTestsItem.class);
                adpter = new MyTestAdpter(MyTestsActivity.this, myTestsItem.getOnedata());
                recyclerView.setAdapter(adpter);

                //dynamic adapter
                // adapter.registerDataSetObserver(indicator.getDataSetObserver());
                adpter.notifyDataSetChanged();


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MyTestsActivity.this);
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
