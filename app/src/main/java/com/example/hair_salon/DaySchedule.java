package com.example.hair_salon;

import java.text.DateFormat;
import java.util.Date;

public class DaySchedule {
    private String Name;
    private String hourStart;
    private String hourFinish;
    private Boolean available;

    public DaySchedule(String name, String hourStart, String hourFinish, Boolean available) {
        Name = name;
        this.hourStart = hourStart;
        this.hourFinish = hourFinish;
        this.available = available;
    }

    public String getName() {
        return Name;
    }

    public String getHourStart() {
        return hourStart;
    }

    public String getHourFinish() {
        return hourFinish;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setHourStart(String hourStart) {
        this.hourStart = hourStart;
    }

    public void setHourFinish(String hourFinish) {
        this.hourFinish = hourFinish;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}