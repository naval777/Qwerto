package in.qwerto.qwerto.stores;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 21/8/15.
 */
public class FragmentStores extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_stores, container, false);
        return view;
    }
}
