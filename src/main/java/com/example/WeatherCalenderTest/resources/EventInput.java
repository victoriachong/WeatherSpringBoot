package com.example.WeatherCalenderTest.resources;

import com.example.WeatherCalenderTest.model.Event;
import com.example.WeatherCalenderTest.model.WeatherUser;

import java.util.Optional;

public class EventInput {
    private String title;
    private String description;
    private Long startTime;
    private Long endTime;
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Event toNewEvent(UserRepository userRepository) {
        Event newEvent = new Event();
        newEvent.setTitle(this.getTitle());
        newEvent.setDescription(this.getDescription());
        Optional<WeatherUser> userWithTheGivenID= userRepository.findById(this.getUserid());
        if (userWithTheGivenID.isEmpty()){
            newEvent.setUser(null);
        }
        else{newEvent.setUser(userWithTheGivenID.get());}
        newEvent.setStartTime(this.getStartTime());
        newEvent.setEndTime(this.getEndTime());

        return newEvent;
    }


}
