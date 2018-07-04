package pivotalsoft.com.JobSuchi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import pivotalsoft.com.JobSuchi.Constants.Constants;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private final int REQUEST_CODE_PLACEPICKER = 1;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    final int QUALIFICATION_ALERTDIALOG = 1;
    CharSequence[] qualificationList ={"Graduation and above","10+2 Pass","Diploma","Other than Mentioned"};
    String lattitude,longitude;
    EditText etFullName,etMobile,etEmail,etPassword,etAddress,etQualification;
    String fullName,mobile,email,password,address,qualification,currentDate;
    int otpCode;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDate = df.format(c.getTime());
        Log.e("CurrentDate",""+currentDate);

        etFullName=(EditText)findViewById(R.id.etFullName);
        etMobile=(EditText)findViewById(R.id.etMobile);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);

        etAddress=(EditText)findViewById(R.id.etAddress);
        etAddress.setOnClickListener(this);
        etQualification=(EditText)findViewById(R.id.etQualification);
        etQualification.setOnClickListener(this);

        btnSave=(Button)findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);

        // check self permissions
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            /*case R.id.coverImage:

                showFileChooser1(PICK_IMAGE_REQUEST1);

                break;*/

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

    // get location
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(RegisterActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (RegisterActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                Geocoder gcd=new Geocoder(RegisterActivity.this, Locale.ENGLISH);
                List<Address> addresses;

                try {
                    addresses=gcd.getFromLocation(latti,longi,1);
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
                            Toast.makeText(RegisterActivity.this,"No data Found for this ... Please Choose Another City",Toast.LENGTH_SHORT).show();
                        }
                        // }
                        Log.e("locality",""+locality);
                        Log.e("SubLocality",""+subLocality);
                        Log.e("AddressLine",""+AddressLine);
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }



            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                Geocoder gcd=new Geocoder(RegisterActivity.this, Locale.ENGLISH);
                List<Address> addresses;

                try {
                    addresses=gcd.getFromLocation(latti,longi,1);
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
                            Toast.makeText(RegisterActivity.this,"No data Found for this ... Please Choose Another City",Toast.LENGTH_SHORT).show();
                        }
                        // }
                        Log.e("locality",""+locality);
                        Log.e("SubLocality",""+subLocality);
                        Log.e("AddressLine",""+AddressLine);
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }



            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                Geocoder gcd=new Geocoder(RegisterActivity.this, Locale.ENGLISH);
                List<Address> addresses;

                try {
                    addresses=gcd.getFromLocation(latti,longi,1);
                    if(addresses.size()>0)

                    {
                        String address = addresses.get(0).getAddressLine(0);
                        String locality = addresses.get(0).getLocality().toString();
                        String subLocality = addresses.get(0).getSubLocality().toString();
                        String AddressLine = addresses.get(0).getAddressLine(0).toString();
                        //locTextView.setText(cityname);

                        if (locality != null) {
                            etAddress.setText(locality);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"No data Found for this ... Please Choose Another City",Toast.LENGTH_SHORT).show();
                        }
                        // }
                        Log.e("locality",""+locality);
                        Log.e("SubLocality",""+subLocality);
                        Log.e("AddressLine",""+AddressLine);
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }


            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
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
        //latitude = String.valueOf(lat);

        // places longitude
        Double lon =qLoc.longitude;
        Log.e("lon", "Place: " + lon);
        //longitude =String.valueOf(lon);

        Geocoder gcd=new Geocoder(RegisterActivity.this, Locale.ENGLISH);
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
                    Toast.makeText(RegisterActivity.this,"No data Found for this ... Please Choose Another City",Toast.LENGTH_SHORT).show();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this)
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

// random no generater
        Random r = new Random();
        otpCode =r.nextInt(900000 - 750000) + 75;

        if (!fullName.isEmpty() && !address.isEmpty() && !email.isEmpty()  && !mobile.isEmpty() && !qualification.isEmpty() && !password.isEmpty()) {

            //adddata();

            prepareOtpData(fullName,mobile,otpCode);

            Intent intent =new Intent(RegisterActivity.this,OtpVerificationActivity.class);
            intent.putExtra("fullname",fullName);
            intent.putExtra("email",email);
            intent.putExtra("mobile",mobile);
            intent.putExtra("password",password);
            intent.putExtra("address",address);
            intent.putExtra("otpCode",otpCode);
            intent.putExtra("qualification",qualification);
            intent.putExtra("currentDate",currentDate);

            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
        }
    }

    private void prepareOtpData(String fullname,String mobile,int otpCode) {

        String message ="Hello "+fullname+", Thanks for choosing Pivotalsoft. Your OTP is: "+String.valueOf(otpCode);

        String urlencode=null;
        try {
            urlencode =  URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final String otpUrl =Constants.OTP_BASE_URL+mobile+Constants.SENDERID_URL+urlencode;

        Log.e("MSG",""+otpUrl);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                otpUrl, null, new Response.Listener<JSONObject>() {



            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());


                //OtpalertDilogue();

                Log.e("URL",""+response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //Toast.makeText(RegisterActivity.this, "no data Found. check once data is enable or not..", Toast.LENGTH_LONG).show();
                // hide the progress dialog

                // OtpalertDilogue();

                Log.e("URl",""+otpUrl);

            }
        });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);

        //Adding request to the queue
        requestQueue.add(jsonObjReq);

    }


    private void adddata(){

        pDialog.setMessage("Loading ...");
        showDialog();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.RIGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE : ",""+response);
                        hideDialog();

                        Intent pivotal = new Intent(RegisterActivity.this, LoginActivity.class);
                        pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(pivotal);
                        Toast.makeText(RegisterActivity.this,"Registration Successfully",Toast.LENGTH_LONG).show();
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

}
