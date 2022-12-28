package com.example.project1.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	//	app.post("api/planet", ctx -> planetController.createPlanet(ctx));

    // Delete a planet and all of its moons
    //app.delete("api/planet/{id}", ctx -> planetController.deletePlanet(ctx));
}
