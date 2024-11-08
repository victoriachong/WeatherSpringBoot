package com.example.WeatherCalenderTest.controller;

import com.example.WeatherCalenderTest.model.WeatherUser;
import com.example.WeatherCalenderTest.resources.UserInput;
import com.example.WeatherCalenderTest.resources.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users")
    public List<WeatherUser> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path="/users/username/{username}")
    public List<WeatherUser> getUserByUsername(@PathVariable("username") String username) {
        return userRepository.findByUsernameContainsIgnoreCase(username);
    }

    @GetMapping(path="/users/email/{email}")
    public List<WeatherUser> getUserByEmail(@PathVariable("email") String email) {
        return userRepository.findByEmailContainsIgnoreCase(email);
    }

    @GetMapping(path = "/users/{id}")
    public WeatherUser getUser(@PathVariable("id") long id){
        Optional<WeatherUser> userWithTheGivenID= userRepository.findById(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userWithTheGivenID.get();
    }

    @PostMapping(path = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUser(@RequestBody UserInput inputUser){
        WeatherUser newWeatherUser = inputUser.toNewUser();
        userRepository.save(newWeatherUser);
    }

    @DeleteMapping(path = "/users/{id}")
    @Transactional
    public void deleteUser(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }

    @PatchMapping(path = "users/{id}")
    public WeatherUser editUser(@PathVariable("id") long id, @RequestBody UserInput newUser){
        WeatherUser editWeatherUser = newUser.toNewUser();

        Optional<WeatherUser> userWithTheGivenID = userRepository.findById(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        WeatherUser edittedWeatherUser = editUserService(userWithTheGivenID.get(), editWeatherUser);
        userRepository.save(edittedWeatherUser);

        return edittedWeatherUser;
    }

    private WeatherUser editUserService(WeatherUser cuser, WeatherUser editWeatherUser) {
        if (editWeatherUser.getUsername() != null) {
            cuser.setUsername(editWeatherUser.getUsername());
        }

        if (editWeatherUser.getDefaultLocation() != null) {
            cuser.setDefaultLocation(editWeatherUser.getDefaultLocation());
        }

        if (editWeatherUser.getEmail() != null) {
            cuser.setEmail(editWeatherUser.getEmail());
        }

        if (editWeatherUser.getPassword() != null) {
            cuser.setPassword(editWeatherUser.getPassword());
        }

        return cuser;
    }


}
