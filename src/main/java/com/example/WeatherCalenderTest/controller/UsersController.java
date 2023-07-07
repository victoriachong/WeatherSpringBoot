package com.example.WeatherCalenderTest.controller;

import com.example.WeatherCalenderTest.service.UserService;
import com.example.WeatherCalenderTest.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(path = "/users")
    public void insertUser(@RequestBody User newUser){
        try{
            userService.insertUser(newUser);
        } catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable("id") Integer id){
        Optional<User> userWithTheGivenID = userService.getUser(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userWithTheGivenID.get();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        Optional<User> userWithTheGivenID = userService.getUser(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(userWithTheGivenID.get());
    }

    @PatchMapping(path = "users/{id}")
    public void editUser(@PathVariable("id") Integer id, @RequestBody User editUser){
        Optional<User> userWithTheGivenID = userService.getUser(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userService.editUser(userWithTheGivenID.get(), editUser);

    }
//
//    @PutMapping(path = "users/{id}/username")
//    public void editUsername(@PathVariable("id") Integer id, @RequestBody String newUsername){
//        Optional<User> userWithTheGivenID = userService.getUser(id);
//        if (userWithTheGivenID.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        userService.editUsername(userWithTheGivenID.get(), newUsername);
//    }
//
//    @PutMapping(path = "users/{id}/password")
//    public void editPassword(@PathVariable("id") Integer id, @RequestBody String newPassword) {
//        Optional<User> userWithTheGivenID = userService.getUser(id);
//        if (userWithTheGivenID.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        userService.editPassword(userWithTheGivenID.get(), newPassword);
//    }
//
//    @PutMapping(path = "users/{id}")
//    public void editEmail(@PathVariable("id") Integer id, @RequestBody String newEmail) {
//        Optional<User> userWithTheGivenID = userService.getUser(id);
//        if (userWithTheGivenID.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        userService.editEmail(userWithTheGivenID.get(), newEmail);
//    }
//
//    @PutMapping(path = "users/{id}")
//    public void editFavourites(@PathVariable("id") Integer id, @RequestBody String Favourite) {
//        Optional<User> userWithTheGivenID = userService.getUser(id);
//        if (userWithTheGivenID.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        userService.editFavourites(userWithTheGivenID.get(),Favourite);
//    }
//

}
