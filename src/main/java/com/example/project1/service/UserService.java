package com.example.project1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.entities.User;
import com.example.project1.entities.UsernamePasswordAuthentication;
import com.example.project1.exceptions.EntityNotFound;
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

    public User getUserByUsername(UsernamePasswordAuthentication loginRequest){
        Optional<User> possibleUser = this.userDao.findByusername(loginRequest.getUsername());
        if (possibleUser.isPresent()){
            User u = possibleUser.get();
            if (u.getPassword().equals(loginRequest.getPassword())){
                return u;
            } else {
                throw new EntityNotFound("Password is incorrect");
            }
        } else {
            throw new EntityNotFound("Username is incorrect");
        }
    }

    public String register(UsernamePasswordAuthentication registerRequest){
        this.userDao.createUser(registerRequest.getUsername(), registerRequest.getPassword());
        return "User Created!";
    }
}
