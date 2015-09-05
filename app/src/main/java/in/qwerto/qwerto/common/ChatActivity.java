package in.qwerto.qwerto.common;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;

import in.qwerto.qwerto.R;
import in.qwerto.qwerto.SingleSuggestion;

/**
 * Created by sandeep on 21/8/15.
 */
public class ChatActivity extends ActionBarActivity {



    RecyclerView chat;
    ArrayList<ChatClass> chatData;
    ChatAdapter adapter;
    private final int TAKE_PIC = 1, OPEN_GALLERY = 2, LOCATION = 3, CONTACT = 4, AUDIO = 5, WISHLIST = 6, CROP_PIC = 7;
    Uri uri;
    Toolbar toolbar;

    DownloadImageTask downloadImageTask;

    EditText chatBox;
    ImageView postAttach;

    int postOrAttachCheck=2;
    DialogChat dialogChat;

    //Hard-coded data
    String [] msgs = {"Hi, I would like to find a 2BHK near Ascendas. My budget is 12K",
            "Hello. We will process your request",
            "Hey we have found 4 suggestions for you",
            "Thanks for the response. I would like to take the second option. Can I know more details about the place.",
            "Sure, I am sending the owner's contact details. Please contact him. For any further queries contact me! :)","Thank you"};
    int [] sides={1,2,2,1,2,1};
    int [] types = {1,4,1,1,5,1,1,3,1};

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

        int k=0;
        for (int i=0;i<types.length;i++){
            switch (types[i]){
                case 1:
                    ChatText ct = new ChatText(sides[k],1,msgs[k]);
                    k++;
                    chatData.add(ct);
                    break;
                case 3:
                    ChatContact cc = new ChatContact(2,3,"Apartment owner","9876543210");
                    chatData.add(cc);
                    break;
                case 4:
                    ChatLocation cl = new ChatLocation(1,4,"Ascendas",12.985806, 80.249666);
                    chatData.add(cl);
                    break;
                case 5:
                    ArrayList<SingleSuggestion> ss = new ArrayList<SingleSuggestion>();
                    Log.d("ChatActivity","working");
                        ss.add(new SingleSuggestion("Mandakini Hostel", new ChatLocation(1,4, "Mandakini hostel",13.082680,80.270718)));
                        ss.add(new SingleSuggestion("Research Park",new ChatLocation(1,4,"Research Park",12.985854,80.249685)));
                        ss.add(new SingleSuggestion("Tiruvanmayur", new ChatLocation(1,4,"Tirubanmayur",13.009361,80.213231)));
                        ss.add(new SingleSuggestion("NIFT", new ChatLocation(1,4,"NIFT",12.984193,80.251769)));
                    ChatSuggestions cs = new ChatSuggestions(2,5,ss);
                    chatData.add(cs);
                    break;
            }
        }


//        for(int i=0;i<sides.length;i++){
//            ChatClass cclass = new ChatClass();
//            switch (i%3){
//                case 0:
//                    ChatText ct = new ChatText(sides[i],1,msgs[i]);
//                    cclass=ct;
//                    chatData.add(ct);
//                    break;
//                case 1:
//                    ChatImage ci = new ChatImage(sides[i],2,bitmap[j]);
//                    cclass=ci;
//                    j++;
//                    break;
//                case 2:
//                    ChatContact cc = new ChatContact(sides[i],3,"Sandeep","7418191883");
//                    cclass=cc;
//                    break;
//            }
//            chatData.add(cclass);
//
//        }

        adapter = new ChatAdapter(this,chatData);

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
                    LatLng ll=place.getLatLng();
                    double lon=ll.longitude;
                    double lat = ll.latitude;
                    Log.d("location","lat:"+lat+" lon:"+lon);
                    ChatLocation chatLocation = new ChatLocation(1,4,(String)place.getName(),lat,lon);
                    chatData.add(chatLocation);
                    adapter.notifyDataSetChanged();
                    chat.scrollToPosition(chatData.size()-1);
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
