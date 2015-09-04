package in.qwerto.qwerto;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep on 22/5/15.
 */
public class FBLoginFragment extends Fragment {

    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private Profile profile;
    private FacebookCallback<LoginResult> callback;
    private ProfileTracker profileTracker;

    ViewPager _mViewPager;
    private TutorialAdapter _adapter;
    Uri img_value;

    public FBLoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profil, Profile profile2) {
                        Log.d("facebook - profile", profile2.getFirstName());
                        profile = profile2;
                        Log.d("facebook -", profile.getFirstName());
                        Log.d("fb_id", profile.getId());
                        Log.d("fb_uri", String.valueOf(profile.getProfilePictureUri(100, 100)));
                        Log.d("email",profile.getString)

                        img_value = profile.getProfilePictureUri(100, 100);

                        asyncTask at = new asyncTask();
                        at.execute();
                        profileTracker.stopTracking();
                    }
                };

                profileTracker.startTracking();
                accessToken = loginResult.getAccessToken();

            }

            @Override
            public void onCancel() {
                Log.d("cancel", "Cancelled");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("error", e.toString());
            }
        };
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        List<String> permissions = new ArrayList<String>();
        permissions.add("public_profile");
        permissions.add("email");
        loginButton.setReadPermissions(permissions);
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_fblogin, container, false);
        _mViewPager = (ViewPager) view.findViewById(R.id.registerPager);
        _adapter = new TutorialAdapter(getActivity());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
//        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrollStateChanged(int position) {
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                // TODO Auto-generated method stub
////                btnAction(position);
////                titleAction(position);
//
//            }
//
//        });
//        _btn1=(Button)view.findViewById(R.id.btn1);
//        _btn2=(Button)view.findViewById(R.id.btn2);
//        _btn3= (Button) view.findViewById(R.id.btn3);
//        _btn4= (Button) view.findViewById(R.id.btn4);

//        initButton();
//        setTab();
        return view;
    }

//    private void initButton(){
//        setButton(_btn1, 1, 10, 10);
//        setButton(_btn2,0,10,10);
//        setButton(_btn3,0,10,10);
//        setButton(_btn4,0,10,10);
//    }

//    private void setButton(Button btn,int color, int h, int w){
//        btn.setWidth(w);
//        btn.setHeight(h);
//        if(color==0){
//            btn.setBackgroundColor(getResources().getColor(R.color.dull));
//        }else{
//            btn.setBackgroundColor(Color.BLACK);
//        }
//    }

//    private void setTab(){
//        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
//
//            @Override
//            public void onPageScrollStateChanged(int position) {}
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {}
//            @Override
//            public void onPageSelected(int position) {
//                // TODO Auto-generated method stub
//                btnAction(position);
//                titleAction(position);
//            }
//
//        });
//
//    }
//    private void titleAction(int position) {
//        switch(position){
//            case 0: title.setText("Participate"); break;
//
//            case 1: title.setText("Engage"); break;
//
//            case 2: title.setText("Empower"); break;
//
//            case 3: title.setText("Enhance"); break;
//        }
//    }

//    private void btnAction(int action){
//        switch(action){
//            case 0: setButton(_btn1,1,10,10); setButton(_btn2,0,10,10); setButton(_btn3,0,10,10); setButton(_btn4,0,10,10);break;
//
//            case 1: setButton(_btn1,0,10,10); setButton(_btn2,1,10,10); setButton(_btn3,0,10,10); setButton(_btn4,0,10,10);break;
//
//            case 2: setButton(_btn1,0,10,10); setButton(_btn2,0,10,10); setButton(_btn3,1,10,10); setButton(_btn4,0,10,10);break;
//
//            case 3: setButton(_btn1,0,10,10); setButton(_btn2,0,10,10); setButton(_btn3,0,10,10); setButton(_btn4,1,10,10);break;
//        }
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager.onActivityResult(requestCode, resultCode, data))
            return;
    }

    public class asyncTask extends AsyncTask<Void, Void, String> {

        Bitmap mIcon1 = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

//            try {
//                mIcon1 = BitmapFactory.decodeStream(img_value.openConnection().getInputStream());
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            mIcon1 = getImageBitmap(String.valueOf(img_value));
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
//            Complaint.userImage.setImageBitmap(mIcon1);
            Toast.makeText(getActivity(), "Successfully logged in",
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(getActivity(), UserDetails.class);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            mIcon1.compress(Bitmap.CompressFormat.PNG, 50, bs);
            i.putExtra("image", bs.toByteArray());
            i.putExtra("name", profile.getName());
            startActivity(i);
            getActivity().finish();
        }
    }


    final private static Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("error in bmp", "Error getting bitmap", e);
        }
        return bm;
    }
}