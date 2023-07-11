package com.example.WeatherCalenderTest.controller;

import com.example.WeatherCalenderTest.model.Event;
import com.example.WeatherCalenderTest.model.WeatherUser;
import com.example.WeatherCalenderTest.resources.EventInput;
import com.example.WeatherCalenderTest.resources.EventsRepository;
import com.example.WeatherCalenderTest.resources.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class EventsController {
    private EventsRepository eventsRepository;
    private UserRepository userRepository;

    public EventsController (EventsRepository eventsRepository, UserRepository userRepository){this.eventsRepository=eventsRepository; this.userRepository = userRepository;}

    @GetMapping(path = "/events")
    public List<Event> getAllEvents(){
        return eventsRepository.findAll();
    }

    @GetMapping(path="/events/{id}")
    public Set<Event> getUsersEvents(@PathVariable("id") Long id){
        Optional<WeatherUser> userWithTheGivenID= userRepository.findById(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userWithTheGivenID.get().getEvents();
    }

    @PostMapping(path="events")
    public Event addEvent(@RequestBody EventInput inputEvent){
        Event newEvent = inputEvent.toNewEvent(userRepository);
        eventsRepository.save(newEvent);
        return newEvent;
    }

    @DeleteMapping(path = "/events/{id}")
    @Transactional
    public void deleteEvent(@PathVariable("id") long id){
        eventsRepository.deleteById(id);
    }


    @PatchMapping(path = "events/{id}")
    public Event editEvent(@PathVariable("id") long id , @RequestBody EventInput inputEvent){
//        Find the event to be editted
        Optional<Event> eventWithGivenID = eventsRepository.findById(id);
        if (eventWithGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
//        Make Eventinput into event shouldnt have a userId as original event should alr have a user
        Event eventInput = inputEvent.toNewEvent(userRepository);

        Event edittedEvent = editEventService(eventWithGivenID.get(), eventInput);
        eventsRepository.save(edittedEvent);

        return edittedEvent;
    }


    private Event editEventService(Event cevent, Event editEvent) {
        if (editEvent.getTitle() != null) {
            cevent.setTitle(editEvent.getTitle());
        }

        if (editEvent.getDescription() != null) {
            cevent.setDescription(editEvent.getDescription());
        }

        if (editEvent.getStartTime() != null) {
            cevent.setStartTime(editEvent.getStartTime());
        }

        if (editEvent.getEndTime() != null) {
            cevent.setEndTime(editEvent.getEndTime());
        }

        return cevent;
    }
}
