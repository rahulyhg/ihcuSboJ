package pivotalsoft.com.JobSuchi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import pivotalsoft.com.JobSuchi.Adapters.MyTestAdpter;
import pivotalsoft.com.JobSuchi.Adapters.SectionScoreAdapter;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.MyTestsItem;
import pivotalsoft.com.JobSuchi.Response.ScoreItem;
import pivotalsoft.com.JobSuchi.Response.StudentDetailsItem;

public class ScoreActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private SectionScoreAdapter adpter;
    StudentDetailsItem myTestsItem =new StudentDetailsItem();
    ScoreItem scoreItem = new ScoreItem();
    Gson gson;

    TextView txtRight, txtWrong, txtTotalScore, txtPercentage,txtright, txtwrong, txttotalScore, txtpercentage,txtRank;
    //txtTestName, txtTotalQuestions, txttotalMarks , txtNegativeMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getSupportActionBar().setTitle("Score");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String userid =pref.getString("userid",null);


        txtRight = (TextView) findViewById(R.id.txtCorrectAns);
        txtWrong = (TextView) findViewById(R.id.txtWronans);
        txtTotalScore = (TextView) findViewById(R.id.txtTotalScore);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);

        txtright = (TextView) findViewById(R.id.txtRight);
        txtwrong = (TextView) findViewById(R.id.txtWrong);
        txttotalScore = (TextView) findViewById(R.id.txtScore);
        txtpercentage = (TextView) findViewById(R.id.txtTotalPercentage);
        txtRank = (TextView) findViewById(R.id.txtRank);

        // receive the score from last activity by Intent
        String testid = getIntent().getStringExtra("testid");
        Log.e("TESTID",""+testid);
        String studenttestid = getIntent().getStringExtra("studenttestid");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerScore);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        prepareTestData(testid);
        prepareScoreData(studenttestid);
    }

    private void prepareScoreData(String studenttestid){



        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.STUDENT_SCORE_DETAILS_URL+studenttestid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //hidePDialog();

                gson = new Gson();
                scoreItem = gson.fromJson(response,ScoreItem.class);

                for (ScoreItem.OnedataBean scoreItem1: scoreItem.getOnedata()){

                    String percentage=scoreItem1.getPercentage().substring(0,2).toUpperCase();
                    String obtainedscore= scoreItem1.getObtainedscore().substring(0,3).toUpperCase();
                    String totalscore= scoreItem1.getTotalscore().substring(0,2).toUpperCase();
                    Log.e("totalscore",""+totalscore);
                    txtRight.setText(obtainedscore+"/"+totalscore);
                    txtright.setText(obtainedscore+"/"+totalscore);

                    Double wrong =Double.parseDouble(totalscore) - Double.parseDouble(obtainedscore);

                    txtWrong.setText(String.valueOf(wrong));
                    txtwrong.setText(String.valueOf(wrong));

                    txtTotalScore.setText(totalscore);
                    txttotalScore.setText(totalscore);

                    txtPercentage.setText(percentage+"%");
                    txtpercentage.setText(percentage+"%");

                    txtRank.setText(scoreItem1.getRank());
                }




            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ScoreActivity.this);
        requestQueue.add(stringRequest);

    }

    // response from gson lib
    private void prepareTestData(final String testid){

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.STUDENT_TEST_DETAILS_URL+testid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                hidePDialog();

                Log.e("URL",""+Constants.STUDENT_TEST_DETAILS_URL+testid);
                gson = new Gson();
                myTestsItem = gson.fromJson(response,StudentDetailsItem.class);
                adpter = new SectionScoreAdapter(ScoreActivity.this, myTestsItem.getOnedata());
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
        RequestQueue requestQueue = Volley.newRequestQueue(ScoreActivity.this);
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

    public void onClick(View view) {
        Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ScoreActivity.this);
        } else {
            builder = new AlertDialog.Builder(ScoreActivity.this);
        }
        builder.setTitle("Confirm ! ")
                .setMessage("Do you want to start the test again ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        // moveTaskToBack(true);
                        Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing

                    }
                })
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}




