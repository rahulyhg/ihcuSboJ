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

import pivotalsoft.com.JobSuchi.Adapters.SubCategoryAdapter;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.SubCategoryItem;

public class SubCategoryActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    SubCategoryItem subCategoryItem=new SubCategoryItem();
    Gson gson;
    private RecyclerView recyclerSubcategory;
    private SubCategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Sub Category");

        recyclerSubcategory = (RecyclerView) findViewById(R.id.recyclerSubcategory);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerSubcategory.setLayoutManager(mLayoutManager1);
        recyclerSubcategory.setItemAnimator(new DefaultItemAnimator());

        prepareTestData();
    }

    // response from gson lib
    private void prepareTestData(){

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.ALL_SUB_CATEGOERY_URL+"1", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                hidePDialog();

                gson = new Gson();
                subCategoryItem = gson.fromJson(response,SubCategoryItem.class);

                // mPager.setAdapter(adapter);

                adapter = new SubCategoryAdapter(SubCategoryActivity.this, subCategoryItem.getAlldata());
                recyclerSubcategory.setAdapter(adapter);
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
        RequestQueue requestQueue = Volley.newRequestQueue(SubCategoryActivity.this);
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
