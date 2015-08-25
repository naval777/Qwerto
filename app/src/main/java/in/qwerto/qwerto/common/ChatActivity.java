package in.qwerto.qwerto.common;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
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
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;

import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 21/8/15.
 */
public class ChatActivity extends ActionBarActivity {

    RecyclerView chat;
    ArrayList<ChatClass> chatData;
    ChatAdapterTemp adapter;
    private final int TAKE_PIC = 1, OPEN_GALLERY = 2, LOCATION = 3, CONTACT = 4, AUDIO = 5, WISHLIST = 6, CROP_PIC = 7;
    Uri uri;
    Toolbar toolbar;

    EditText chatBox;
    ImageView postAttach;

    int postOrAttachCheck=2;
    DialogChat dialogChat;

    //Hard-coded data
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

    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap [] bitmap = {BitmapFactory.decodeResource(getResources(),
                R.drawable.add),BitmapFactory.decodeResource(getResources(),
                R.drawable.location),BitmapFactory.decodeResource(getResources(),
                R.drawable.camera),BitmapFactory.decodeResource(getResources(),
                R.drawable.wishlist),BitmapFactory.decodeResource(getResources(),
                R.drawable.squares)};

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
            ChatClass cclass = new ChatClass();
            switch (i%3){
                case 0:
                    ChatText ct = new ChatText(sides[i],1,msgs[i]);
                    cclass=ct;
                    chatData.add(ct);
                    break;
                case 1:
                    ChatImage ci = new ChatImage(sides[i],2,bitmap[j]);
                    cclass=ci;
                    j++;
                    break;
                case 2:
                    ChatContact cc = new ChatContact(sides[i],3,"Sandeep","7418191883");
                    cclass=cc;
                    break;
            }
            chatData.add(cclass);

        }

        adapter = new ChatAdapterTemp(this,chatData);

        chat.setAdapter(adapter);
        chat.setLayoutManager(new LinearLayoutManager(this));

        postAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(postOrAttachCheck==1){
                    ChatText chatText = new ChatText(1,1,chatBox.getText().toString());
                    chatData.add(chatText);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCATION:
                if (resultCode == RESULT_OK) {
                    Place place = PlacePicker.getPlace(data, this);
                    String toastMsg = String.format("Place: %s", place.getName());
                    Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                }
                break;
            case TAKE_PIC:
                uri = data.getData();
                performCrop();
                break;
            case OPEN_GALLERY:
                uri = data.getData();
                performCrop();
                break;
            case AUDIO:
                break;
            case CONTACT:
                if (resultCode == RESULT_OK) {
                    String name=null,number=null;
                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {


                        String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                    null, null);
                            phones.moveToFirst();
                            number = phones.getString(phones.getColumnIndex("data1"));
                        }
                        name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                    }
                    ChatContact chatContact = new ChatContact(1,3,name,number);
                    chatData.add(chatContact);
                    adapter.notifyDataSetChanged();
                    chat.scrollToPosition(chatData.size()-1);
                }
                break;
            case CROP_PIC:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    // get the cropped bitmap
                    Bitmap thePic = extras.getParcelable("data");

                    ChatImage chatImage = new ChatImage(1,2,thePic);
                    chatData.add(chatImage);
                    adapter.notifyDataSetChanged();
                    chat.scrollToPosition(chatData.size()-1);
                    break;

                }
        }
        dialogChat.dismiss();
    }

    private void performCrop() {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(uri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, CROP_PIC);
        } catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
