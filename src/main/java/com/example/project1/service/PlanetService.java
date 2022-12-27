package com.example.project1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.entities.Planet;
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
            return new Planet();
        }
    }

    public Planet getPlanetByName(String name){
        Optional<Planet> possiblePlanet = this.planetDao.findByName(name);
        if (possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            return new Planet();
        }
    }

    public List<Planet> getAllPlanets() {
        return this.planetDao.findAll();
    }
}
