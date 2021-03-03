package com.example.hair_salon;

import android.location.Location;

import java.sql.Time;

public class HairSalon {
    private  int hairCut;
    private int shave;
    private int HairColor;
    private int straighteningHair;
    private Location location;


    public HairSalon() {
    }
    public HairSalon(int hairCut, int shave, int hairColor, int straighteningHair, Location location) {
        this.hairCut = hairCut;
        this.shave = shave;
        HairColor = hairColor;
        this.straighteningHair = straighteningHair;
        this.location = location;
    }
    public HairSalon(Location location){
        hairCut = 50;
        shave = 25;
        HairColor = 100;
        straighteningHair =250;
        this.location = location;
    }



    public int gethairCut() {
        return hairCut;
    }


    public int getShave() {
        return shave;
    }

    public int getHairColor() {
        return HairColor;
    }

    public int getStraighteningHair() {
        return straighteningHair;
    }

    public Location getLocation() {
        return location;
    }

    public void setHairCut(int hairCut) {
        this.hairCut = hairCut;
    }

    public void setShave(int shave) {
        this.shave = shave;
    }

    public void setHairColor(int hairColor) {
        HairColor = hairColor;
    }

    public void setStraighteningHair(int straighteningHair) {
        this.straighteningHair = straighteningHair;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

