package com.example.WeatherCalenderTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "weatherUsers")
public class WeatherUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "weatherUser", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<WeatherEvent> userEvents = new HashSet<>();
    @Column(nullable = false)
    private Double localtzoffset;
    @ElementCollection
    private List<String> favourites = new ArrayList<String>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<WeatherEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<WeatherEvent> weatherEvents) {
        this.userEvents = weatherEvents;
    }

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
