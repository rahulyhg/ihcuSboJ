package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import pivotalsoft.com.JobSuchi.CollegeDetailsActivity;
import pivotalsoft.com.JobSuchi.PreviousScoreActivity;
import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.CollegeItem;
import pivotalsoft.com.JobSuchi.Response.MyTestsItem;
import pivotalsoft.com.JobSuchi.ScoreActivity;

/**
 * Created by USER on 2/23/2018.
 */

public class MyTestAdpter extends RecyclerView.Adapter<MyTestAdpter.MyViewHolder> {

    private Context mContext;
    private List<MyTestsItem.OnedataBean> myTestsItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTestName,txtPercentage,txtRemarks,txttotalscore,txtobtainedscore,txtTime,txtRank;
        LinearLayout parentLayout;


        public MyViewHolder(View view) {
            super(view);
            txtTestName = (TextView) view.findViewById(R.id.txtTestName);
            txtPercentage = (TextView) view.findViewById(R.id.txtPercentage);
            txtRemarks = (TextView) view.findViewById(R.id.txtRemarks);
            txttotalscore = (TextView) view.findViewById(R.id.txttotalscore);
            txtobtainedscore = (TextView) view.findViewById(R.id.txtobtainedscore);
            txtTime = (TextView) view.findViewById(R.id.txtTime);
            txtRank = (TextView) view.findViewById(R.id.txtRank);
            parentLayout=(LinearLayout)view.findViewById(R.id.parentLayout);

        }
    }


    public MyTestAdpter(Context mContext, List<MyTestsItem.OnedataBean> collegeItemList) {
        this.mContext = mContext;
        this.myTestsItemList = collegeItemList;
    }

    @Override
    public MyTestAdpter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_test_item, parent, false);

        return new MyTestAdpter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyTestAdpter.MyViewHolder holder, final int position) {
        final MyTestsItem.OnedataBean album = myTestsItemList.get(position);

        String percentage=album.getPercentage().substring(0,4).toUpperCase();

        holder.txtPercentage.setText("Percentage : "+percentage +"%");
        holder.txtRemarks.setText("Remarks : "+album.getRemarks());
        holder.txttotalscore.setText("TotalScore : "+album.getTotalscore());
        holder.txtobtainedscore.setText("ObtainedScore : "+album.getObtainedscore());
        holder.txtTime.setText("Date : "+album.getTesttime());
        holder.txtTestName.setText(album.getTestname());
        holder.txtRank.setText(album.getRank());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,PreviousScoreActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("studenttestid",album.getStudenttestid());
                intent.putExtra("testid",album.getTestid());
                intent.putExtra("testName",album.getTestname());

                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return myTestsItemList.size();
    }



}


