package com.example.WeatherCalenderTest.service;

import com.example.WeatherCalenderTest.controller.UsersController;
import com.example.WeatherCalenderTest.model.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private Integer idcount=0;

    public List<User> getAllUsers() {
        return users;
    }
    public void insertUser(User newUser){
        newUser.setId(idcount);
        idcount+=1;
        users.add(newUser);
    }
    public Optional<User> getUser (Integer id){
        for(User user: users ){
            if(id.equals(user.getId())){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void deleteUser (User cuser){
        users.remove(cuser);
    }

    public void editUser (User cuser, User editUser){
        if(editUser.getUsername()!=null){
            cuser.setUsername(editUser.getUsername());
        }

        if(editUser.getEmail()!=null){
            cuser.setEmail(editUser.getEmail());
        }

        if(editUser.getPassword()!=null){
            cuser.setPassword(editUser.getPassword());
        }

        if(editUser.getFavourites()!=null){
            List<String> cfavourites = cuser.getFavourites();
            for(String Favourite: editUser.getFavourites()) {
                if (cfavourites.contains(Favourite)) {
                    cfavourites.remove(Favourite);
                } else {
                    cfavourites.add(Favourite);
                }
            }
        }
    }
//
//    public void editUsername (User cuser, String newUsername){
//        cuser.setUsername(newUsername);
//    }
//
//    public void editPassword (User cuser, String newPassword){
//        cuser.setUsername(newPassword);
//    }
//
//    public void editEmail (User cuser, String newEmail){
//        cuser.setUsername(newEmail);
//    }
//
//    public void editFavourites (User cuser, String Favourite){
//        List<String> cfavourites = cuser.getFavourites();
//        if(cfavourites.contains(Favourite)){
//            cfavourites.remove(Favourite);
//        }else{
//            cfavourites.add(Favourite);
//        }
//    }


}


