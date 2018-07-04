package pivotalsoft.com.JobSuchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsDetailsActivity extends AppCompatActivity {
ImageView imageViewNews;
TextView txtTitle,txtDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String imageurl =getIntent().getStringExtra("newsImage");
        String title =getIntent().getStringExtra("newsTitle");
        getSupportActionBar().setTitle(title);
        String description =getIntent().getStringExtra("description");

        txtTitle=(TextView)findViewById(R.id.txtNewsTitle);
        txtTitle.setText(title);

        txtDescription=(TextView)findViewById(R.id.txtNewsDescription);
        txtDescription.setText(description);

        imageViewNews=(ImageView)findViewById(R.id.newsImage);

        try {
            Glide.with(this).load(imageurl).into(imageViewNews);
        } catch (Exception e) {
            e.printStackTrace();
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
