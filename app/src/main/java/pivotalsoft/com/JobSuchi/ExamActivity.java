package pivotalsoft.com.JobSuchi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.AllQuestionsItem;
import pivotalsoft.com.JobSuchi.Response.Sections;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener {


    int userSelection;
    int radioSelected;
    int correctAnswerForQuestion;
    private int currentQuizQuestion=0;
    private int quizCount;

    private AllQuestionsItem firstQuestion;
    List<AllQuestionsItem> allQuestionsItem = new ArrayList<AllQuestionsItem>();
    ArrayList<String> answersStringArray = new ArrayList<String>();
    List<Sections> sectionsList =new ArrayList<>();

    HashMap<Integer, Integer> hashMap;

    Set<String> sections = new HashSet<>();

    private TextView txtQuestion, mTextViewCountDown,txtSection;
    private RadioGroup radioGroup;
    private RadioButton rbA, rbB, rbC, rbD, rbE;
    private ImageView imgQuestion;
    private String testid,userid, testname, totalquestions, negativemarks, totalmarks, duration,currentDate, studenttestid;
    private long milliSeconds;
    int score =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        // getSupportActionBar().setTitle("Test");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid =pref.getString("userid",null);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDate = df.format(c.getTime());
        Log.e("CurrentDate",""+currentDate);

        testid = getIntent().getStringExtra("testid");
        testname = getIntent().getStringExtra("testname");
        totalquestions = getIntent().getStringExtra("totalquestions");
        totalmarks = getIntent().getStringExtra("totalmarks");
        negativemarks = getIntent().getStringExtra("negativemarks");
        duration = getIntent().getStringExtra("duration");

        milliSeconds = Long.parseLong(duration) * 60000;
        hashMap = new HashMap<>();

        // textview
        txtQuestion = (TextView) findViewById(R.id.question);
        mTextViewCountDown = (TextView) findViewById(R.id.tvTimer);
        txtSection =(TextView)findViewById(R.id.txtSection);
        imgQuestion = (ImageView) findViewById(R.id.imageQuestion);

        //buttons
        Button bt1 = (Button) findViewById(R.id.btnForward);
        Button bt2 = (Button) findViewById(R.id.btnPrevious);

        //radiobuttons
        rbA = (RadioButton) findViewById(R.id.radioButtonA);
        rbB = (RadioButton) findViewById(R.id.radioButtonB);
        rbC = (RadioButton) findViewById(R.id.radioButtonC);
        rbD = (RadioButton) findViewById(R.id.radioButtonD);
        rbE = (RadioButton) findViewById(R.id.radioButtonE);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                firstQuestion = allQuestionsItem.get(currentQuizQuestion);
                Log.e("SECTION",""+firstQuestion.getSection());


                // userSelection = checkedId;
                    // userSelection = getSelectedAnswer(radioSelected);
                    View radioButton = radioGroup.findViewById(checkedId);
                    userSelection = radioGroup.indexOfChild(radioButton);
                    correctAnswerForQuestion = Integer.parseInt(firstQuestion.getRightanswer()) - 1;

                    hashMap.put(Integer.parseInt(firstQuestion.getId()), userSelection);
                    Log.e("HASMAPPUT", "" + Integer.parseInt(firstQuestion.getId()) + " " + userSelection);

                    sections.add(firstQuestion.getSection());

                    // validate answer
                    if (userSelection == correctAnswerForQuestion) {

                        allQuestionsItem.get(currentQuizQuestion).setScore(1);

                    }
                    else {

                        allQuestionsItem.get(currentQuizQuestion).setScore(0);

                    }


            }

        });


        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

        prepareTestData();
        startTimer();



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnPrevious:

                prevButton();

                break;

            case R.id.btnForward:

                nextButton();

                break;


        }

    }

    // NEXT BUTTON
    private void nextButton() {

        if (currentQuizQuestion < quizCount - 1) {
            currentQuizQuestion++;

            firstQuestion = allQuestionsItem.get(currentQuizQuestion);

          /*  for(Integer qid : hashMap.keySet()){
                Log.e("HASMAP_DATA", qid + ", " + hashMap.get(qid));
            }*/
            // create string representation
            Integer str = hashMap.get(Integer.parseInt(firstQuestion.getId()));
            Log.e("HASMAP_NEXT_DATA", "" + str);

            radioGroup.clearCheck();

            if (str != null) {
                int  rbindex = str;
                Log.e("INDEX",""+rbindex);
                ((RadioButton) radioGroup.getChildAt(rbindex)).setChecked(true);
            }/*else {

                rbA.setChecked(true);
            }*/

            // loading question
            txtQuestion.setText(firstQuestion.getQuestion());
            txtSection.setText(firstQuestion.getSection());

            // image loading to imageview
            Glide.with(ExamActivity.this).load(firstQuestion.getQuestionurl()).into((imgQuestion));
            Log.e("IMAGE", "" + firstQuestion.getQuestionurl());

            //5th radio button hide and visibulity
            if (firstQuestion.getAnswer5().equalsIgnoreCase("")) {
                rbE.setVisibility(View.GONE);
            } else {
                rbE.setVisibility(View.VISIBLE);
            }

            //uncheckedRadioButton();
            rbA.setText(firstQuestion.getAnswer1());
            rbB.setText(firstQuestion.getAnswer2());
            rbC.setText(firstQuestion.getAnswer3());
            rbD.setText(firstQuestion.getAnswer4());
            rbE.setText(firstQuestion.getAnswer5());


        }
    }

    // PREVIOUS BUTTON
    private void prevButton() {

        if (currentQuizQuestion > 0) {

            currentQuizQuestion--;

            firstQuestion = allQuestionsItem.get(currentQuizQuestion);


          /*  for(Integer qid : hashMap.keySet()){
                Log.e("HASMAP_DATA", qid + ", " + hashMap.get(qid));
            }*/

            // create string representation
            Integer str = hashMap.get(Integer.parseInt(firstQuestion.getId()));
            Log.e("HASMAP_PREV_DATA", ""+""+firstQuestion.getId() + str);



            radioGroup.clearCheck();

            if (str != null) {
                int  rbindex = str;
                Log.e("INDEX",""+rbindex);
                ((RadioButton) radioGroup.getChildAt(rbindex)).setChecked(true);
            }
            /*else {

                rbA.setChecked(true);
            }*/


            //loading question
            txtQuestion.setText(firstQuestion.getQuestion());
            txtSection.setText(firstQuestion.getSection());

            // image loading to imageview
            Glide.with(ExamActivity.this).load(firstQuestion.getQuestionurl()).into((imgQuestion));
            Log.e("IMAGE", "" + firstQuestion.getQuestionurl());


            //radio button hide and visibulity
            if (firstQuestion.getAnswer5().equalsIgnoreCase("")) {
                rbE.setVisibility(View.GONE);
            } else {
                rbE.setVisibility(View.VISIBLE);
            }

            // loading radiobuttons
            rbA.setText(firstQuestion.getAnswer1());
            rbB.setText(firstQuestion.getAnswer2());
            rbC.setText(firstQuestion.getAnswer3());
            rbD.setText(firstQuestion.getAnswer4());
            rbE.setText(firstQuestion.getAnswer5());


        }


    }

    // TIMER
    private void startTimer() {
        CountDownTimer timer = new CountDownTimer(milliSeconds, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                mTextViewCountDown.setText(String.format("%d:%d:%d", hours, minutes, seconds));
            }

            public void onFinish() {
                mTextViewCountDown.setText("Time Up");
            }
        };
        timer.start();
    }

    // LOADING DATA
    public void prepareTestData() {

        final String NEWJOB_URL = Constants.ALL_QUESTIONS_URL + testid;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                NEWJOB_URL, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", response.toString());

                Log.e("URL", "" + NEWJOB_URL);

                try {
                    // Parsing json object response
                    // response will be a json object
                    // newJobItemList.clear();

                    //Get the instance of JSONArray that contains JSONObjects
                    JSONArray jsonArray1 = response.optJSONArray("alldata");


                    //Iterate the jsonArray and print the info of JSONObjects
                    for (int i = 0; i < jsonArray1.length(); i++) {

                        JSONObject jsonChildNode = jsonArray1.getJSONObject(i);
                        String id = jsonChildNode.getString("questionid");
                        String section = jsonChildNode.getString("section");
                        String question = jsonChildNode.getString("questiontext");
                        String questionurl = Constants.IMAGE_AD_URL + jsonChildNode.getString("questionurl");
                        String answer1 = jsonChildNode.getString("answer1");
                        String answer1url = jsonChildNode.getString("answer1url");
                        String answer2 = jsonChildNode.getString("answer2");
                        String answer2url = jsonChildNode.getString("answer2url");
                        String answer3 = jsonChildNode.getString("answer3");
                        String answer3url = jsonChildNode.getString("answer3url");
                        String answer4 = jsonChildNode.getString("answer4");
                        String answer4url = jsonChildNode.getString("answer4url");
                        String answer5 = jsonChildNode.getString("answer5");
                        String answer5url = jsonChildNode.getString("answer5url");
                        String testid = jsonChildNode.getString("testid");
                        String rightanswer = jsonChildNode.getString("rightanswer");
                        allQuestionsItem.add(new AllQuestionsItem(id, section, question, questionurl, answer1, answer1url, answer2, answer2url, answer3,
                                answer3url, answer4, answer4url, answer5, answer5url, testid, rightanswer,score));

                        Log.e("scorevalue",""+score);

                    }

                    quizCount = allQuestionsItem.size();
                    firstQuestion = allQuestionsItem.get(0);
                    txtQuestion.setText(firstQuestion.getQuestion());
                    txtSection.setText(firstQuestion.getSection());

                    Glide.with(ExamActivity.this).load(firstQuestion.getQuestionurl()).into((imgQuestion));
                    Log.e("IMAGE", "" + firstQuestion.getQuestionurl());

                    //radio button hide and visibulity
                    if (firstQuestion.getAnswer5().equalsIgnoreCase("")) {
                        rbE.setVisibility(View.GONE);
                    } else {
                        rbE.setVisibility(View.VISIBLE);
                    }

                    rbA.setText(firstQuestion.getAnswer1());
                    rbB.setText(firstQuestion.getAnswer2());
                    rbC.setText(firstQuestion.getAnswer3());
                    rbD.setText(firstQuestion.getAnswer4());
                    rbE.setText(firstQuestion.getAnswer5());


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ExamActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                // Toast.makeText(OffersActivity.this, "no data Found. check once data is enable or not..", Toast.LENGTH_LONG).show();
                // hide the progress dialog

            }
        });


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(ExamActivity.this);
        requestQueue.add(jsonObjReq);


    }

    private int getSelectedAnswer(int radioSelected) {

        int answerSelected = -1;
        if (radioSelected == R.id.radioButtonA) {
            answerSelected = 0;
        }
        if (radioSelected == R.id.radioButtonB) {
            answerSelected = 1;
        }
        if (radioSelected == R.id.radioButtonC) {
            answerSelected = 2;
        }
        if (radioSelected == R.id.radioButtonD) {
            answerSelected = 3;
        }
        if (radioSelected == R.id.radioButtonE) {
            answerSelected = 4;
        }
        return answerSelected;
    }

    private void uncheckedRadioButton() {
        rbA.setChecked(false);
        rbB.setChecked(false);
        rbC.setChecked(false);
        rbD.setChecked(false);
        rbE.setChecked(false);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit) {


            final AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(ExamActivity.this);
            } else {
                builder = new AlertDialog.Builder(ExamActivity.this);
            }
            builder.setTitle("Confirm ! ")
                    .setMessage("Do you want to submit the test ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            // moveTaskToBack(true);
                            submitButton();


                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing

                        }
                    })
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .show();




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void submitButton(){


        int totalquestions=0;
        int totalscore=0;

        for (String section : sections) {
            Log.e("SSET",""+section);

            int currentQuestion=0;
            int currentScore=0;

            for (AllQuestionsItem item1 : allQuestionsItem) {

                if (section.equalsIgnoreCase(item1.getSection())) {

                    currentScore = currentScore + item1.getScore();

                    currentQuestion++;
                    totalquestions++;

                    totalscore = totalscore+item1.getScore();



                    Log.e("RESULTSCORE", "" + currentScore + "   " + totalscore);
                }
            }
            sectionsList.add(new Sections(section,currentQuestion,currentScore));

            for (Sections sections1 : sectionsList){

                Log.e("RESULTSECTION", "" + sections1.getSectionName()+" "+sections1.getTotalquestions()+" "+sections1.getTotalsocre());

                // calculate the total percentage
                String  totalPercentage = String.valueOf(sections1.getTotalsocre()/sections1.getTotalquestions() * 100);
                Log.e("TotalScore",""+totalPercentage);

                adddata(testid,userid,String.valueOf(totalscore),String.valueOf(currentScore),totalPercentage,currentDate,section);
            }



        }




    }

    private void adddata(final String testid, final String userid, final String totalscore, final String obtainedScore, final String percentage, final String currentDate,final String section){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.STUDENT_TESTS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE : ",""+response);


                        try {
                            JSONObject messageObject =new JSONObject(response.toString());
                            studenttestid =messageObject.getString("status");
                            Log.e("RESPONSE2 : ",""+studenttestid);

                            addTestDetailsdata(studenttestid,totalscore,obtainedScore,percentage,section);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // hideDialog();

                      /*  Intent pivotal = new Intent(ScoreActivity.this, LoginActivity.class);
                        pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(pivotal);*/
                        //Toast.makeText(ScoreActivity.this,"Registration Successfully",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RESPONSE_ERROR: ",""+error);
                        // hideDialog();
                        // Toast.makeText(AddAddsActivity.this,"Email Already Exist",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("testid",testid);
                params.put("userid",userid);
                params.put("totalscore",totalscore);
                params.put("obtainedscore", obtainedScore);
                params.put("percentage",percentage);
                params.put("result","Pass");
                params.put("remarks","Good");
                params.put("testtime",currentDate);
                Log.e("RESPONSE_Parasms: ",""+params);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void addTestDetailsdata(final String studenttestid,final String section, final String totalscore, final String obtainedScore, final String percentage){



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.STUDENT_TEST_DETAILS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE : ",""+response);


                        try {
                            JSONObject messageObject =new JSONObject(response.toString());
                            String status =messageObject.getString("status");
                            Log.e("RESPONSE2 : ",""+status);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Intent intent = new Intent(ExamActivity.this, ScoreActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("testid", testid);
                        intent.putExtra("studenttestid",studenttestid);
                        Log.e("STUDENT",""+studenttestid);
                        startActivity(intent);

                        // hideDialog();

                      /*  Intent pivotal = new Intent(ScoreActivity.this, LoginActivity.class);
                        pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(pivotal);*/
                        //Toast.makeText(ScoreActivity.this,"Registration Successfully",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RESPONSE_ERROR: ",""+error);
                        // hideDialog();
                        // Toast.makeText(AddAddsActivity.this,"Email Already Exist",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("studenttestid",studenttestid);
                params.put("section",section);
                params.put("totalscore",totalscore);
                params.put("obtainedscore", obtainedScore);
                params.put("percentage",percentage);
                params.put("remarks","Good");
                Log.e("RESPONSE_Parasms: ",""+params);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ExamActivity.this);
        } else {
            builder = new AlertDialog.Builder(ExamActivity.this);
        }
        builder.setTitle("Confirm ! ")
                .setMessage("Do you want to start the test again ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        // moveTaskToBack(true);
                        Intent intent = new Intent(ExamActivity.this, SubCategoryActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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