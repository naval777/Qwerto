package in.qwerto.qwerto.Login;

/**
 * Created by sandeep on 4/9/15.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 1/3/15.
 */
public class TutorialAdapter extends PagerAdapter {



    Context mContext;
    LayoutInflater mLayoutInflater;


    public TutorialAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_single_tutorial, container, false);
        TextView tv = (TextView) itemView.findViewById(R.id.pager_text);
        tv.setText(Integer.toString(position)+1);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}