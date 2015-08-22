package in.qwerto.qwerto.request;


import android.app.Dialog;
import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import java.util.ArrayList;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 21/8/15.
 */
public class FragmentRequests extends Fragment {

    LinearLayout addRequest;
    DialogRequest dialog;
    RecyclerView requests;
    public static RequestsAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    public static ArrayList<RequestClass> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag_requests, container, false);

        addRequest = (LinearLayout) view.findViewById(R.id.lAddRequest);
        addRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new DialogRequest(getActivity());
                dialog.show();
            }
        });

        requests = (RecyclerView) view.findViewById(R.id.rvRequests);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipetoRefreshRequests);
        adapter = new RequestsAdapter(getActivity(),getData());

        requests.setAdapter(adapter);
        requests.setLayoutManager(new LinearLayoutManager(getActivity()));


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refresh List
            }
        });

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d("swipe"," "+direction);
                data.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        touchHelper.attachToRecyclerView(requests);

        return view;
    }

    public ArrayList<RequestClass> getData(){
        data = new ArrayList<RequestClass>();
        String [] msgs = {"2","3","1","4","2","69"};
        String [] vendors = {"5","2","4","2","1","120"};
        String [] requests ={"Hi I would like to purchase a bluetooth",
                "I want to book 6 tickets for Jurassic World ",
                "Book a bus for me to Banglore tomorrow",
                "Order Pizza from Pizza hut",
                "Looking to buy a second hand bike",
                "Would like to see someone in my bed tonight!"};
        for(int i=0;i<msgs.length;i++){
            RequestClass requestClass = new RequestClass();
            requestClass.setRequest(requests[i]);
            requestClass.setNumOfVendors(vendors[i]);
            requestClass.setNumOfMsgs(msgs[i]);
            data.add(requestClass);
        }
        return data;
    }

}
