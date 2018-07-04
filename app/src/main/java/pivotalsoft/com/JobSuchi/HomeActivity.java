package pivotalsoft.com.JobSuchi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import pivotalsoft.com.JobSuchi.Adapters.AddsAdaptes;
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.Response.AddsInfoItem;
import pivotalsoft.com.JobSuchi.utilities.Session;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Session session;
    AddsInfoItem addsInfo=new AddsInfoItem();
    Gson gson;
    private static ViewPager mPager;
    CircleIndicator indicator;
    private static int currentPage = 0;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    LinearLayout coursesLayout, batchesLayout, eventsLayout, askLayout, studyLayout, offerLayout, qrCodeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String fullname =pref.getString("fullname",null);
        String email =pref.getString("email",null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);

        // userName on Nav Header
        TextView txtname = (TextView)headerLayout.findViewById(R.id.txtFullname);
        txtname.setText(fullname);

        // userName on Nav Header
        TextView txtEmail = (TextView)headerLayout.findViewById(R.id.txtEmail);
        txtEmail.setText(email);


        coursesLayout=(LinearLayout)findViewById(R.id.coursesLayout);
        coursesLayout.setOnClickListener(this);

        batchesLayout=(LinearLayout)findViewById(R.id.batchsLayout);
        batchesLayout.setOnClickListener(this);



        askLayout=(LinearLayout)findViewById(R.id.askLayout);
        askLayout.setOnClickListener(this);

        studyLayout=(LinearLayout)findViewById(R.id.studyLayout);
        studyLayout.setOnClickListener(this);

        offerLayout =(LinearLayout)findViewById(R.id.offerLayout);
        offerLayout.setOnClickListener(this);

        qrCodeLayout =(LinearLayout)findViewById(R.id.qrCodeLayout);
        qrCodeLayout.setOnClickListener(this);

     init();
    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent logout =new Intent(this,LoginActivity.class);
            logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(logout);

            editor = pref.edit();
            editor.clear();
            editor.apply();

            logout();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_slideshow) {
             Intent pivotal =new Intent(this,MyTestsActivity.class);
             pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(pivotal);


        } else if (id == R.id.nav_profile) {

            Intent pivotal =new Intent(this,UpdateProfileActivity.class);
            pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(pivotal);


        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.coursesLayout:

        Intent pivotal =new Intent(this,SubCategoryActivity.class);
        pivotal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(pivotal);

        break;

        case R.id.batchsLayout:
                Intent batch =new Intent(this,CollegesActivity.class);
                batch.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(batch);

        break;



        case R.id.askLayout:

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+91939172255"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);

        break;

        case R.id.studyLayout:

               /* Intent study =new Intent(this,StudyLiveActivity.class);
                study.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(study);*/

        break;

        case R.id.offerLayout:

            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.pivotalsoft.user.hikestreet");

            Log.e("LUNCH",""+launchIntent);

            if (launchIntent != null){
                startActivity(launchIntent);
            }
            else {
                Intent intentplay = new Intent(Intent.ACTION_VIEW);
                intentplay.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.pivotalsoft.user.hikestreet&hl=en"));
                startActivity(intentplay);
            }


        break;

        case R.id.qrCodeLayout:

        Intent offer =new Intent(this,NewsListActivity.class);
                offer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(offer);

        break;
    }
    }


    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);

        indicator = (CircleIndicator) findViewById(R.id.indicator);



        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {

                if (addsInfo.getAdsdata()!=null) {
                    if (currentPage == addsInfo.getAdsdata().size() - 1) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                    indicator.setViewPager(mPager);
                }
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 10000, 10000);

        getAddsInformation();

    }

    // response from gson lib
    private void getAddsInformation(){

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Constants.AD_INFO_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // String responsestr = response;
                gson = new Gson();
                addsInfo = gson.fromJson(response,AddsInfoItem.class);

                // mPager.setAdapter(adapter);

                if (getApplicationContext()!=null){

                    AddsAdaptes adapter = new AddsAdaptes(getApplicationContext(), addsInfo.getAdsdata());
                    mPager.setAdapter(adapter);
                    //dynamic adapter
                    adapter.registerDataSetObserver(indicator.getDataSetObserver());
                    adapter.notifyDataSetChanged();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
        requestQueue.add(stringRequest);

    }
}
