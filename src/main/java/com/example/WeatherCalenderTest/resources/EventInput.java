package com.example.WeatherCalenderTest.resources;

import com.example.WeatherCalenderTest.model.WeatherEvent;
import com.example.WeatherCalenderTest.model.WeatherUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class EventInput {
//    private Long id;
    private String title;
    private String description;
    private Long startTime;
    private Long endTime;
    private Long userid;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public WeatherEvent toNewEvent(UserRepository userRepository) {
        WeatherEvent newWeatherEvent = new WeatherEvent();
        newWeatherEvent.setTitle(this.getTitle());
        newWeatherEvent.setDescription(this.getDescription());
        if (this.getUserid() == null){newWeatherEvent.setWeatherUser(null);}
        else{
            Optional<WeatherUser> userWithTheGivenID= userRepository.findById(this.getUserid());
            if (userWithTheGivenID.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            else {
                newWeatherEvent.setWeatherUser(userWithTheGivenID.get());
            }
        }
        newWeatherEvent.setStartTime(this.getStartTime());
        newWeatherEvent.setEndTime(this.getEndTime());

        return newWeatherEvent;
    }


}
