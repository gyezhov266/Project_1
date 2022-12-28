package com.example.project1.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.project1.entities.Planet;
import com.example.project1.service.PlanetService;

@RestController
public class PlanetController {
    @Autowired
    private PlanetService planetService;

    @GetMapping("/planet/id/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable int id){
        Planet planet = this.planetService.getPlanetById(id);
        if (planet.getId() != 0){
            return new ResponseEntity<>(planet,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(planet,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/planet/{name}")
    public ResponseEntity<Planet> getPlanetByName(@PathVariable String name){
        Planet planet = this.planetService.getPlanetByName(name);
        if (planet.getId() != 0){
            return new ResponseEntity<>(planet,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(planet,HttpStatus.NOT_FOUND);
        }
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

    // Create a new planet, sending the data in the body as JSON
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
