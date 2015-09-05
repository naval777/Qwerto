package in.qwerto.qwerto.Maps;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import in.qwerto.qwerto.R;
import in.qwerto.qwerto.SingleSuggestion;
import in.qwerto.qwerto.common.ChatLocation;


public class MapsActivity extends ActionBarActivity implements OnMapReadyCallback {

    TextView mapText;
    GoogleMap map;
    ArrayList<LatLongs> arrayLatLng;
    ArrayList<SingleSuggestion> suggestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_maps);

        mapText = (TextView) findViewById(R.id.mapText);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        suggestions = (ArrayList<SingleSuggestion>) getIntent().getSerializableExtra("locations");
//        try{
//            // Get the Bundle Object
//            Bundle bundleObject = getIntent().getExtras();
//
//            // Get ArrayList Bundle
////            suggestions = (ArrayList<SingleSuggestion>) bundleObject.getSerializable("locations");
//        } catch(Exception e){
//            e.printStackTrace();
//            Log.d("maps","Cupping");
//
//        }
        suggestions = new ArrayList<SingleSuggestion>();
        Log.d("ChatActivity","working");
        suggestions.add(new SingleSuggestion("Mandakini Hostel", new ChatLocation(1,4, "Mandakini hostel",13.082680,80.270718)));
        suggestions.add(new SingleSuggestion("Research Park",new ChatLocation(1,4,"Research Park",12.985854,80.249685)));
        suggestions.add(new SingleSuggestion("Tiruvanmayur", new ChatLocation(1,4,"Tirubanmayur",13.009361,80.213231)));
        suggestions.add(new SingleSuggestion("NIFT", new ChatLocation(1,4,"NIFT",12.984193,80.251769)));

        map = mapFragment.getMap();
        map.setMyLocationEnabled(true);

        arrayLatLng = new ArrayList<LatLongs>();
        arrayLatLng.add(new LatLongs("Phoenix",12.991403,80.216494));
        arrayLatLng.add(new LatLongs("Velachery gate",12.985165,80.232026));
        arrayLatLng.add(new LatLongs("Sai sananda",12.975841,80.221062));
        arrayLatLng.add(new LatLongs("Taramani",12.981388,80.243227));

        map.setInfoWindowAdapter(new MapsinfoWindowAdapter());

    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-33.867, 151.206);
        LatLng melbourne = new LatLng(-37.8136, 144.9631);
        LatLng mid = new LatLng(12.981388,80.213220);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mid, 10));

        for(int i =0;i<suggestions.size();i++){
            LatLng ll = new LatLng(suggestions.get(i).getChatLocation().getLatitude(),suggestions.get(i).getChatLocation().getLongitude());
            map.addMarker(new MarkerOptions().title(suggestions.get(i).getName()).position(ll));
        }

//        map.addMarker(new MarkerOptions()
//                .title("Sydney")
//                .snippet("The most populous city in Australia.")
//                .position(sydney));
//
//        map.addMarker(new MarkerOptions().title("Melbourne").icon(BitmapDescriptorFactory.fromResource(R.drawable.add)).position(melbourne));

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mapText.setText("Marker: " + marker.getTitle());
                return false;
            }
        });
    }

    class MapsinfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MapsinfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.view_maps_info, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    class LatLongs{

        double lat;
        double lng;
        String name;
        LatLng ltln;

        LatLongs(String name,double lat, double lng){
            this.name=name;
            this.lat = lat;
            this.lng = lng;
            ltln = new LatLng(lat,lng);
        }

        public LatLng getLtln(){
            return ltln;
        }

        public String getName(){
            return name;
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }
}
