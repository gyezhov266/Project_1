package com.example.project1.controller;

import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.entities.User;
import com.example.project1.entities.UsernamePasswordAuthentication;
import com.example.project1.exceptions.EntityNotFound;
import com.example.project1.service.UserService;

@RestController
public class AuthenticationController {
    private static Logger authenticationLogger = LoggerFactory.getLogger(AuthenticationController.class);
    
    @Autowired
    private UserService userService;
    

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> duplicateUser(DataIntegrityViolationException e){
        authenticationLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        authenticationLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e){
        authenticationLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestBody UsernamePasswordAuthentication loginRequest){
        User u = this.userService.getUserByUsername(loginRequest);
        session.setAttribute("user", u);
        return "logged in successfully";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsernamePasswordAuthentication registerRequest){
        return new ResponseEntity<>(this.userService.register(registerRequest), HttpStatus.CREATED);
    }
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "logged out";
    }
    
}
