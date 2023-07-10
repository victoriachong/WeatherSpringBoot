package com.example.WeatherCalenderTest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weatherUsers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
//    private List<Event> UserEvents = new ArrayList<Event>();
    private Double localtzoffset;
    private List<String> favourites = new ArrayList<String>();

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

//    public List<Event> getUserEvents() {
//        return UserEvents;
//    }
//
//    public void setUserEvents(List<Event> userEvents) {
//        UserEvents = userEvents;
//    }

    public Double getLocaltzoffset() {
        return localtzoffset;
    }

    public void setLocaltzoffset(Double localtzoffset) {
        this.localtzoffset = localtzoffset;
    }

    public List<String> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<String> favourites) {
        this.favourites = favourites;
    }
}
