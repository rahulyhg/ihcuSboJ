package pivotalsoft.com.JobSuchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import pivotalsoft.com.JobSuchi.Adapters.CollegeinfoAdapter;
import pivotalsoft.com.JobSuchi.Adapters.CourseDetailsAdpter;
import pivotalsoft.com.JobSuchi.Adapters.RankAdapter1;
import pivotalsoft.com.JobSuchi.Adapters.RankAdapter2;
import pivotalsoft.com.JobSuchi.Adapters.RankAdapter3;
import pivotalsoft.com.JobSuchi.Adapters.RatingAdapter;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.CollegeInfoItem;
import pivotalsoft.com.JobSuchi.Response.CollegeRatingsItem;
import pivotalsoft.com.JobSuchi.Response.CollegeotehrinfoItem;
import pivotalsoft.com.JobSuchi.Response.CourseDetailsItem;
import pivotalsoft.com.JobSuchi.Response.RankItem;

public class CollegeDetailsActivity extends AppCompatActivity {
    Gson gson;
    private RecyclerView recyclerCourseDetails,recyclerRank1,recyclerRank2,recyclerrank3,recyclerCollegeInfo,recyclerViewratings;
    CourseDetailsItem courseDetailsItem= new CourseDetailsItem();
    RankItem rankItem= new RankItem();
    CollegeotehrinfoItem collegeotehrinfoItem= new CollegeotehrinfoItem();
    CollegeInfoItem collegeinfoItem= new CollegeInfoItem();
    CollegeRatingsItem collegeRatingsItem =new CollegeRatingsItem();

    private RatingAdapter ratingAdapter;
    private CourseDetailsAdpter adapter;
    private CollegeinfoAdapter collegeinfoAdapter;
    private RankAdapter1 rankAdapter1;
    private RankAdapter2 rankAdapter2;
    private RankAdapter3 rankAdapter3;
TextView txtCollegeName,txtAffilatedBy,txtEstablishedYear,txtCollegeGender,txtCollegeCode,txtMiniroity,txtCollegeRegion,
        txtAddress,txtTelephone,txtEmail,txtWebsite;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("College Details");

        String collegeid =getIntent().getStringExtra("collegeid");

        imageView=(ImageView)findViewById(R.id.collegeImage);
        txtCollegeName=(TextView)findViewById(R.id.txtCollegeName);
        txtAffilatedBy=(TextView)findViewById(R.id.txtAffilatedBy);
        txtEstablishedYear=(TextView)findViewById(R.id.txtEstablishedYear);
        txtCollegeGender=(TextView)findViewById(R.id.txtCollegeGender);
        txtCollegeCode=(TextView)findViewById(R.id.txtCollegeCode);
        txtMiniroity=(TextView)findViewById(R.id.txtMiniroity);
        txtCollegeRegion=(TextView)findViewById(R.id.txtCollegeRegion);
        txtAddress=(TextView)findViewById(R.id.txtAddress);
        txtTelephone=(TextView)findViewById(R.id.txtTelephone);
        txtEmail=(TextView)findViewById(R.id.txtEmail);
        txtWebsite=(TextView)findViewById(R.id.txtWebsite);



        recyclerCourseDetails=(RecyclerView)findViewById(R.id.recyclerCourseDetails);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCourseDetails.setLayoutManager(mLayoutManager);
        recyclerCourseDetails.setItemAnimator(new DefaultItemAnimator());

        recyclerRank1=(RecyclerView)findViewById(R.id.recyclerRank1);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerRank1.setLayoutManager(mLayoutManager1);
        recyclerRank1.setItemAnimator(new DefaultItemAnimator());

        recyclerRank2=(RecyclerView)findViewById(R.id.recyclerRank2);
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerRank2.setLayoutManager(mLayoutManager2);
        recyclerRank2.setItemAnimator(new DefaultItemAnimator());

        recyclerrank3=(RecyclerView)findViewById(R.id.recyclerrank3);
        LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerrank3.setLayoutManager(mLayoutManager3);
        recyclerrank3.setItemAnimator(new DefaultItemAnimator());

        recyclerCollegeInfo=(RecyclerView)findViewById(R.id.recyclerCollegeInfo);
        LinearLayoutManager mLayoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCollegeInfo.setLayoutManager(mLayoutManager4);
        recyclerCollegeInfo.setItemAnimator(new DefaultItemAnimator());

        recyclerViewratings=(RecyclerView)findViewById(R.id.recyclerRatings);
        LinearLayoutManager mLayoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewratings.setLayoutManager(mLayoutManager5);
        recyclerViewratings.setItemAnimator(new DefaultItemAnimator());


