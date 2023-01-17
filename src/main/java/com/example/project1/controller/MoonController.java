package com.example.project1.controller;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.project1.entities.Moon;
import com.example.project1.exceptions.AuthenticationFailed;
import com.example.project1.exceptions.EntityNotFound;
import com.example.project1.service.MoonService;

@RestController
public class MoonController {
    @Autowired
    private MoonService moonService;
    private static Logger moonLogger = LoggerFactory.getLogger(MoonController.class);

    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFailed(AuthenticationFailed e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>("Could not delete Team", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/moon/id/{id}")
    public ResponseEntity<Moon> getMoonById(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.getMoonById(id), HttpStatus.OK);
    }

    @GetMapping("api/moon/{name}")
    public ResponseEntity<Moon> getMoonByName(@PathVariable String name){
        Moon moon = this.moonService.getMoonByName(name);
        if (moon.getId() != 0){
            return new ResponseEntity<>(moon,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(moon,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/moons")
    public ResponseEntity<List<Moon>> getAllMoons(){
        List<Moon> moons = this.moonService.getAllMoons();
        if (moons.size() > 0){
            return new ResponseEntity<>(moons,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(moons,HttpStatus.NOT_FOUND);
        }
    }

    // Create a new moon, sending the data in the body as JSON
    @PostMapping("api/moon")
    public ResponseEntity<String> createMoon(@RequestBody Moon moon){
        String message = this.moonService.createMoon(moon);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("api/moon/id/{id}")
    public ResponseEntity<String> deleteMoon(@PathVariable int id){
        try{
            String message = this.moonService.deleteMoonById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){ 
            return new ResponseEntity<>("Could not Delete Moon", HttpStatus.CREATED);
        }
    }

    @GetMapping("api/{myplanetid}/moons")
    public ResponseEntity<List<Moon>> getMoonsByPlanetId(@PathVariable("myplanetid") int myPlanetId){
        return new ResponseEntity<>(this.moonService.getMoonsByPlanetId(myPlanetId), HttpStatus.OK);
    }

}
