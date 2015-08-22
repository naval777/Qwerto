package in.qwerto.qwerto.request;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.qwerto.qwerto.R;
import in.qwerto.qwerto.common.ChatActivity;


/**
 * Created by sandeep on 22/8/15.
 */
public class VendorsAdapter extends RecyclerView.Adapter<VendorsAdapter.VendorsViewHolder> {

    private LayoutInflater inflater;
    ArrayList<String> vendorsData;
    Context context;

    public VendorsAdapter(Context context, ArrayList<String> vendorsData){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.vendorsData = vendorsData;
    }

    @Override
    public VendorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.view_single_vendor, parent, false);
        VendorsViewHolder vvh = new VendorsViewHolder(view);
        return vvh;
    }

    @Override
    public void onBindViewHolder(VendorsViewHolder holder, final int position) {

        holder.vendorName.setText(vendorsData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChatActivity.class);
                i.putExtra("vendor_name",vendorsData.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vendorsData.size();
    }

    class VendorsViewHolder extends RecyclerView.ViewHolder {

        TextView vendorName;

        public VendorsViewHolder(View itemView) {
            super(itemView);

            vendorName = (TextView) itemView.findViewById(R.id.tvVendorName);

        }
    }
}
