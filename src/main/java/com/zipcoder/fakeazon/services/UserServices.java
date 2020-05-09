package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zipcoder.fakeazon.exception.IllegalArgumentException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private UserRepository userRepo;

    @Autowired
    public UserServices(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user){
        if(!userRepo.findUserByEmail(user.getEmail()).isPresent()){
            return userRepo.save(user);
        }
        else throw new IllegalArgumentException("Email is already in our system. Try something else!");
    }

    public Optional<User> findUserById(Integer id){
        return userRepo.findById(id);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }
}
