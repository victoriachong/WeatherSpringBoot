package com.example.WeatherCalenderTest.resources;

import com.example.WeatherCalenderTest.model.WeatherUser;

import java.util.ArrayList;
import java.util.List;


public class UserInput {
    private String username;
    private String password;
    private String email;
    private String defaultLocation;

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
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




    public WeatherUser toNewUser(){
        WeatherUser newuser = new WeatherUser();
        newuser.setUsername(this.username);
        newuser.setEmail(this.email);
        newuser.setPassword(this.password);
        newuser.setDefaultLocation(this.defaultLocation);

        return newuser;

    }
}
