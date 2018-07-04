package pivotalsoft.com.JobSuchi.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import pivotalsoft.com.JobSuchi.R;
import pivotalsoft.com.JobSuchi.Response.SliderItem;

/**
 * Created by Gangadhar on 10/30/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    private List<SliderItem> sliderItemList;

    public CustomPagerAdapter(Context mContext, List<SliderItem> sliderItemList) {
        this.mContext = mContext;
        this.sliderItemList = sliderItemList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return sliderItemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        final SliderItem album = sliderItemList.get(position);

        TextView txtQuestionNo = (TextView) itemView.findViewById(R.id.txtQuestionNo);
        TextView txtQuestion = (TextView) itemView.findViewById(R.id.txtQuestion);

        final RadioGroup rg = (RadioGroup) itemView.findViewById(R.id.rg);
        LinearLayout pa =(LinearLayout)itemView.findViewById(R.id.parentLayout);

        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = rg .getCheckedRadioButtonId();

                // find the radio button by returned id
                RadioButton radioButton = (RadioButton)itemView. findViewById(selectedId);

                Toast.makeText(mContext, radioButton.getText(), Toast.LENGTH_SHORT).show();

            }
        });


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

