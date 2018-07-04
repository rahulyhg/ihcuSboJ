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

import pivotalsoft.com.JobSuchi.Adapters.TestAdapter;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.TestItem;

public class TestActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    TestItem testItem=new TestItem();
    Gson gson;
    private RecyclerView recyclerTest;
    // buyer
    private TestAdapter adapter;

    private String subcategoryid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Test");



        subcategoryid =getIntent().getStringExtra("subcategoryid");

        recyclerTest = (RecyclerView) findViewById(R.id.testRecycler);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerTest.setLayoutManager(mLayoutManager1);
        recyclerTest.setItemAnimator(new DefaultItemAnimator());

        prepareTestData();
    }

    // response from gson lib
    private void prepareTestData(){

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.TEST_URL+subcategoryid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                hidePDialog();

                gson = new Gson();
                testItem = gson.fromJson(response,TestItem.class);
                  adapter = new TestAdapter(TestActivity.this, testItem.getAlldata());
                    recyclerTest.setAdapter(adapter);

                    //dynamic adapter
                   // adapter.registerDataSetObserver(indicator.getDataSetObserver());
                    adapter.notifyDataSetChanged();


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(TestActivity.this);
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
