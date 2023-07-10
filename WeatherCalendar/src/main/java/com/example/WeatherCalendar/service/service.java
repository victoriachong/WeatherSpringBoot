package com.example.WeatherCalendar.service;

import com.example.WeatherCalendar.Model.event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class service {
    //This stores all the events in an array
    private final List<event> events = new ArrayList<>();

    // "get" will request resources from a server
    // This function will return "event" structure
    public List<event> getAllEvents() {
        return events;
    }

    //This function will add an event to the database
    public void insertEvent(event newEvent) {
        newEvent.setEventID(events.size());
        events.add(newEvent);
    }

    //This function will return an event structure as long as the event ID exists
    // The optional class will return either a null value, meaning it exists or an empty, meaning it doesn't.
    // .of() will return the contents of the event and .empty() will return an empty.
    public Optional<event> getEvent(Long id) {
        for (event event : events) {
            if (id.equals(event.getEventID())) {
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }
    //This function will delete an event
    public void deleteEvent(event cevent){
        events.remove(cevent);
    }
    //This function will allow a user to edit parts of their event
    public void editEvent (event cevent, event editEvent){
        if (editEvent.getEventTitle()!= null){
            cevent.setEventTitle(editEvent.getEventTitle());
        }
        if (editEvent.getLocation ()!= null){
            cevent.setLocation(editEvent.getLocation());
        }
        if (editEvent.getStartTime() != null){
            cevent.setStartTime(editEvent.getStartTime());
        }
        if (editEvent.getEndTime ()!= null){
            cevent.setEndTime(editEvent.getEndTime());
        }
        if (editEvent.getTzOffset ()!= null){
            cevent.setTzOffset(editEvent.getTzOffset());
        }
        if (editEvent.getDescription ()!= null){
            cevent.setDescription(editEvent.getDescription());
        }
    }
}
