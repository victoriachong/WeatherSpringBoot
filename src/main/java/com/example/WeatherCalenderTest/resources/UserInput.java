package com.example.WeatherCalenderTest.resources;

import com.example.WeatherCalenderTest.model.WeatherUser;

import java.util.ArrayList;
import java.util.List;


public class UserInput {
    private String username;
    private String password;
    private String email;
    private Double localtzoffset;

    private List<String> favourites = new ArrayList<String>();

    public List<String> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<String> favourites) {
        this.favourites = favourites;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Double getLocaltzoffset() {
        return localtzoffset;
    }

    public void setLocaltzoffset(Double localtzoffset) {
        this.localtzoffset = localtzoffset;
    }

    public WeatherUser toNewUser(){
        WeatherUser newuser = new WeatherUser();
        newuser.setUsername(this.username);
        newuser.setEmail(this.email);
        newuser.setPassword(this.password);
        newuser.setLocaltzoffset(this.localtzoffset);
        newuser.setFavourites(this.favourites);

        return newuser;

    }
}
