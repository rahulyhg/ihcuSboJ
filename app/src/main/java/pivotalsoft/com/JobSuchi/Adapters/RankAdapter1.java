package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.RankItem;

/**
 * Created by USER on 2/19/2018.
 */

public class RankAdapter1 extends RecyclerView.Adapter<RankAdapter1.MyViewHolder>{


    private Context mContext;
    private List<RankItem.AlldataBean> courseDetailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtcourse,txtOCB,txtOCG,txtSCB,txtSCG,txtSTB,txtSTG;


        public MyViewHolder(View view) {
            super(view);
            txtcourse = (TextView) view.findViewById(R.id.txtCourse);
            txtOCB = (TextView) view.findViewById(R.id.txtOcb);
            txtOCG = (TextView) view.findViewById(R.id.txtOcG);
            txtSCB = (TextView) view.findViewById(R.id.txtScb);
            txtSCG=(TextView)view.findViewById(R.id.txtScG);
            txtSTB = (TextView) view.findViewById(R.id.txtStb);
            txtSTG=(TextView)view.findViewById(R.id.txtStG);


        }
    }


    public RankAdapter1(Context mContext, List<RankItem.AlldataBean> courseDetailsList) {
        this.mContext = mContext;
        this.courseDetailsList = courseDetailsList;
    }

    @Override
    public RankAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_item1, parent, false);

        return new RankAdapter1.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RankAdapter1.MyViewHolder holder, final int position) {
        final RankItem.AlldataBean album = courseDetailsList.get(position);
        holder.txtcourse.setText(album.getCourse());
        holder.txtOCB.setText(album.getOCBoys());
        holder.txtOCG.setText(album.getOCGirls());
        holder.txtSCB.setText(album.getSCBoys());
        holder.txtSCG.setText(album.getSCGirls());
        holder.txtSTB.setText(album.getSTBoys());
        holder.txtSTG.setText(album.getSTGirls());


    }


    @Override
    public int getItemCount() {
        return courseDetailsList.size();
    }


}


