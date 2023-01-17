package com.example.project1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.entities.Planet;
import com.example.project1.exceptions.EntityNotFound;
import com.example.project1.repository.PlanetDao;

@Service
public class PlanetService {
    @Autowired
    private PlanetDao planetDao;

    public Planet getPlanetById(int id){
        Optional<Planet> possiblePlanet = this.planetDao.findById(id);
        if (possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("Planet Not Found");
        }
    }

    public Planet getPlanetByName(String name){
        Optional<Planet> possiblePlanet = this.planetDao.findByName(name);
        if (possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("Planet Not Found");        
        }
    }

    public List<Planet> getAllPlanets() {
        return this.planetDao.findAll();
    }

    public String createPlanet(Planet planet){
        this.planetDao.createPlanet(planet.getId(), planet.getName(), planet.getOwnerId());
        return "Planet Created!";
    }

    // public String updatePlanet(Planet planet){
    //     int rowCount = this.planetDao.updatePlanet(planet.getName(), planet.getOwnerId(), planet.getId());
    //     if (rowCount == 1){
    //         return "Planet Updated Successfully";
    //     } else {
    //         return "Could not update table";
    //     }
    // }

    public String deletePlanetById(int id){
        this.planetDao.deleteById(id);
        return "Planet with given ID deleted";
    }
}
