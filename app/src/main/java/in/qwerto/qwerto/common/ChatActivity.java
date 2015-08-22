package in.qwerto.qwerto.common;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 21/8/15.
 */
public class ChatActivity extends Activity {

    TextView chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_chat);

        chat = (TextView) findViewById(R.id.tvChatText);
        chat.setText("Chat with "+getIntent().getStringExtra("vendor_name"));
    }
}
