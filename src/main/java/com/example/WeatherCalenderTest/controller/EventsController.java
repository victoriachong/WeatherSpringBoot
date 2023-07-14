package com.example.WeatherCalenderTest.controller;

import com.example.WeatherCalenderTest.model.WeatherEvent;
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
    public List<WeatherEvent> getAllEvents(){
        return eventsRepository.findAll();
    }

    @GetMapping(path="/events/{userid}")
    public Set<WeatherEvent> getUsersEvents(@PathVariable("userid") Long id){
        Optional<WeatherUser> userWithTheGivenID= userRepository.findById(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userWithTheGivenID.get().getUserEvents();
    }

    @PostMapping(path="events")
    public WeatherEvent addEvent(@RequestBody EventInput inputEvent){
        WeatherEvent newWeatherEvent = inputEvent.toNewEvent(userRepository);
        eventsRepository.save(newWeatherEvent);
        return newWeatherEvent;
    }

    @DeleteMapping(path = "/events/{id}")
    @Transactional
    public void deleteEvent(@PathVariable("id") long id){
        eventsRepository.deleteById(id);
    }


    @PatchMapping(path = "events/{id}")
    public WeatherEvent editEvent(@PathVariable("id") long id , @RequestBody EventInput inputEvent){
//        Find the event to be editted
        Optional<WeatherEvent> eventWithGivenID = eventsRepository.findById(id);
        if (eventWithGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
//        Make Eventinput into event shouldnt have a userId as original event should alr have a user
        WeatherEvent weatherEventInput = inputEvent.toNewEvent(userRepository);

        WeatherEvent edittedWeatherEvent = editEventService(eventWithGivenID.get(), weatherEventInput);
        eventsRepository.save(edittedWeatherEvent);

        return edittedWeatherEvent;
    }


    private WeatherEvent editEventService(WeatherEvent cevent, WeatherEvent editWeatherEvent) {
        if (editWeatherEvent.getTitle() != null) {
            cevent.setTitle(editWeatherEvent.getTitle());
        }

        if (editWeatherEvent.getDescription() != null) {
            cevent.setDescription(editWeatherEvent.getDescription());
        }

        if (editWeatherEvent.getStartTime() != null) {
            cevent.setStartTime(editWeatherEvent.getStartTime());
        }

        if (editWeatherEvent.getEndTime() != null) {
            cevent.setEndTime(editWeatherEvent.getEndTime());
        }

        return cevent;
    }
}
