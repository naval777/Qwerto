package in.qwerto.qwerto.request;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 21/8/15.
 */
public class FragmentRequests extends Fragment {

    LinearLayout addRequest;
    DialogRequest dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_requests, container, false);

        addRequest = (LinearLayout) view.findViewById(R.id.lAddRequest);
        addRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new DialogRequest(getActivity());
                dialog.show();
            }
        });
        return view;
    }
}
