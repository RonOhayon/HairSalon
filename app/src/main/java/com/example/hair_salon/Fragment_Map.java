package com.example.hair_salon;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment {

    protected View view;
    protected  SupportMapFragment smp;
    public double lat;
    public double lng;
    callBack_map cb_map;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map,container,false);
        initMap();

        return view;
    }

    public void initMap(){
        smp = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_map);
        smp.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();

                        markerOptions.position(latLng);

                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);

                        cb_map.sendLocation(latLng.latitude,latLng.longitude);

                        googleMap.clear();

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

                        googleMap.addMarker(markerOptions);
                    }

                });
            }
        });
    }

    public void setOnPosition(double latitude ,double longitude){
        LatLng latLng = new LatLng(latitude,longitude);
        Log.d("pff", "setOnPosition: " + latitude +":" + longitude);
        smp.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);

                markerOptions.title(latLng.latitude+" : "+latLng.longitude);

                googleMap.clear();

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

                googleMap.addMarker(markerOptions);

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof callBack_map){
            cb_map =(callBack_map)context;
        }
        else throw  new RuntimeException(context.toString()+"must implement callback map");
    }
}
