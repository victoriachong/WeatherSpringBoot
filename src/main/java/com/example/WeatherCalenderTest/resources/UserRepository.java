package com.example.WeatherCalenderTest.resources;

import com.example.WeatherCalenderTest.model.WeatherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<WeatherUser, Long> {
    List<WeatherUser> findByUsernameContainsIgnoreCase(String username);
    List<WeatherUser> findByEmailContainsIgnoreCase(String email);

    WeatherUser findByEmail(String email);

}
