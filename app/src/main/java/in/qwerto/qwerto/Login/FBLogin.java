package in.qwerto.qwerto.Login;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.facebook.AccessToken;

import in.qwerto.qwerto.R;


public class FBLogin extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_fblogin);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FBLoginFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isLoggedIn()){

        }
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}