package com.example.abhilashsk.storelocator;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.abhilashsk.storelocator.MarkersMapActivity;

import java.util.ArrayList;

public class MarkersMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitude;
    Double longitude;
    Tracer gps;
    ArrayList<String> shopnames;
    ArrayList<String> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markers_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        gps = new Tracer(MarkersMapActivity.this);
        if (gps.getLocation() != null) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Log.d("onCreate MarkersMap","Your location is "+latitude+" "+longitude);
        }else
        {
//gps.showSettingAlert();
            latitude = 12.942898;
            longitude = 77.56819659999996;
            Log.d("onCreate MarkersMap","Your location cannot be found");
        }
        Intent intent=getIntent();
        shopnames=intent.getStringArrayListExtra("shopname_key");
        locations=intent.getStringArrayListExtra("address_key");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i=0;i<shopnames.size();i++) {
            GeocodingLocation locationAddress = new GeocodingLocation();
            Bundle bundle = locationAddress.getAddressFromLocation(locations.get(i),
                    getApplicationContext());
            Double lat1 = Double.parseDouble(bundle.getString("latitude"));
            Double lon1 = Double.parseDouble(bundle.getString("longitude"));
            LatLng loc = new LatLng(lat1, lon1);
            mMap.addMarker(new MarkerOptions().position(loc).title(shopnames.get(i)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
        Log.d("onCreate MarkersMap","Your location is "+latitude+" "+longitude);
        LatLng yourloc = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(yourloc).title("Your Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourloc,15));
    }
}
