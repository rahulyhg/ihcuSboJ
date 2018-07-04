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
import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.CollegeItem;

/**
 * Created by USER on 2/17/2018.
 */

public class CollegeListAdpter extends RecyclerView.Adapter<CollegeListAdpter.MyViewHolder> {

    private Context mContext;
    private List<CollegeItem> collegeItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtCollegeName,txtNo,txtAddrerss,txtCode;
        RelativeLayout parentLayout;


        public MyViewHolder(View view) {
            super(view);
            txtCollegeName = (TextView) view.findViewById(R.id.txtCollegeName);
            //txtNo = (TextView) view.findViewById(R.id.txtNo);
            txtAddrerss = (TextView) view.findViewById(R.id.txtCollegeAddress);
            txtCode = (TextView) view.findViewById(R.id.txtCollegeCode);
            parentLayout=(RelativeLayout)view.findViewById(R.id.parentLayout);

        }
    }


    public CollegeListAdpter(Context mContext, List<CollegeItem> collegeItemList) {
        this.mContext = mContext;
        this.collegeItemList = collegeItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.college_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CollegeItem album = collegeItemList.get(position);
        holder.txtCollegeName.setText(album.getCollegename());
        holder.txtAddrerss.setText(album.getAddress());
        holder.txtCode.setText(album.getCollegecode());


       /* try {
            Glide.with(mContext).load(album.getNewsImage()).into(holder.imgView);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,CollegeDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                /*intent.putExtra("collegecode",album.getCollegecode());
                intent.putExtra("collegename",album.getCollegename());
                intent.putExtra("address",album.getAddress());
                intent.putExtra("district",album.getDistrict());
                intent.putExtra("state",album.getState());
                intent.putExtra("gender",album.getGender());
                intent.putExtra("affiliatedto",album.getAffiliatedto());
                intent.putExtra("region",album.getRegion());
                intent.putExtra("startedyear",album.getStartedyear());
                intent.putExtra("collegepic", Constants.IMAGE_COLLEGE_URL+album.getCollegepic());
                intent.putExtra("telephone",album.getTelephone());
                intent.putExtra("email",album.getEmail());
                intent.putExtra("website",album.getWebsite());*/
                intent.putExtra("collegeid",album.getCollegeid());

                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return collegeItemList.size();
    }



}

