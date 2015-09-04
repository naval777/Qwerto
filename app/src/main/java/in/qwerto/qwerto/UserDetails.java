package in.qwerto.qwerto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;


public class UserDetails extends ActionBarActivity {

    EditText name, mobile, email;
    ImageView dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        dp = (ImageView) findViewById(R.id.user_image);
        name = (EditText) findViewById(R.id.user_name);
        mobile = (EditText) findViewById(R.id.user_mobile);
        email = (EditText) findViewById(R.id.user_email);

        Intent i = getIntent();
        Bitmap b = BitmapFactory.decodeByteArray(
                i.getByteArrayExtra("image"), 0, getIntent().getByteArrayExtra("image").length);
        dp.setImageBitmap(b);

        name.setText(i.getStringExtra("name"));
    }
}
