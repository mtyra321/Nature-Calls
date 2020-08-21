package com.example.myapplication.fragment;

import android.location.Location;

public class UserHelperClass {
    String name, email, requests;
    Location location;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String requests, Location location) {
        this.name = name;
        this.email = email;
        this.requests = requests;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserHelperClass(String name, String email, String requests) {
        this.name = name;
        this.email = email;
        this.requests = requests;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }
}
