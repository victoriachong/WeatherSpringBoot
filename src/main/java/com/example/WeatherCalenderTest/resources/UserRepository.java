package com.example.WeatherCalenderTest.resources;

import com.example.WeatherCalenderTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContainsIgnoreCase(String username);
    List<User> findByEmailContainsIgnoreCase(String email);

    User findByEmail(String email);

}
