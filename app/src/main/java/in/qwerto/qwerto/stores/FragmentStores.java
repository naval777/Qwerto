package in.qwerto.qwerto.stores;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.qwerto.qwerto.R;
import in.qwerto.qwerto.SingleSuggestion;
import in.qwerto.qwerto.common.ChatLocation;
import in.qwerto.qwerto.common.SuggestionsPagerAdapter;

/**
 * Created by sandeep on 21/8/15.
 */
public class FragmentStores extends Fragment {

    ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_chat_suggestion, container, false);
//        pager = (ViewPager) view.findViewById(R.id.pagerSuggestions);
//        ArrayList<SingleSuggestion> ss = new ArrayList<SingleSuggestion>();
//        Log.d("ChatActivity", "working");
//        ss.add(new SingleSuggestion("Mandakini Hostel", new ChatLocation(1,4, "Mandakini hostel",13.082680,80.270718)));
//        ss.add(new SingleSuggestion("Research Park",new ChatLocation(1,4,"Research Park",12.985854,80.249685)));
//        ss.add(new SingleSuggestion("Tiruvanmayur", new ChatLocation(1,4,"Tirubanmayur",13.009361,80.213231)));
//        ss.add(new SingleSuggestion("NIFT", new ChatLocation(1,4,"NIFT",12.984193,80.251769)));
//        SuggestionsPagerAdapter adapter = new SuggestionsPagerAdapter(getActivity(),ss, new ChatLocation(1,4,"Research Park",12.985854,80.249685));
//        pager.setAdapter(adapter);
        return view;
    }
}
