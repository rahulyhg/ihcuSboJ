package pivotalsoft.com.JobSuchi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pivotalsoft.com.JobSuchi.Adapters.CollegeListAdpter;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.CollegeItem;
import pivotalsoft.com.JobSuchi.Response.CourseSpinnerItem;
import pivotalsoft.com.JobSuchi.Response.SpeclizationSpinnerItem;

public class CollegesActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    Gson gson;

    private List<CollegeItem> collegeItemList = new ArrayList<>();
    CourseSpinnerItem courseSpinnerItem = new CourseSpinnerItem();
    SpeclizationSpinnerItem speclizationSpinnerItem = new SpeclizationSpinnerItem();

    private RecyclerView recyclerCollege;

    private CollegeListAdpter adapter;

    //An ArrayList for Spinner Items
    private ArrayList<String> courseList = new ArrayList<>();

    //An ArrayList for Spinner Items
    private ArrayList<String> speclizationList = new ArrayList<>();

    //JSON Array
    private JSONArray result;

    EditText etLocation;
    private final int REQUEST_CODE_PLACEPICKER = 3;

    String location, course, speclization;
    String latitude, longitude;
    Spinner spincourse, spinSpeclization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleges);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Search Colleges");

        //An ArrayList for Spinner Items
        courseList = new ArrayList<>();

        //An ArrayList for Spinner Items
        speclizationList = new ArrayList<>();

        etLocation = (EditText) findViewById(R.id.etLocation);
        etLocation.setOnClickListener(this);

        spincourse = (Spinner) findViewById(R.id.spinner1);
        spinSpeclization = (Spinner) findViewById(R.id.spinner2);


        recyclerCollege = (RecyclerView) findViewById(R.id.recyclerCollege);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCollege.setLayoutManager(mLayoutManager1);
        recyclerCollege.setItemAnimator(new DefaultItemAnimator());


        courseSpinnerData();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.etLocation:
                startPlacePickerActivity();
                break;


        }

    }

    // google place picker
    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        // this would only work if you have your Google Places API working

        try {
            Intent intent = intentBuilder.build(CollegesActivity.this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, CollegesActivity.this);
        Log.e("placeSelected", "" + placeSelected);

        String name = placeSelected.getName().toString();

        // places latitude
        LatLng qLoc = placeSelected.getLatLng();
        Double lat = qLoc.latitude;
        Log.e("lat", "Place: " + lat);
        latitude = String.valueOf(lat);

        // places longitude
        Double lon = qLoc.longitude;
        Log.e("lon", "Place: " + lon);
        longitude = String.valueOf(lon);


        Geocoder gcd = new Geocoder(CollegesActivity.this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = gcd.getFromLocation(lat, lon, 1);
            if (addresses.size() > 0)

            {
                String address = addresses.get(0).getAddressLine(0);
                location = addresses.get(0).getLocality().toString();
                String subLocality = addresses.get(0).getSubLocality().toString();
                String AddressLine = addresses.get(0).getAddressLine(0).toString();
                //locTextView.setText(cityname);
                if (location != null) {
                    etLocation.setText(location);
                } else {
                    Toast.makeText(CollegesActivity.this, "No data Found for this ... Please Choose Another City", Toast.LENGTH_SHORT).show();
                }
                // }
                Log.e("locality", "" + location);
                Log.e("SubLocality", "" + subLocality);
                Log.e("AddressLine", "" + AddressLine);

                searchCollegeData();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }


      /*  TextView enterCurrentLocation = (TextView) findViewById(R.id.textView9);
        enterCurrentLocation.setText(name + ", " + address);*/
    }

    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CODE_PLACEPICKER) {
                displaySelectedPlaceFromPlacePicker(data);
            }
        }

    }




    // response from gson lib
    private void searchCollegeData() {

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ALL_COLLEGES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE : ",""+response);

                        hidePDialog();

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            collegeItemList.clear();
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("alldata");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject jsonObject  = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                String collegeid = jsonObject.getString("collegeid");
                                String collegecode = jsonObject.getString("collegecode");
                                String collegename = jsonObject.getString("collegename");
                                String address = jsonObject.getString("address");
                                String district = jsonObject.getString("district");
                                String state = jsonObject.getString("state");
                                String latitude2 = jsonObject.getString("latitude");
                                String longitude2 = jsonObject.getString("longitude");
                                String gender = jsonObject.getString("gender");
                                String affiliatedto = jsonObject.getString("affiliatedto");
                                String region = jsonObject.getString("region");
                                String others = jsonObject.getString("others");
                                String startedyear = jsonObject.getString("startedyear");
                                String collegepic = jsonObject.getString("collegepic");
                                String telephone = jsonObject.getString("telephone");
                                String email = jsonObject.getString("email");
                                String website = jsonObject.getString("website");




                                   Double lat2=Double.parseDouble(latitude2);
                                   Double lon2=Double.parseDouble(longitude2);
                                   Double lat1 = Double.parseDouble(latitude);
                                   Double lon1 = Double.parseDouble(longitude);
                                   Double aDouble =60.0;

                                    distance(lat1,lon1,lat2,lon2);

                                    if (distance(lat1,lon1,lat2,lon2)<aDouble){

                                        collegeItemList.add(new CollegeItem(collegeid,collegecode,collegename,address,district,state,latitude,longitude,gender,affiliatedto,region,others,startedyear,collegepic,telephone,email,website));

                                    }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (getApplicationContext()!=null){
                            adapter = new CollegeListAdpter(CollegesActivity.this, collegeItemList);
                            recyclerCollege.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RESPONSE_ERROR: ",""+error);
                        //hideDialog();
                        // Toast.makeText(AddAddsActivity.this,"Email Already Exist",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("course",course);
                params.put("specialization",speclization);

                Log.e("RESPONSE_Parasms: ",""+params);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(CollegesActivity.this);
        requestQueue.add(stringRequest);

    }


    // response from gson lib
    private void courseSpinnerData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.COURSE_SPINNER_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                gson = new Gson();
                courseSpinnerItem = gson.fromJson(response, CourseSpinnerItem.class);

                courseList.clear();

                for (int i = 0; i < courseSpinnerItem.getAlldata().size(); i++) {

                    String coursename = courseSpinnerItem.getAlldata().get(i).getCoursename();
                    Log.e("coursename", "" + coursename);
                    courseList.add(coursename);
                }

                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(CollegesActivity.this, android.R.layout.simple_spinner_item, courseList);
                // Drop down layout style - list view with radio button
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spincourse.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();

                spincourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        course = parent.getItemAtPosition(position).toString();
                        Log.e("selected course Name", course);

                        String SPECLIZATION_URL = Constants.SPECLIZATION_SPINNER_URL + course.replace(" ", "%20").trim();
                        speclizationSpinnerData(SPECLIZATION_URL);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CollegesActivity.this);
        requestQueue.add(stringRequest);

    }

    // response from gson lib
    private void speclizationSpinnerData(String SPECLIZATION_URL) {

        Log.e("SPECLIZATION_URL121233", "" + SPECLIZATION_URL);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, SPECLIZATION_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                gson = new Gson();
                speclizationSpinnerItem = gson.fromJson(response, SpeclizationSpinnerItem.class);

                speclizationList.clear();

                for (int i = 0; i < speclizationSpinnerItem.getAlldata().size(); i++) {

                    String specialization = speclizationSpinnerItem.getAlldata().get(i).getSpecialization();
                    Log.e("specialization", "" + specialization);
                    speclizationList.add(specialization);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollegesActivity.this, android.R.layout.simple_spinner_item, speclizationList);
                // Drop down layout style - list view with radio button
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spinSpeclization.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                spinSpeclization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        speclization = parent.getItemAtPosition(position).toString();
                        Log.e("speclization", "" + speclization);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CollegesActivity.this);
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


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        Log.e("Dist",""+dist);
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
