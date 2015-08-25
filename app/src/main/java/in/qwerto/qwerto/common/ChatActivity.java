package in.qwerto.qwerto.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 21/8/15.
 */
public class ChatActivity extends ActionBarActivity {

    RecyclerView chat;
    ArrayList<ChatClass> chatData;
    ChatAdapter adapter;
    Toolbar toolbar;

    EditText chatBox;
    ImageView postAttach;

    int postOrAttachCheck=1;
    DialogChat dialogChat;

    String [] msgs = {"Hi",
            "Hi. How can I help you?",
            "What's up girl",
            "I am pregnant",
            "No Joke",
            "Is Danny the dad",
            "Yeah that night",
            "We were very drunk",
            "Okay. What are you gonna do?",
            "Well I didn't want to tell Danny this",
            "You have to tell him","No way",
            "I know. It could be 10",
            "Looks like their backs has been broken"};
    int [] sides={1,2,2,1,1,2,1,1,2,1,2,1,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_chat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.propic);
        //getSupportActionBar().setLogo(R.drawable.propic);
        getSupportActionBar().setTitle("XYZ Agencies");
        getSupportActionBar().setSubtitle("Specialist in Mobile Repairs");

        chat = (RecyclerView) findViewById(R.id.rvChat);
        chatBox = (EditText) findViewById(R.id.etMessage);
        postAttach = (ImageView) findViewById(R.id.ivPostAttach);
        chatData = new ArrayList<ChatClass>();

        for(int i=0;i<sides.length;i++){
            ChatClass chatClass = new ChatClass(sides[i],msgs[i]);
            chatData.add(chatClass);
        }

        adapter = new ChatAdapter(this,chatData);

        chat.setAdapter(adapter);
        chat.setLayoutManager(new LinearLayoutManager(this));

        postAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(postOrAttachCheck==1){
                    ChatClass chatClass = new ChatClass(2,chatBox.getText().toString());
                    chatData.add(chatClass);
                    adapter.notifyDataSetChanged();
                    chatBox.setText("");
                    chat.scrollToPosition(chatData.size()-1);
                }else{
                    dialogChat = new DialogChat(ChatActivity.this);
                    dialogChat.show();
                }
            }
        });


        chatBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {


                if(chatBox.getText().toString().length()==0){
                    postAttach.setImageResource(R.drawable.attachment);
                    postOrAttachCheck=2;
                }else{
                    postAttach.setImageResource(R.drawable.posttt);
                    postOrAttachCheck=1;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
