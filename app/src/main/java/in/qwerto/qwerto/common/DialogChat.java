package in.qwerto.qwerto.common;

/**
 * Created by sandeep on 22/8/15.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import in.qwerto.qwerto.AppLocationService;
import in.qwerto.qwerto.LocationAddress;
import in.qwerto.qwerto.R;

/**
 * Created by sandeep on 21/8/15.
 */
public class DialogChat extends Dialog implements View.OnClickListener {

    Context context;
    ImageView camera, gallery, location, contact, audio, wishlist;
    private int TAKE_PIC=1,OPEN_GALLERY=2,LOCATION=3,CONTACT=4,AUDIO=5,WISHLIST=6;
    AppLocationService appLocationService;
    Location gpsLocation;
    double latitude,longitude;


    public DialogChat(Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.dialog_chat);

        initDialog();


    }

    private void initDialog() {

        camera = (ImageView) findViewById(R.id.ivCamera);
        gallery = (ImageView) findViewById(R.id.ivGallery);
        location = (ImageView) findViewById(R.id.ivLocation);
        contact = (ImageView) findViewById(R.id.ivContact);
        audio = (ImageView) findViewById(R.id.ivAudio);
        wishlist = (ImageView) findViewById(R.id.ivWishlist);


        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        location.setOnClickListener(this);
        contact.setOnClickListener(this);
        audio.setOnClickListener(this);
        wishlist.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.ivCamera:
                Intent intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                getOwnerActivity().startActivityForResult(intent, TAKE_PIC);
                break;
            case R.id.ivGallery:
                Intent photoPic = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                getOwnerActivity().startActivityForResult(photoPic, OPEN_GALLERY);
                break;
            case R.id.ivLocation:
                //GetLocation
                appLocationService = new AppLocationService(getOwnerActivity());

                gpsLocation = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);

                if (gpsLocation != null) {
                    latitude = gpsLocation.getLatitude();
                    longitude = gpsLocation.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getOwnerActivity().getApplicationContext(), new GeocoderHandler());
                } else {
                    showSettingsAlert();
                }
                break;
            case R.id.ivContact:
                Intent i = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                getOwnerActivity().startActivityForResult(i,CONTACT);
                break;
            case R.id.ivAudio:
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                getOwnerActivity().startActivityForResult(intent_upload, AUDIO);
                break;
            case R.id.ivWishlist:
                break;

        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getOwnerActivity());
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getOwnerActivity().startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

        }
    }
}
