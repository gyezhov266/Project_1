package com.example.project1.controller;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.project1.entities.Planet;
import com.example.project1.exceptions.AuthenticationFailed;
import com.example.project1.exceptions.EntityNotFound;
import com.example.project1.service.PlanetService;

@RestController
public class PlanetController {
    private static Logger planetLogger = LoggerFactory.getLogger(PlanetController.class);
    
    @Autowired
    private PlanetService planetService;

    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFailed(AuthenticationFailed e) {
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>("Could not delete Team", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/planet/id/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable int id){
        return new ResponseEntity<>(this.planetService.getPlanetById(id),HttpStatus.OK);
    }

    @GetMapping("/planet/{name}")
    public ResponseEntity<Planet> getPlanetByName(@PathVariable String name){
        return new ResponseEntity<>(this.planetService.getPlanetByName(name),HttpStatus.OK);
    }

    @GetMapping("/planets")
    public ResponseEntity<List<Planet>> getAllPlanets(){
        List<Planet> planets = this.planetService.getAllPlanets();
        if (planets.size() > 0){
            return new ResponseEntity<>(planets,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(planets,HttpStatus.NOT_FOUND);
        }
    }
    
    // Get moons associated with a planet
    //	app.get("api/planet/{id}/moons", ctx -> moonController.getPlanetMoons(ctx));

    @PostMapping("/planet")
    public ResponseEntity<String> createPlanet(@RequestBody Planet planet){
        String message = this.planetService.createPlanet(planet);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/planet/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable int id){
        try{
            String message = this.planetService.deletePlanetById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){ 
            return new ResponseEntity<>("Could not Delete Planet", HttpStatus.CREATED);
        }
    }
}

// comment