package com.example.abhilashsk.storelocator;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.abhilashsk.storelocator.MarkersMapActivity;

public class MarkersMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitude;
    Double longitude;
    Tracer gps;
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
        }else
        {
//gps.showSettingAlert();
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GeocodingLocation locationAddress = new GeocodingLocation();
        Bundle bundle=locationAddress.getAddressFromLocation("Truffles, Kormanagala",
                getApplicationContext());
        Double lat1=Double.parseDouble(bundle.getString("latitude"));
        Double lon1=Double.parseDouble(bundle.getString("longitude"));

        // Add a marker in Sydney and move the camera
        LatLng loc1 = new LatLng(12.945072, 77.57134300000007);
        mMap.addMarker(new MarkerOptions().position(loc1).title("Vidyarthi Bhavan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc1));

        LatLng loc2 = new LatLng(12.9401065, 77.56437540000002);
        mMap.addMarker(new MarkerOptions().position(loc2).title("Szeuchaun Dragon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc2));

        LatLng loc3 = new LatLng(lat1, lon1);
        Log.d("LOC1",latitude+","+longitude);
        mMap.addMarker(new MarkerOptions().position(loc3).title("Truffles"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc3));

        LatLng loc4 = new LatLng(12.93837985105567, 77.56329819560051);
        mMap.addMarker(new MarkerOptions().position(loc4).title("Drugs Store"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc4));

        LatLng yourloc = new LatLng(12.9416079, 77.56688299999996);
        mMap.addMarker(new MarkerOptions().position(yourloc).title("Your Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourloc,15));
    }
}
