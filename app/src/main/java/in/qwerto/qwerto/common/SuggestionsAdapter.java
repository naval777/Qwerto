package in.qwerto.qwerto.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
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
 * Created by sandeep on 6/9/15.
 */
public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.SuggestionViewHolder> {
    private LayoutInflater inflater;
    Context context;
    ArrayList<SingleSuggestion> suggestions;

    public SuggestionsAdapter(Context context, ArrayList<SingleSuggestion> suggestions) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.suggestions = suggestions;
    }

    @Override
    public SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = inflater.inflate(R.layout.view_single_suggestion, parent, false);
        return new SuggestionViewHolder(view, 1);

    }

    @Override
    public void onBindViewHolder(SuggestionViewHolder holder, int position) {


        if(position<suggestions.size()){
            SingleSuggestion single = suggestions.get(position);
            holder.image.setImageResource(R.drawable.add);
            holder.name.setText(single.getName());
        }else{
            double lat = 12.985844;
            double lon = 80.249675;
            String url = "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lon+"&zoom=17&size=600x300&maptype=normal";
            new DownloadImageTask(holder.image).execute(url);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, MapsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("locations", suggestions);
//                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return suggestions.size()+1;
    }


    class SuggestionViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public SuggestionViewHolder(View itemView, int type) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.ivPlacePic);
            name = (TextView) itemView.findViewById(R.id.tvPlaceName);

        }
    }
}