        prepareCourseData(collegeid);
        prepareRankData(collegeid);
        prepareCollegeOtherInfoData(collegeid);
        prepareCollegeInfoData(collegeid);
        prepareRatingsData(collegeid);

    }

    // response from gson lib
    private void prepareCollegeInfoData(String collegeid){

       /* pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.COLLEGE_INFO_URL+collegeid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //hidePDialog();
                Log.e("RESPONSE",""+response);
                gson = new Gson();
                collegeinfoItem = gson.fromJson(response,CollegeInfoItem.class);

                for (int i = 0; i < collegeinfoItem.getAlldata().size(); i++) {


                    String collegecode =collegeinfoItem.getAlldata().get(i).getCollegecode();
                    String collegename =collegeinfoItem.getAlldata().get(i).getCollegename();
                    String address =collegeinfoItem.getAlldata().get(i).getAddress();
                    String district =collegeinfoItem.getAlldata().get(i).getDistrict();
                    String state =collegeinfoItem.getAlldata().get(i).getState();
                    String gender =collegeinfoItem.getAlldata().get(i).getGender();
                    String affiliatedto =collegeinfoItem.getAlldata().get(i).getAffiliatedto();
                    String region =collegeinfoItem.getAlldata().get(i).getRegion();
                    String startedyear =collegeinfoItem.getAlldata().get(i).getStartedyear();
                    String collegepic =Constants.IMAGE_COLLEGE_URL+collegeinfoItem.getAlldata().get(i).getCollegepic();
                    String telephone =collegeinfoItem.getAlldata().get(i).getTelephone();
                    String email =collegeinfoItem.getAlldata().get(i).getEmail();
                    String website =collegeinfoItem.getAlldata().get(i).getWebsite();

                    txtCollegeName.setText(collegename);
                    txtAffilatedBy.setText(affiliatedto);
                    txtEstablishedYear.setText(startedyear);
                    txtCollegeGender.setText(gender);
                    txtCollegeCode.setText(collegecode);
                    txtMiniroity.setText("");
                    txtCollegeRegion.setText(region);
                    txtAddress.setText(address+","+district+","+state);
                    txtTelephone.setText(telephone);
                    txtEmail.setText(email);
                    txtWebsite.setText(website);

                    Log.e("IMAGE",""+collegepic);
                    try {
                        Glide.with(CollegeDetailsActivity.this).load(collegepic).into(imageView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CollegeDetailsActivity.this);
        requestQueue.add(stringRequest);

    }

    // response from gson lib
    private void prepareCollegeOtherInfoData(String collegeid){

       /* pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.COLLEGE_OTHER_INFO_URL+collegeid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //hidePDialog();
                Log.e("RESPONSE",""+response);
                gson = new Gson();
                collegeotehrinfoItem = gson.fromJson(response,CollegeotehrinfoItem.class);
                collegeinfoAdapter = new CollegeinfoAdapter(CollegeDetailsActivity.this, collegeotehrinfoItem.getAlldata());
                recyclerCollegeInfo.setAdapter(collegeinfoAdapter);
                collegeinfoAdapter.notifyDataSetChanged();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CollegeDetailsActivity.this);
        requestQueue.add(stringRequest);

    }

    // response from gson lib
    private void prepareCourseData(String collegeid){

       /* pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.ALL_COURSE_DETAILS_URL+collegeid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //hidePDialog();
Log.e("RESPONSE",""+response);
                gson = new Gson();
                courseDetailsItem = gson.fromJson(response,CourseDetailsItem.class);
                adapter = new CourseDetailsAdpter(CollegeDetailsActivity.this, courseDetailsItem.getAlldata());
                recyclerCourseDetails.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CollegeDetailsActivity.this);
        requestQueue.add(stringRequest);

    }

    // response from gson lib
    private void prepareRankData(String collegeid){

       /* pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.ALL_RANK_URL+collegeid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //hidePDialog();
                Log.e("RESPONSE",""+response);
                gson = new Gson();
                rankItem = gson.fromJson(response,RankItem.class);
                rankAdapter1 = new RankAdapter1(CollegeDetailsActivity.this, rankItem.getAlldata());
                rankAdapter2 = new RankAdapter2(CollegeDetailsActivity.this, rankItem.getAlldata());
                rankAdapter3 = new RankAdapter3(CollegeDetailsActivity.this, rankItem.getAlldata());
                recyclerRank1.setAdapter(rankAdapter1);
                recyclerRank2.setAdapter(rankAdapter2);
                recyclerrank3.setAdapter(rankAdapter3);

                rankAdapter1.notifyDataSetChanged();
                rankAdapter2.notifyDataSetChanged();
                rankAdapter3.notifyDataSetChanged();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CollegeDetailsActivity.this);
        requestQueue.add(stringRequest);

    }

    private void prepareRatingsData(String collegeid){

       /* pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.COLLEGE_RATINGS_URL+collegeid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //hidePDialog();
                Log.e("RESPONSE",""+response);
                gson = new Gson();
                collegeRatingsItem = gson.fromJson(response,CollegeRatingsItem.class);
                ratingAdapter = new RatingAdapter(CollegeDetailsActivity.this, collegeRatingsItem.getAlldata());
                recyclerViewratings.setAdapter(ratingAdapter);
                ratingAdapter.notifyDataSetChanged();


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CollegeDetailsActivity.this);
        requestQueue.add(stringRequest);

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
