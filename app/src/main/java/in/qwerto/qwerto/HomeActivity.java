package in.qwerto.qwerto;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.List;

import in.qwerto.qwerto.request.FragmentRequests;
import in.qwerto.qwerto.stores.FragmentStores;


public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    NonSwipeablePager viewPager;
    LinearLayout[] layouts;
    TextView actionBarText;
    String titles[] = {"Requests", "Stores", "Favorites", "More"};
    private final int TAKE_PIC = 1, OPEN_GALLERY = 2, LOCATION = 3, CONTACT = 4, AUDIO = 5, WISHLIST = 6, CROP_PIC = 7;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);

        findViewById(R.id.tRequest).setOnClickListener(this);
        findViewById(R.id.tStores).setOnClickListener(this);
        findViewById(R.id.tFavorites).setOnClickListener(this);
        findViewById(R.id.tMore).setOnClickListener(this);
        layouts = new LinearLayout[]{(LinearLayout) findViewById(R.id.tRequest), (LinearLayout) findViewById(R.id.tStores), (LinearLayout) findViewById(R.id.tFavorites), (LinearLayout) findViewById(R.id.tMore)};

        actionBarText = (TextView) findViewById(R.id.abText);


        viewPager = (NonSwipeablePager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        setSelectedTab(0);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectedTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentRequests(), "Requests");
        adapter.addFragment(new FragmentStores(), "Stores");
        adapter.addFragment(new FragmentFavs(), "Favorites");
        adapter.addFragment(new FragmentMore(), "More");
        viewPager.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tRequest:
                setSelectedTab(0);
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.tStores:
                setSelectedTab(1);
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.tFavorites:
                setSelectedTab(2);
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.tMore:
                setSelectedTab(3);
                viewPager.setCurrentItem(3, false);
                break;
        }
    }

    private void setSelectedTab(int index) {
        for (int i = 0; i < layouts.length; i++) {
            layouts[i].setBackgroundColor(Color.WHITE);
            actionBarText.setText(titles[index]);
        }
        layouts[index].setBackgroundColor(Color.GRAY);
    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {

            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
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
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                        Log.d(this.getClass().getName(), "Selected " + name);
                    }
                }
                break;
            case CROP_PIC:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    // get the cropped bitmap
                    Bitmap thePic = extras.getParcelable("data");
                    break;

                }
        }
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
