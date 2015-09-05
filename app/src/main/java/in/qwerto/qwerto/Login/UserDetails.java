package in.qwerto.qwerto.Login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import in.qwerto.qwerto.HomeActivity;
import in.qwerto.qwerto.R;


public class UserDetails extends ActionBarActivity {

    EditText name, mobile, email;
    ImageView dp;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_user_details);

        dp = (ImageView) findViewById(R.id.user_image);
        name = (EditText) findViewById(R.id.user_name);
        mobile = (EditText) findViewById(R.id.user_mobile);
        email = (EditText) findViewById(R.id.user_email);
        submit = (Button) findViewById(R.id.bSubmitDetails);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDetails.this, HomeActivity.class);
                startActivity(i);
            }
        });

        Intent i = getIntent();
        Bitmap b = BitmapFactory.decodeByteArray(
                i.getByteArrayExtra("image"), 0, getIntent().getByteArrayExtra("image").length);
        dp.setImageBitmap(b);

        name.setText(i.getStringExtra("name"));
        if(i.getStringExtra("email")!=null){
            email.setText(i.getStringExtra("email"));
        }

    }
}
