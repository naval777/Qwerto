package in.qwerto.qwerto.request;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 22/8/15.
 */
public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {

    private  LayoutInflater inflater;
    ArrayList<RequestClass> requestsData;
    Context context;

    public RequestsAdapter(Context context, ArrayList<RequestClass> requestsData){
        this.context =context;
        inflater=LayoutInflater.from(context);
        this.requestsData = requestsData;
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.view_single_request, parent, false);
        RequestViewHolder rvh = new RequestViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, final int position) {
        final RequestClass singleRequest = requestsData.get(position);
        holder.request.setText(singleRequest.getRequest());
        holder.numOfMsgs.setText(singleRequest.getNumOfMsgs());
        holder.numOfVendors.setText(singleRequest.getNumOfVendors());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,RequestDetailActivity.class);
                i.putExtra("request",singleRequest.getRequest());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestsData.size();
    }


    class RequestViewHolder extends RecyclerView.ViewHolder {

        TextView request, numOfMsgs, numOfVendors;

        public RequestViewHolder(View itemView) {
            super(itemView);

            request = (TextView) itemView.findViewById(R.id.tvRequestInList);
            numOfMsgs = (TextView) itemView.findViewById(R.id.tvNumOfMsgs);
            numOfVendors = (TextView) itemView.findViewById(R.id.tvNumOfVendors);
        }
    }
}

