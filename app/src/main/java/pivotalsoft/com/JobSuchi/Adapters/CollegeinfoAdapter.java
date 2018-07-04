package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.CollegeotehrinfoItem;

/**
 * Created by USER on 2/19/2018.
 */

public class CollegeinfoAdapter extends RecyclerView.Adapter<CollegeinfoAdapter.MyViewHolder> {

    private Context mContext;
    private List<CollegeotehrinfoItem.AlldataBean> courseDetailsList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtamenityname,txtdescription;
        LinearLayout parentlayout;


        public MyViewHolder(View view) {
            super(view);
            txtamenityname = (TextView) view.findViewById(R.id.txtamenityname);
            txtdescription = (TextView) view.findViewById(R.id.txtdescription);
            parentlayout=(LinearLayout)view.findViewById(R.id.parentLayout);

        }
    }


    public CollegeinfoAdapter(Context mContext, List<CollegeotehrinfoItem.AlldataBean> courseDetailsList) {
        this.mContext = mContext;
        this.courseDetailsList = courseDetailsList;
    }

    @Override
    public CollegeinfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.college_info_item, parent, false);

        return new CollegeinfoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CollegeinfoAdapter.MyViewHolder holder, final int position) {
        final CollegeotehrinfoItem.AlldataBean album = courseDetailsList.get(position);
        holder.txtamenityname.setText(album.getAmenityname());
        holder.txtdescription.setText(album.getDescription());

        if (position % 2 == 1) {
            holder.parentlayout.setBackgroundColor(Color.parseColor("#ffffffff"));
        } else {
            holder.parentlayout.setBackgroundColor(Color.parseColor("#55c4c4c4"));
        }

    }


    @Override
    public int getItemCount() {
        return courseDetailsList.size();
    }



}

