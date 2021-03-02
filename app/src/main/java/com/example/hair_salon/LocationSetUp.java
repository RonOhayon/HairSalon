package com.example.hair_salon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.location.LocationCallback;

import java.util.List;

public class LocationSetUp extends AppCompatActivity {

    private Button lsu_BTN_set;
    private ImageView lsu_IMG_pin,lsu_IMG_BB;
    private EditText ls_EDT_search;
    private FrameLayout ls_LAY_map;
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
    }

    private void findView(){
        lsu_BTN_set=findViewById(R.id.lsu_BTN_set);
        lsu_IMG_BB=findViewById(R.id.ls_IMG_BB);
        lsu_IMG_pin=findViewById(R.id.ls_IMG_pin);
        ls_EDT_search=findViewById(R.id.ls_EDT_search);
        ls_LAY_map = findViewById(R.id.lsu_LAY_map);
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
        ls_EDT_search.setText(location.getLatitude()+" : "+location.getLongitude());
        log=location.getLongitude();
        lat=location.getLatitude();
        fragment_map.setOnPosition(lat,log);
    }
}