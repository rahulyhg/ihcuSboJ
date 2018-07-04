package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.RankItem;

/**
 * Created by USER on 2/19/2018.
 */

public class RankAdapter3 extends RecyclerView.Adapter<RankAdapter3.MyViewHolder> {

    private Context mContext;
    private List<RankItem.AlldataBean> courseDetailsList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtcourse,txtBCAB,txtBCBB,txtBCCB,txtBCDB,txtBCEB;


        public MyViewHolder(View view) {
            super(view);
            txtcourse = (TextView) view.findViewById(R.id.txtCourse);
            txtBCAB = (TextView) view.findViewById(R.id.txtBCAB);
            txtBCBB = (TextView) view.findViewById(R.id.txtBCBB);
            txtBCCB = (TextView) view.findViewById(R.id.txtBCCB);
            txtBCDB=(TextView)view.findViewById(R.id.txtBCDB);
            txtBCEB = (TextView) view.findViewById(R.id.txtBCEB);

        }
    }


    public RankAdapter3(Context mContext, List<RankItem.AlldataBean> courseDetailsList) {
        this.mContext = mContext;
        this.courseDetailsList = courseDetailsList;
    }

    @Override
    public RankAdapter3.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_item2, parent, false);

        return new RankAdapter3.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RankAdapter3.MyViewHolder holder, final int position) {
        final RankItem.AlldataBean album = courseDetailsList.get(position);
        holder.txtcourse.setText(album.getCourse());
        holder.txtBCAB.setText(album.getBCAGirls());
        holder.txtBCBB.setText(album.getBCBGirls());
        holder.txtBCCB.setText(album.getBCCGirls());
        holder.txtBCDB.setText(album.getBCDGirls());
        holder.txtBCEB.setText("");

    }


    @Override
    public int getItemCount() {
        return courseDetailsList.size();
    }



}




