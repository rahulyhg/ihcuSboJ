package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.TestItem;
import pivotalsoft.com.JobSuchi.TestDetailsActivity;

/**
 * Created by USER on 1/26/2018.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private Context mContext;
    private List<TestItem.AlldataBean> buyerSearchItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTestNo,txtQuestion,txtAttempts;
        LinearLayout parentLayout;
        ImageView imgView;

        public MyViewHolder(View view) {
            super(view);
            txtTestNo = (TextView) view.findViewById(R.id.txtTestNo);
            txtQuestion = (TextView) view.findViewById(R.id.txtQuestion);
            txtAttempts = (TextView) view.findViewById(R.id.txtAttempts);
            imgView = (ImageView) view.findViewById(R.id.imgview);
            parentLayout=(LinearLayout)view.findViewById(R.id.parentLayout);

        }
    }


    public TestAdapter(Context mContext, List<TestItem.AlldataBean> coursesItemList) {
        this.mContext = mContext;
        this.buyerSearchItems = coursesItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TestItem.AlldataBean album = buyerSearchItems.get(position);
        holder.txtTestNo.setText(album.getTestname());
        holder.txtQuestion.setText("Total questions "+album.getTotalquestions()+"  |  "+album.getDuration()+"minutes");


       /* try {
            Glide.with(mContext).load(album.getImage()).into(holder.imgHouse);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,TestDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("testid",album.getTestid());
                intent.putExtra("testname",album.getTestname());
                intent.putExtra("totalquestions",album.getTotalquestions());
                intent.putExtra("totalmarks",album.getTotalmarks());
                intent.putExtra("negativemarks",album.getNegativemarks());
                intent.putExtra("duration",album.getDuration());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return buyerSearchItems.size();
    }



}
