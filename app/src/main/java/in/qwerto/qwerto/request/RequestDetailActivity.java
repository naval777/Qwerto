package in.qwerto.qwerto.request;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import in.qwerto.qwerto.R;

public class RequestDetailActivity extends ActionBarActivity {

    TextView request;
    RecyclerView vendors;
    VendorsAdapter adapter;
    ArrayList<String> vendorsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac_request_details);

        request = (TextView) findViewById(R.id.tvRequestComplete);
        vendors = (RecyclerView) findViewById(R.id.rvVendors);

        request.setText(getIntent().getStringExtra("request"));

        adapter = new VendorsAdapter(this,getVendorsData());

        vendors.setAdapter(adapter);
        vendors.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if(viewHolder.getAdapterPosition()!=0) {
                    //Error here. Check
//                    vendorsData.remove(viewHolder.getAdapterPosition());
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if(viewHolder.getAdapterPosition()==0) return 0;
                return super.getSwipeDirs(recyclerView, viewHolder);
            }
        });

        touchHelper.attachToRecyclerView(vendors);

    }

    private ArrayList<String> getVendorsData() {
        vendorsData = new ArrayList<String>();
        for(int i=0;i<20;i++){
            if(i==0){
                vendorsData.add("Qwerto support");
            }else{
                vendorsData.add("Vendor"+i);
            }
        }
        return vendorsData;
    }


}
