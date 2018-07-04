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

import com.bumptech.glide.Glide;

import java.util.List;

import pivotalsoft.com.JobSuchi.Constants.Constants;
import pivotalsoft.com.JobSuchi.NewsDetailsActivity;
import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.NewsItem;

/**
 * Created by USER on 2/12/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

private Context mContext;
private List<NewsItem.NewsdataBean> newsItemList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle,txtPostdon;
    LinearLayout parentLayout;
    ImageView imgView;

    public MyViewHolder(View view) {
        super(view);
        txtTitle = (TextView) view.findViewById(R.id.txtNewsTitle);
        txtPostdon = (TextView) view.findViewById(R.id.txtPostdon);
        imgView = (ImageView) view.findViewById(R.id.newsImage);
        parentLayout=(LinearLayout)view.findViewById(R.id.parentLayout);

    }
}


    public NewsAdapter(Context mContext, List<NewsItem.NewsdataBean> coursesItemList) {
        this.mContext = mContext;
        this.newsItemList = coursesItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final NewsItem.NewsdataBean album = newsItemList.get(position);
        holder.txtTitle.setText(album.getTitle());
        holder.txtPostdon.setText("Posted on "+album.getPostedon());

        try {
            Glide.with(mContext).load(Constants.IMAGE_NEWS_URL+album.getImageurl()).into(holder.imgView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,NewsDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("newsId",album.getNewsid());
                intent.putExtra("newsTitle",album.getTitle());
                intent.putExtra("newsImage",Constants.IMAGE_NEWS_URL+album.getImageurl());
                intent.putExtra("description",album.getDescription());

                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return newsItemList.size();
    }



}
