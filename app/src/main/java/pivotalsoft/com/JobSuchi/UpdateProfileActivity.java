package pivotalsoft.com.JobSuchi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pivotalsoft.com.JobSuchi.Constants.Constants;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog pDialog;
    private final int REQUEST_CODE_PLACEPICKER = 3;
    final int QUALIFICATION_ALERTDIALOG = 1;
    CharSequence[] qualificationList ={"Graduation and above","10+2 Pass","Diploma","Other than Mentioned"};
    String latitude,longitude;
    EditText etFullName,etMobile,etEmail,etPassword,etAddress,etQualification;
    String fullName,mobile,email,password,address,qualification,currentDate,userid;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Update Profile");


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String spfullname =pref.getString("fullname",null);
        String spemail =pref.getString("email",null);
        String spmobile =pref.getString("mobile",null);
        String sppassword =pref.getString("password",null);
        String spaddress =pref.getString("address",null);
        String spqualification =pref.getString("qualification",null);
        userid =pref.getString("userid",null);

        Log.e("Password",""+sppassword);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDate = df.format(c.getTime());
        Log.e("CurrentDate",""+currentDate);

        etFullName=(EditText)findViewById(R.id.etFullName);
        etFullName.setText(spfullname);

        etMobile=(EditText)findViewById(R.id.etMobile);
        etMobile.setText(spmobile);

        etEmail=(EditText)findViewById(R.id.etEmail);
        etEmail.setText(spemail);

        etPassword=(EditText)findViewById(R.id.etPassword);
        etPassword.setText(sppassword);

        etAddress=(EditText)findViewById(R.id.etAddress);
        etAddress.setText(spaddress);
        etAddress.setOnClickListener(this);

        etQualification=(EditText)findViewById(R.id.etQualification);
        etQualification.setOnClickListener(this);
        etQualification.setText(spqualification);

        btnSave=(Button)findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.etAddress:
                startPlacePickerActivity();
                break;

            case R.id.buttonSave:
                saveDate();

                break;


            case R.id.etQualification:
                showDialog(QUALIFICATION_ALERTDIALOG);

                break;
        }
    }

    // google place picker
    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        // this would only work if you have your Google Places API working

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, this);
        Log.e("placeSelected",""+placeSelected);

        String name = placeSelected.getName().toString();

        // places latitude
        LatLng qLoc = placeSelected.getLatLng();
        Double lat =qLoc.latitude;
        Log.e("lat", "Place: " + lat);
        latitude = String.valueOf(lat);

        // places longitude
        Double lon =qLoc.longitude;
        Log.e("lon", "Place: " + lon);
        longitude =String.valueOf(lon);

        Geocoder gcd=new Geocoder(UpdateProfileActivity.this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses=gcd.getFromLocation(lat,lon,1);
            if(addresses.size()>0)

            {
                String address = addresses.get(0).getAddressLine(0);
                String locality = addresses.get(0).getLocality().toString();
                String subLocality = addresses.get(0).getSubLocality().toString();
                String AddressLine = addresses.get(0).getAddressLine(0).toString();
                //locTextView.setText(cityname);
                if (locality!=null) {
                    etAddress.setText(locality);
                }
                else {
                    Toast.makeText(UpdateProfileActivity.this,"No data Found for this ... Please Choose Another City",Toast.LENGTH_SHORT).show();
                }
                // }
                Log.e("locality",""+locality);
                Log.e("SubLocality",""+subLocality);
                Log.e("AddressLine",""+AddressLine);
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

            if (requestCode == REQUEST_CODE_PLACEPICKER){
                displaySelectedPlaceFromPlacePicker(data);
            }
        }

    }

    /*triggered by showDialog method. onCreateDialog creates a dialog*/
    @Override
    public Dialog onCreateDialog(int id) {
        switch (id) {

            case QUALIFICATION_ALERTDIALOG:

                AlertDialog.Builder builder1 = new AlertDialog.Builder(UpdateProfileActivity.this)
                        .setTitle("Job Type")
                        .setSingleChoiceItems(qualificationList, -1, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                // Toast.makeText(getApplicationContext(),"The selected" + experienceList[which], Toast.LENGTH_LONG).show();
                                etQualification.setText(qualificationList[which]);
                                //dismissing the dialog when the user makes a selection.
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertdialog1 = builder1.create();
                return alertdialog1;


        }
        return null;

    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
// TODO Auto-generated method stub

        switch (id) {


            case QUALIFICATION_ALERTDIALOG:
                AlertDialog expdialog1 = (AlertDialog) dialog;
                ListView list_exp1 = expdialog1.getListView();
                for (int i = 0; i < list_exp1.getCount(); i++) {
                    list_exp1.setItemChecked(i, false);
                }
                break;




        }
    }

    private void saveDate(){


        fullName=etFullName.getText().toString().trim();
        address =etAddress.getText().toString().trim();
        email =etEmail.getText().toString().trim();
        mobile =etMobile.getText().toString().trim();
        qualification =etQualification.getText().toString().trim();
        password =etPassword.getText().toString().trim();



        if (!fullName.isEmpty() && !address.isEmpty() && !email.isEmpty()  && !mobile.isEmpty() && !qualification.isEmpty() && !password.isEmpty()) {

            adddata();

        } else {
            Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
        }
    }


    private void adddata(){

        pDialog.setMessage("Loading ...");
        showDialog();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPDATE_RIGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE : ",""+response);
                        hideDialog();

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("email", email);  // Saving string
                        editor.putString("fullname", fullName);  // Saving string
                        editor.putString("mobile", mobile);  // Saving string
                        editor.putString("address", address);  // Saving string
                        editor.putString("qualification", qualification);  // Saving string
                        editor.putString("password", password);  // Saving string
                        // Save the changes in SharedPreferences
                        editor.commit(); // commit changes



                        Intent pivotal = new Intent(UpdateProfileActivity.this, HomeActivity.class);
                        pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(pivotal);
                        Toast.makeText(UpdateProfileActivity.this,"Update Successfully",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RESPONSE_ERROR: ",""+error);
                        hideDialog();
                        // Toast.makeText(AddAddsActivity.this,"Email Already Exist",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("userid",userid);
                params.put("fullname",fullName);
                params.put("email",email);
                params.put("mobile",mobile);
                params.put("qualification", qualification);
                params.put("address",address);
                params.put("password",password);
                params.put("postedon",currentDate);


                Log.e("RESPONSE_Parasms: ",""+params);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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


