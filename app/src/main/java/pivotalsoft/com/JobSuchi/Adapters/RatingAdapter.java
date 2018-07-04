package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.CollegeRatingsItem;
import pivotalsoft.com.JobSuchi.Response.RankItem;

/**
 * Created by USER on 2/22/2018.
 */

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.MyViewHolder> {

    private Context mContext;
    private List<CollegeRatingsItem.AlldataBean> courseDetailsList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtRatingname,txtDescription,txtDate;


        public MyViewHolder(View view) {
            super(view);
            txtRatingname = (TextView) view.findViewById(R.id.txtRatingname);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            txtDate = (TextView) view.findViewById(R.id.txtDate);



        }
    }


    public RatingAdapter(Context mContext, List<CollegeRatingsItem.AlldataBean> courseDetailsList) {
        this.mContext = mContext;
        this.courseDetailsList = courseDetailsList;
    }

    @Override
    public RatingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rating_item, parent, false);

        return new RatingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RatingAdapter.MyViewHolder holder, final int position) {
        final CollegeRatingsItem.AlldataBean album = courseDetailsList.get(position);
        holder.txtRatingname.setText(album.getRatingname());
        holder.txtDate.setText("posted on : "+album.getRatedon());
        holder.txtDescription.setText(album.getDescription());

    }


    @Override
    public int getItemCount() {
        return courseDetailsList.size();
    }



}



