package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import pivotalsoft.com.JobSuchi.CollegeDetailsActivity;
import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.MyTestsItem;
import pivotalsoft.com.JobSuchi.Response.StudentDetailsItem;

/**
 * Created by USER on 2/26/2018.
 */

public class SectionScoreAdapter extends RecyclerView.Adapter<SectionScoreAdapter.MyViewHolder> {

    private Context mContext;
    private List<StudentDetailsItem.OnedataBean> myTestsItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtPercentage,txtSection,txttotalscore,txtScore;
        //RelativeLayout parentLayout;


        public MyViewHolder(View view) {
            super(view);
            txtPercentage = (TextView) view.findViewById(R.id.txtPercentage);
            txtSection = (TextView) view.findViewById(R.id.txtSection);
            txttotalscore = (TextView) view.findViewById(R.id.txttotalscore);
            //txtobtainedscore = (TextView) view.findViewById(R.id.txtobtainedscore);
            txtScore = (TextView) view.findViewById(R.id.txtscore);
           // parentLayout=(RelativeLayout)view.findViewById(R.id.parentLayout);

        }
    }


    public SectionScoreAdapter(Context mContext, List<StudentDetailsItem.OnedataBean> collegeItemList) {
        this.mContext = mContext;
        this.myTestsItemList = collegeItemList;
    }

    @Override
    public SectionScoreAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.studentdetails_item, parent, false);

        return new SectionScoreAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SectionScoreAdapter.MyViewHolder holder, final int position) {
        final StudentDetailsItem.OnedataBean album = myTestsItemList.get(position);

        String percentage=album.getPercentage().substring(0,4).toUpperCase();
        holder.txtPercentage.setText(percentage);


        String firstScore=album.getObtainedscore().substring(0,1).toUpperCase();
        String secondScore=album.getTotalscore().substring(0,1).toUpperCase();
        holder.txttotalscore.setText(secondScore);
        holder.txtScore.setText(firstScore+"/"+secondScore);
        holder.txtSection.setText(album.getSection());



    }


    @Override
    public int getItemCount() {
        return myTestsItemList.size();
    }



}


