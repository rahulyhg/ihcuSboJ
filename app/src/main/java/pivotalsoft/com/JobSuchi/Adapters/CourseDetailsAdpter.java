package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.CourseDetailsItem;

/**
 * Created by USER on 2/19/2018.
 */

public class CourseDetailsAdpter extends RecyclerView.Adapter<CourseDetailsAdpter.MyViewHolder> {

    private Context mContext;
    private List<CourseDetailsItem.AlldataBean> courseDetailsList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtcourse,txtCourseCode,txtRemarks,txtIntake,txtEligibulity;


        public MyViewHolder(View view) {
            super(view);
            txtcourse = (TextView) view.findViewById(R.id.txtCourse);
            txtCourseCode = (TextView) view.findViewById(R.id.txtCourseCode);
            txtRemarks = (TextView) view.findViewById(R.id.txtRemarks);
            txtIntake = (TextView) view.findViewById(R.id.txtIntake);
            txtEligibulity=(TextView)view.findViewById(R.id.txtEligibulity);


        }
    }


    public CourseDetailsAdpter(Context mContext, List<CourseDetailsItem.AlldataBean> courseDetailsList) {
        this.mContext = mContext;
        this.courseDetailsList = courseDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CourseDetailsItem.AlldataBean album = courseDetailsList.get(position);
        holder.txtcourse.setText(album.getCourse());
        holder.txtCourseCode.setText(album.getSpecialization());
        holder.txtIntake.setText(album.getIntake());
        holder.txtRemarks.setText(album.getRemarks());


    }


    @Override
    public int getItemCount() {
        return courseDetailsList.size();
    }



}

