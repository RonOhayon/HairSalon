package com.example.hair_salon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

public class LocationSetUp extends AppCompatActivity implements callBack_map {

    public static final String EXTRA_KEY_LAT = "EXTRA_KEY_LAT";
    public static final String EXTRA_KEY_LONG = "EXTRA_KEY_LONG";
    public static final String EXTRA_KEY_CHECK = "EXTRA_KEY_CHECK";
    private Button lsu_BTN_set,find;
    private ImageView lsu_IMG_pin,lsu_IMG_BB;
    private EditText lsu_EDT_search;
    private FrameLayout lsu_LAY_map;
    Fragment_Map fragment_map;
    LocationManager mLocationManager;
    private Location location;
    double log,lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_set_up);
        findView();
        initFragment();
        location = new Location(getLastKnownLocation());
        setLocation();
        initView();
    }

    private void findView(){
        lsu_BTN_set=findViewById(R.id.lsu_BTN_set);
        lsu_IMG_BB=findViewById(R.id.lsu_IMG_BB);
        lsu_IMG_pin=findViewById(R.id.lsu_IMG_pin);
        lsu_EDT_search=findViewById(R.id.lsu_EDT_search);
        lsu_LAY_map = findViewById(R.id.lsu_LAY_map);

    }

    private void initFragment(){
        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.lsu_LAY_map,fragment_map).commit();

    }

    private Location getLastKnownLocation() {

        if (ActivityCompat.checkSelfPermission(LocationSetUp.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            List<String> providers = mLocationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            return bestLocation;
        }
        else {
            ActivityCompat.requestPermissions(LocationSetUp.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            getLastKnownLocation();
            //get location again after granting permission
        }
        return null;
    }

    private void setLocation(){
        lsu_EDT_search.setText(location.getLatitude()+" : "+location.getLongitude());
        log=location.getLongitude();
        lat=location.getLatitude();

    }

    private void initView(){
        lsu_IMG_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_map.setOnPosition(lat,log);
            }
        });
        lsu_IMG_BB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        lsu_BTN_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAndSend();
            }
        });
    }

    @Override
    public void sendLocation(double lat, double lng) {
        lsu_EDT_search.setText(location.getLatitude()+" : "+location.getLongitude());
        location.setLatitude(lat);
        location.setLongitude(lng);
    }

    private void closeActivity() {
        finish();
        setResult(RESULT_CANCELED);
    }

    private void closeAndSend(){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_KEY_LAT,lat);
        intent.putExtra(EXTRA_KEY_LONG,log);
        setResult(RESULT_OK,intent);
        finish();

    }
}