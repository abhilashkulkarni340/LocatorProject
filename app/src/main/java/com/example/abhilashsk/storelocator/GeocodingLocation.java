package com.example.abhilashsk.storelocator;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodingLocation {

    private static final String TAG = "GeocodingLocation";

    public static Bundle getAddressFromLocation(final String locationAddress,
                                              final Context context) {
        final Bundle bundle = new Bundle();

                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String latitude = null;
                String longitude =null;
                String result=null;
        try {
            List<Address> addressList = geocoder.getFromLocationName(locationAddress, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                latitude=Double.toString(address.getLatitude());
                longitude=Double.toString(address.getLongitude());
                Log.d("GEOLOCATION","Got latitude: "+latitude+" and longitude: "+longitude);
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to connect to Geocoder", e);
        } finally {
            if (latitude != null && longitude!=null) {
                bundle.putString("latitude", latitude);
                bundle.putString("longitude",longitude);
                Log.d("In finally","Geolocation: "+latitude+","+longitude);
            } else {
                result = "Unable to get Latitude and Longitude for this location: "+locationAddress;
                Toast.makeText(context,"Unable to get Latitude and Longitude for this location: "+locationAddress,Toast.LENGTH_LONG);
                bundle.putString("latitude", "12.9416079");
                bundle.putString("longitude","77.56688299999996");
                Log.d("GEOLOCATION ERROR",result);
            }
        }
        return bundle;

    }
}