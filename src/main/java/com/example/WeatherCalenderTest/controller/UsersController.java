package com.example.WeatherCalenderTest.controller;

import com.example.WeatherCalenderTest.resources.UserInput;
import com.example.WeatherCalenderTest.resources.UserRepository;
import com.example.WeatherCalenderTest.model.User;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    private UserRepository userRepository;

    public UsersController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path="/users/username")
    public List<User> getUserByUsername(@RequestParam("username") String username) {
        return userRepository.findByUsernameContainsIgnoreCase(username);
    }

    @GetMapping(path="/users/email")
    public List<User> getUserByEmail(@RequestParam("email") String email) {
        return userRepository.findByEmailContainsIgnoreCase(email);
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable("id") long id){
        Optional<User> userWithTheGivenID= userRepository.findById(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userWithTheGivenID.get();
    }

    @PostMapping(path = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUser(@RequestBody UserInput inputUser){
        User newUser = inputUser.toNewUser();
        userRepository.save(newUser);
    }

    @DeleteMapping(path = "/users/{id}")
    @Transactional
    public void deleteUser(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }

    @PatchMapping(path = "users/{id}")
    public User editUser(@PathVariable("id") long id, @RequestBody UserInput newUser){
        User editUser = newUser.toNewUser();

        Optional<User> userWithTheGivenID = userRepository.findById(id);
        if (userWithTheGivenID.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User edittedUser = editUser(userWithTheGivenID.get(), editUser);
        userRepository.save(edittedUser);

        return edittedUser;
    }

    private User editUser(User cuser, User editUser) {
        if (editUser.getUsername() != null) {
            cuser.setUsername(editUser.getUsername());
        }

        if (editUser.getEmail() != null) {
            cuser.setEmail(editUser.getEmail());
        }

        if (editUser.getPassword() != null) {
            cuser.setPassword(editUser.getPassword());
        }

        if (editUser.getFavourites() != null) {
            List<String> cfavourites = cuser.getFavourites();
            for (String Favourite : editUser.getFavourites()) {
                if (cfavourites.contains(Favourite)) {
                    cfavourites.remove(Favourite);
                } else {
                    cfavourites.add(Favourite);
                }
            }
        }
        return cuser;
    }


}
