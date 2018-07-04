package pivotalsoft.com.JobSuchi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

import pivotalsoft.com.JobSuchi.Constants.Constants;

public class OtpVerificationActivity extends Activity {
    private static final String TAG = OtpVerificationActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    String fullname, email, mobile, password, address, qualification, currentDateandTime,currentDate, referralCode;
    int myReferralcode,otpCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        fullname=getIntent().getStringExtra("fullname");
        email=getIntent().getStringExtra("email");
        mobile=getIntent().getStringExtra("mobile");
        password=getIntent().getStringExtra("password");
        address=getIntent().getStringExtra("address");
        qualification=getIntent().getStringExtra("qualification");
        currentDate=getIntent().getStringExtra("currentDate");

        otpCode=getIntent().getIntExtra("otpCode",0);
        Log.e("OTP",""+otpCode);


        final EditText etOtp =(EditText)findViewById(R.id.etOtp);
        Button dialogButton = (Button) findViewById(R.id.btnsubmit);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp =etOtp.getText().toString();

                if (otp.equalsIgnoreCase(String.valueOf(otpCode))){

                    adddata();

                }
                else {
                    etOtp.setError("Invalid otp");
                }


                /*Intent pivotal = new Intent(RegisterActivity.this, LoginActivity.class);
                pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(pivotal);*/
            }
        });
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

                        Intent pivotal = new Intent(OtpVerificationActivity.this, LoginActivity.class);
                        pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(pivotal);
                        Toast.makeText(OtpVerificationActivity.this,"Registration Successfully",Toast.LENGTH_LONG).show();
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
                params.put("fullname",fullname);
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
