package in.qwerto.qwerto.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import in.qwerto.qwerto.Maps.MapsActivity;
import in.qwerto.qwerto.R;
import in.qwerto.qwerto.SingleSuggestion;

/**
 * Created by sandeep on 5/9/15.
 */
public class SuggestionsPagerAdapter extends PagerAdapter {



    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<SingleSuggestion> suggestions;
    int [] images;
    ChatLocation chatLocation;

    public SuggestionsPagerAdapter(Context context, ArrayList<SingleSuggestion> suggestions, ChatLocation chatLocation) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.suggestions = suggestions;
        images = new int[suggestions.size()];
        for(int i=0;i<suggestions.size();i++){
            images[i] = R.drawable.add;
        }
        this.chatLocation = chatLocation;
    }

    @Override
    public int getCount() {
        return suggestions.size()+1;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_single_suggestion, container, false);
        TextView tv = (TextView) itemView.findViewById(R.id.tvPlaceName);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.ivPlacePic);

        if(position<suggestions.size()){
            tv.setText(suggestions.get(position).getName());
            imageView.setImageResource(images[position]);
        }
        else{
            tv.setText("");
            double lat = chatLocation.getLatitude();
            double lon = chatLocation.getLongitude();
            String url = "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lon+"&zoom=17&size=600x300&maptype=normal";
            new DownloadImageTask(imageView).execute(url);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, MapsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("locations",suggestions);
                    i.putExtras(bundle);
                    mContext.startActivity(i);
                }
            });
        }
        if (position==0){
            itemView.setPadding(0,20,0,0);
        }else if(position==suggestions.size()){
            itemView.setPadding(20,0,0,0);
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}