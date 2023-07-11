package com.example.WeatherCalenderTest.resources;

import com.example.WeatherCalenderTest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Event, Long> {

}
