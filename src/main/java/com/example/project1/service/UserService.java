package com.example.project1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.entities.User;
import com.example.project1.repository.UserDao;
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(int id){
        Optional<User> possibleUser = this.userDao.findById(id);
        if (possibleUser.isPresent()){
            return possibleUser.get();
        } else {
            return new User();
        }
    }
}
