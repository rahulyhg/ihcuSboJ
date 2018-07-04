package pivotalsoft.com.JobSuchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart;
    TextView txtTestName,txtQuestions,txtmarks,txtNegativeMarks,txtDuration;
    String testid,testname,totalquestions,negativemarks,totalmarks,duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Test Details");

         testid =getIntent().getStringExtra("testid");
         testname =getIntent().getStringExtra("testname");
         totalquestions =getIntent().getStringExtra("totalquestions");
         totalmarks =getIntent().getStringExtra("totalmarks");
         negativemarks =getIntent().getStringExtra("negativemarks");
         duration =getIntent().getStringExtra("duration");

        txtTestName=(TextView)findViewById(R.id.txtTestName);
        txtQuestions=(TextView)findViewById(R.id.txtQuestions);
        txtmarks=(TextView)findViewById(R.id.txtMarks);
        txtNegativeMarks=(TextView)findViewById(R.id.txtNegMarks);
        txtDuration=(TextView)findViewById(R.id.txtDuration);

        txtTestName.setText(testname);
        txtQuestions.setText(totalquestions);
        txtmarks.setText(totalmarks);
        txtNegativeMarks.setText(negativemarks);
        txtDuration.setText(duration);

        btnStart =(Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnStart:
                Intent intent =new Intent(TestDetailsActivity.this,ExamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("testid",testid);
                intent.putExtra("testname",testname);
                intent.putExtra("totalquestions",totalquestions);
                intent.putExtra("totalmarks",totalmarks);
                intent.putExtra("negativemarks",negativemarks);
                intent.putExtra("duration",duration);

                startActivity(intent);
                break;
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
