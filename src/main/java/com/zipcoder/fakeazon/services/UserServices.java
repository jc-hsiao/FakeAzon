package com.zipcoder.fakeazon.services;
import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServices {
    private final UserRepository repo;

    @Autowired
    public UserServices(UserRepository repo) {
        this.repo = repo;
    }

    public Optional<User> findOne(Integer userId){
        return repo.findById(userId);
    }



}
