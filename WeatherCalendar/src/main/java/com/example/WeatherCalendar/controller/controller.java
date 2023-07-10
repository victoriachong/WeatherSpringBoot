package com.example.WeatherCalendar.controller;

import com.example.WeatherCalendar.Model.event;
import com.example.WeatherCalendar.service.service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class controller {
    private final service eventService;

    public controller(service eventService){
        this.eventService = eventService;
    }

    // @GetMapping is an HTTP request type. The purpose of @GetMapping is...
    @GetMapping (path = "/events")
    public List<event> getAllEvents(){
        return eventService.getAllEvents();
    }

    // @PostMapping is an HTTP request type.  The purpose of @PostMapping is...
    @PostMapping(path = "/events")
    public void insertEvent(@RequestBody event newEvent) {
        try {
            eventService.insertEvent(newEvent);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // @GetMapping is an HTTP request type. The purpose of @GetMapping is...
    @GetMapping(path = "/events/{EventID}")
    public event getEvent(@PathVariable("EventID") Long EventID) {
        Optional<event> eventWithTheGivenId = eventService.getEvent(EventID);
        if (eventWithTheGivenId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return eventWithTheGivenId.get();
    }
    @DeleteMapping (path="users/{EventID}")
    public void deleteEvent (@PathVariable ("EventID") Long EventID) {
        Optional<event> eventWithTheGivenId = eventService.getEvent(EventID);
        if (eventWithTheGivenId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        eventService.deleteEvent(eventWithTheGivenId.get());
    }
    @PatchMapping (path="users/{EventID}")
    public void editEvent(@PathVariable ("EventID") Long EventID, @RequestBody event newEvent){
        Optional<event> eventWithTheGivenId = eventService.getEvent(EventID);
        if (eventWithTheGivenId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        eventService.editEvent(eventWithTheGivenId.get(), newEvent);

    }


}