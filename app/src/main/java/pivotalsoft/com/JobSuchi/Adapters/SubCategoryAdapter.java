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
import pivotalsoft.com.JobSuchi.Response.SubCategoryItem;
import pivotalsoft.com.JobSuchi.TestActivity;

/**
 * Created by USER on 1/31/2018.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

private Context mContext;
private List<SubCategoryItem.AlldataBean> buyerSearchItems;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView txtSubCategoryName,txtDescription;
    LinearLayout parentLayout;
    ImageView imgView;

    public MyViewHolder(View view) {
        super(view);
        txtSubCategoryName = (TextView) view.findViewById(R.id.txtSubCategoryName);
        txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        // txtStatus = (TextView) view.findViewById(R.id.txtStatus);
        imgView = (ImageView) view.findViewById(R.id.imgview);
        parentLayout=(LinearLayout)view.findViewById(R.id.parentLayout);

    }
}


    public SubCategoryAdapter(Context mContext, List<SubCategoryItem.AlldataBean> coursesItemList) {
        this.mContext = mContext;
        this.buyerSearchItems = coursesItemList;
    }

    @Override
    public SubCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory_item, parent, false);

        return new SubCategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SubCategoryAdapter.MyViewHolder holder, final int position) {
        final SubCategoryItem.AlldataBean album = buyerSearchItems.get(position);
        holder.txtSubCategoryName.setText(album.getSubcategoryname());
        holder.txtDescription.setText(album.getDescription());


       /* try {
            Glide.with(mContext).load(album.getImage()).into(holder.imgHouse);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,TestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("subcategoryid",album.getSubcategoryid());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return buyerSearchItems.size();
    }



}
